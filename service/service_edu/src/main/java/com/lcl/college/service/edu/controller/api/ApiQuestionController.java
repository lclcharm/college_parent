package com.lcl.college.service.edu.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.common.base.result.R;
import com.lcl.college.common.base.util.JwtInfo;
import com.lcl.college.common.base.util.JwtUtils;
import com.lcl.college.service.base.dto.MemberDto;
import com.lcl.college.service.edu.entity.vo.QuestionCommentVo;
import com.lcl.college.service.edu.entity.vo.QuestionVo;
import com.lcl.college.service.edu.feign.MemberService;
import com.lcl.college.service.edu.service.QuestionCommentService;
import com.lcl.college.service.edu.service.QuestionTagRelationService;
import com.lcl.college.service.edu.entity.Question;
import com.lcl.college.service.edu.entity.QuestionComment;
import com.lcl.college.service.edu.entity.QuestionTag;
import com.lcl.college.service.edu.entity.QuestionTagRelation;
import com.lcl.college.service.edu.entity.vo.QuestionQueryVo;
import com.lcl.college.service.edu.service.QuestionService;
import com.lcl.college.service.edu.service.QuestionTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2020/7/27 18:44
 **/
@Api(tags = "问答论坛")
@RestController
@RequestMapping("/api/edu/question")
public class ApiQuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionTagService questionTagService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private QuestionCommentService questionCommentService;

    @Autowired
    private QuestionTagRelationService questionTagRelationService;

    @ApiOperation(value = "所有问答列表")
    @GetMapping("page-list/{page}/{limit}")
    public R PageList(@ApiParam(value = "当前页码", required = true)
                      @PathVariable Long page,

                      @ApiParam(value = "每页记录数", required = true)
                      @PathVariable Long limit,

                      @ApiParam(value = "查询对象") QuestionQueryVo questionQueryVo) {
        Page<Question> questionPage = questionService.getQuestionList(page, limit, questionQueryVo);
        List<Question> questionList = questionPage.getRecords();
        List<QuestionVo> questionVoList = new ArrayList<>();
        if (questionList != null && questionList.size() > 0) {
            for (Question questionsTemp : questionList) {
                QuestionVo questionVo = new QuestionVo();
                BeanUtils.copyProperties(questionsTemp, questionVo);
                questionVo.setId(questionsTemp.getId());
                questionVo.setGmtCreate(String.valueOf(questionsTemp.getGmtCreate()));
                questionVo.setGmtModified(String.valueOf(questionsTemp.getGmtModified()));

                //查询问答的一个回答
//                ArrayList<QuestionComment> questionComments = new ArrayList<>();
                QuestionComment bestComment = questionCommentService.getBestComment(questionsTemp);
//                questionComments.add(bestComment);
//                if(questionComments.size()==1)
                questionVo.setQuestionComment(bestComment);

                //查询该问答对应的标签
                List<QuestionTag> questionTags = questionTagService.getTagIdsByQuestionId(questionVo.getId());
                if (questionTags != null)
                    questionVo.setQuestionTags(questionTags);


                MemberDto memberDto = memberService.getMemberById(questionVo.getCusId());
                questionVo.setCusName(memberDto.getNickname());
                questionVo.setCusAvatar(memberDto.getAvatar());
                questionVoList.add(questionVo);
            }
        }

        long total = questionPage.getTotal();
        long current = questionPage.getCurrent();
        long pages = questionPage.getPages();
        long size = questionPage.getSize();
        boolean hasNext = questionPage.hasNext();//下一页
        System.err.println("ApiQuestionController：" + hasNext);
        boolean hasPrevious = questionPage.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("records", questionVoList);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return R.ok().data("total", total).data("items", map);
    }


    @ApiOperation(value = "问题页面侧边数据")
    @GetMapping("list-side")
    public R hotQuestion() {

        List<Question> hotQuestions = questionService.queryHotQuestions();
        List<QuestionTag> tags = questionTagService.list();
        return R.ok().data("hotQuestions", hotQuestions).data("tags", tags);
    }

    @ApiOperation(value = "所有问题标签")
    @GetMapping("list-tags")
    public R showQuestionTags() {
        List<QuestionTag> tags = questionTagService.list();
        return R.ok().data("tags", tags);
    }


    @ApiOperation(value = "根据id问题的回答")
    @GetMapping("getbyId/{id}")
    public R getQuestionInfoById(@ApiParam(value = "问题的id值", required = true)
                                 @PathVariable String id) {
        QuestionVo questionVo = new QuestionVo();
        Question question = questionService.getQuestionById(id);

        if (StringUtils.isEmpty(question)) {
            return R.ok().message("该问题不存在");
        }
        BeanUtils.copyProperties(question, questionVo);
        questionVo.setGmtCreate(String.valueOf(question.getGmtCreate()));
        questionVo.setGmtModified(String.valueOf(question.getGmtModified()));
        //查询该问答对应的标签
        List<QuestionTag> questionTags = questionTagService.getTagIdsByQuestionId(questionVo.getId());
        questionVo.setQuestionTags(questionTags);

        //提问的人的信息
        MemberDto memberDto = memberService.getMemberById(questionVo.getCusId());
        questionVo.setCusName(memberDto.getNickname());
        questionVo.setCusAvatar(memberDto.getAvatar());

        List<QuestionComment> questionComments = questionCommentService.getQuestionCommentByQuestionId(id);
        List<QuestionCommentVo> questionCommentVoList = new ArrayList<>();
        for (QuestionComment questionComment : questionComments) {
            QuestionCommentVo questionCommentVo = new QuestionCommentVo();
            BeanUtils.copyProperties(questionComment, questionCommentVo);
            questionCommentVo.setGmtCreate(String.valueOf(questionComment.getGmtCreate()));
            questionCommentVo.setGmtModified(String.valueOf(questionComment.getGmtModified()));

            MemberDto commentMember = memberService.getMemberById(questionComment.getCusId());
            questionCommentVo.setCusName(commentMember.getNickname());
            questionCommentVo.setCusAvatar(commentMember.getAvatar());
            questionCommentVoList.add(questionCommentVo);
        }
        questionVo.setQuestionCommentVos(questionCommentVoList);
        return R.ok().data("item", questionVo);
    }

    @ApiOperation(value = "所有问题标签")
    @PutMapping("question-add")
    public R addQuestions(@ApiParam(value = "增加问题", required = true)
                          @RequestBody QuestionVo questionVo,
                          HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(jwtInfo) || StringUtils.isEmpty(jwtInfo.getId())) {
            return R.error().message("未登录");
        }


        List<QuestionTag> questionTags = questionVo.getQuestionTags();
        if (questionTags == null) {
            return R.error().message("请选择问题标签");
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVo, question);
        question.setCusId(jwtInfo.getId());
        boolean save = questionService.save(question);

        String questionId = question.getId();


        ArrayList<QuestionTagRelation> questionTagRelations = new ArrayList<>();

        for (QuestionTag questionTag : questionTags) {
            QuestionTagRelation questionTagRelation = new QuestionTagRelation();
            if (!StringUtils.isEmpty(questionTag.getId())) {
                questionTagRelation.setQuestionId(questionId);
                questionTagRelation.setQuestionTagId(questionTag.getId());
                questionTagRelations.add(questionTagRelation);
            }

        }
        boolean b = questionTagRelationService.insertQuestionTagRelation(questionTagRelations);
        if (save && b) {
            return R.ok().message("添加问题成功");
        }

        return R.ok().message("添加失败");
    }

    @ApiOperation(value = "添加回答")
    @PutMapping("comment-add")
    public R addQuestionComment(@ApiParam(value = "增加问题回答", required = true)
                                @RequestBody QuestionComment questionComment,
                                HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(jwtInfo) || StringUtils.isEmpty(jwtInfo.getId())) {
            return R.error().message("请登录后再试");
        }
        if (StringUtils.isEmpty(questionComment) || StringUtils.isEmpty(questionComment.getContent())) {
            return R.error().message("请输入评论内容");
        }
        questionComment.setCusId(jwtInfo.getId());
        questionCommentService.addQuestionComment(questionComment);
        return R.ok().message("添加成功");
    }

    @ApiOperation(value = "查询当前用户的提问")
    @GetMapping("get-member-question")
    public R getQuestionByMember(HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(jwtInfo) || StringUtils.isEmpty(jwtInfo.getId())) {
            return R.error().message("请登录后再试");
        }

        List<Question> questions = questionService.getQuestionsByMemberId(jwtInfo.getId());

        return R.ok().message("获取成功").data("questions", questions);
    }

    @ApiOperation(value = "查询当前用户的回答")
    @GetMapping("get-member-questionComment")
    public R getQuestionCommentByMember(HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(jwtInfo) || StringUtils.isEmpty(jwtInfo.getId())) {
            return R.error().message("请登录后再试");
        }

        List<QuestionComment> questionComments = questionCommentService.getQuestionCommentsByMemberId(jwtInfo.getId());

        return R.ok().message("获取成功").data("questionComments", questionComments);
    }


    @ApiOperation(value = "用户问题列表")
    @GetMapping("page-member-list/{page}/{limit}")
    public R PageListByMember(@ApiParam(value = "当前页码", required = true)
                              @PathVariable Long page,
                              @ApiParam(value = "每页记录数", required = true)
                              @PathVariable Long limit, HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(jwtInfo) || StringUtils.isEmpty(jwtInfo.getId())) {
            return R.error().message("请登录后再试");
        }


        Page<Question> questionPage = questionService.getQuestionListByMemberId(page, limit, jwtInfo.getId());
        List<Question> questionList = questionPage.getRecords();
        List<QuestionVo> questionVoList = new ArrayList<>();
        if (questionList != null && questionList.size() > 0) {
            for (Question questionsTemp : questionList) {
                QuestionVo questionVo = new QuestionVo();
                BeanUtils.copyProperties(questionsTemp, questionVo);
                questionVo.setId(questionsTemp.getId());
                questionVo.setGmtCreate(String.valueOf(questionsTemp.getGmtCreate()));
                questionVo.setGmtModified(String.valueOf(questionsTemp.getGmtModified()));


                QuestionComment bestComment = questionCommentService.getBestComment(questionsTemp);
//                questionComments.add(bestComment);
//                if(questionComments.size()==1)
                questionVo.setQuestionComment(bestComment);

                //查询该问答对应的标签
                List<QuestionTag> questionTags = questionTagService.getTagIdsByQuestionId(questionVo.getId());
                if (questionTags != null)
                    questionVo.setQuestionTags(questionTags);


                MemberDto memberDto = memberService.getMemberById(questionVo.getCusId());
                questionVo.setCusName(memberDto.getNickname());
                questionVo.setCusAvatar(memberDto.getAvatar());
                questionVoList.add(questionVo);
            }
        }

        long total = questionPage.getTotal();
        long current = questionPage.getCurrent();
        long pages = questionPage.getPages();
        long size = questionPage.getSize();
        boolean hasNext = questionPage.hasNext();//下一页
        boolean hasPrevious = questionPage.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("records", questionVoList);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return R.ok().data("total", total).data("items", map);
    }

    @ApiOperation(value = "设置优质回答")
    @PutMapping("comment-best")
    public R setBest(@ApiParam(value = "设置优质回答", required = true)
                     @RequestBody QuestionComment questionComment) {

        boolean b = questionCommentService.setBestAnswer(questionComment);
        if (b)
            return R.ok().message("问题解决");
        else
            return R.error().message("设置失败");
    }
}
