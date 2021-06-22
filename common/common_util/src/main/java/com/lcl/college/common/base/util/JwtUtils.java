package com.lcl.college.common.base.util;

import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author lcl
 * @since 2020/08/05
 */
public class JwtUtils {

    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";
    private static final long ADVANCE_EXPIRE_TIME = 60*5*1000;  //五分钟

    private static Key getKeyInstance(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary(APP_SECRET);
        return new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
    }

    public static String getJwtToken(JwtInfo jwtInfo, int expire){
        expire =1800*1000;
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("College-user")//主题
                .setIssuedAt(new Date())//颁发时间
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())//过期时间
                .claim("id", jwtInfo.getId())//用户id
                .claim("nickname", jwtInfo.getNickname())//用户昵称
                .claim("avatar", jwtInfo.getAvatar())//用户头像
                .signWith(SignatureAlgorithm.HS256, getKeyInstance())
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkJwtTToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkJwtTToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static JwtInfo getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        JwtInfo jwtInfo = new JwtInfo(claims.get("id").toString(), claims.get("nickname").toString(), claims.get("avatar").toString());
        return jwtInfo;
    }

    /**
     * 判断 token 是否过期以及其剩余存活时间
     * 如果剩余存活时间小于 5min，则颁发新的 jwt
     * 如果 jwt 正常过期(jwt过期，但是 redis 中的 jwt 没有过期)，则颁发新的 token
     * 否则视为异常过期，需要进行重新登录
     * @param token：token
     * @param redisUtil：redis工具类
     * @return ：jwt
     */
    public static String checkTokenExpireTimeAndGetNew(
            String token,
            RedisUtil redisUtil){
        Jws<Claims> claimsJws = null;
        Claims claims;
        JwtInfo member;
        try {
            //得到 DefaultJwtParser
            long expireTime = Jwts.parser()
                    //设置签名的秘钥
                    .setSigningKey(APP_SECRET)
                    .parseClaimsJws(token)
                    .getBody().getExpiration().getTime();
            long diff = expireTime - System.currentTimeMillis();
            claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
            claims = claimsJws.getBody();
            member = new JwtInfo();
            member.setId((String) claims.get("id"));
            member.setAvatar((String) claims.get("avatar"));
            member.setNickname((String) claims.get("nickname"));
            //如果有效期小于5分钟，则不建议继续使用该 jwt，颁发新的 jwt
            if (diff < ADVANCE_EXPIRE_TIME && diff > 0) {
                String jwtInfo = (String)redisUtil.get((String) claims.get("id"));
                if(StringUtils.isEmpty(jwtInfo)){
                    return null;
                }
                return getJwtToken(member, 18000);
            }
            // jwt 过期异常
        } catch (ExpiredJwtException e) {
            // 如果是正常过期 ( jwt 过期了，但是 redis 中还有数据, 同样也生成一个新的 token 返回 )
            claims = e.getClaims();
            String jwtTokenInRedis = (String) redisUtil.get((String) claims.get("id"));
            if(!StringUtils.isEmpty(jwtTokenInRedis)){
                member = new JwtInfo();
                member.setId((String) claims.get("id"));
                member.setAvatar((String) claims.get("avatar"));
                member.setNickname((String) claims.get("nickname"));
                return getJwtToken(member, 18000);
            }
            //如果 Redis 中的 jwt 也过期了，则返回 null，强制登录
            return null;
        } catch (Exception e){
            return null;
        }
        return token;
    }
}
