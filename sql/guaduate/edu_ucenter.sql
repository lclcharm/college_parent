/*
MySQL Data Transfer
Source Host: localhost
Source Database: edu_ucenter
Target Host: localhost
Target Database: edu_ucenter
Date: 2021/5/14 23:03:48
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for ucenter_member
-- ----------------------------
CREATE TABLE `ucenter_member` (
  `id` char(19) NOT NULL COMMENT '会员id',
  `openid` varchar(128) DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) DEFAULT '' COMMENT '手机号',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(3) unsigned DEFAULT '0' COMMENT '性别 1 男，2 女',
  `age` tinyint(3) unsigned DEFAULT '0' COMMENT '年龄',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='会员表';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `ucenter_member` VALUES ('1366743188068417538', null, '15113682149', '5113682149@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '忘忧', '1', '17', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/avatar/2021/04/06/20210406173935.jpg', '你好啊', '0', '0', '2021-03-02 21:32:24', '2021-05-07 09:36:19');
INSERT INTO `ucenter_member` VALUES ('1366751784898469890', 'o3_SC54XjoKgcqoHGstf6P7FhIdE', '17313682149', '2405966806@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '蛾眉', '0', '28', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/avatar/2021/05/07/20210507211941.jpg', '众女嫉余之蛾眉兮，谣诼谓余以善淫', '0', '0', '2021-03-02 22:06:33', '2021-05-07 21:20:22');
INSERT INTO `ucenter_member` VALUES ('1379633042041110530', null, '15113682148', '25113682148@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '流流舞', '0', '18', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/avatar/2021/04/07/20210407111336.jpg', '今天天气不错', '0', '0', '2021-04-07 11:12:04', '2021-04-07 12:02:35');
INSERT INTO `ucenter_member` VALUES ('1390485756421705730', null, '19138982414', '', 'e10adc3949ba59abbe56e057f20f883e', '天下秀', '0', '0', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/avatar/default.jpg', null, '0', '0', '2021-05-07 09:56:53', '2021-05-07 10:00:09');
INSERT INTO `ucenter_member` VALUES ('1390536558913884162', null, '', '2201448347@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '小手冰凉', '0', '0', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/avatar/default.jpg', null, '1', '0', '2021-05-07 13:18:45', '2021-05-07 13:18:45');
