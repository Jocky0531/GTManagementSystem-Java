package com.jyz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.jyz.comm.lang.Result;
import com.jyz.entity.Students;
import com.jyz.entity.TopicAndStudent;
import com.jyz.entity.Topics;
import com.jyz.mapper.TopicsMapper;
import com.jyz.service.TopicsSrevice;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jocky
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    TopicsMapper topicsMapper;

    @Autowired
    TopicsSrevice topicsSrevice;

//    根据学生学号查询课题
    @GetMapping("/getstudenttopic")
    public Result getStudentTopic(@RequestParam String studentid){

        Topics topics = topicsSrevice.getOne(new QueryWrapper<Topics>()
                .eq("studentid",studentid));
        if (topics == null){
            Result.succ(null);
        }
        return Result.succ(topics);
    }

//    查找当前老师未被学生选择的课题
    @GetMapping("/getteachertopics")
    public Result getTeacherTopics(@RequestParam String teacherid){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("teacherid",teacherid);
        columnMap.put("studentid",null);

        List<Topics> topicsList = topicsSrevice.listByMap(columnMap);

        return Result.succ(topicsList);
    }

    //通过连表 课题表和学生表，查询出当前老师管理的学生已确认通过审核的课题
    @GetMapping("/getconfirmedtopicsandstudents")
    public Result getConfirmedTopicsAndStudents(@RequestParam String teacherid,String topicstatus){
        List<TopicAndStudent> topicAndStudentList = topicsMapper.selectJoinList(TopicAndStudent.class,
                new MPJLambdaWrapper<TopicAndStudent>()
                        .selectAll(Topics.class)
                        .selectAll(Students.class)
                        .rightJoin(Students.class,Students::getStudentid,Topics::getStudentid)
                        .eq(Topics::getTeacherid,teacherid)
                        .eq(Topics::getTopicstatus,topicstatus)
                        .isNotNull(Topics::getStudentid));

        return Result.succ(topicAndStudentList);
    }

//    老师发布新课题 或 学生上传
    @PostMapping("/addtopic")
    public Result addTopic (@RequestBody Topics topics){

        if (topicsSrevice.getById(topics) == null){
            if(topics.getStudentid() == null){
                topics.setTopicstatus("已通过");
                topics.setTopictype("老师发布");
            }
            else if (topics.getTeacherid()==null){
                return Result.fail("请先等待老师绑定");
            }else {
                topics.setTopictype("学生自拟");
                topics.setTopicstatus("未审核");
            }
            topicsSrevice.save(topics);
            return Result.succ("成功","");
        }
        return Result.fail("已有此课题");
    }

    @PostMapping("/updatatopic")
     public Result updataTopic(@RequestBody Topics topics){
        topicsSrevice.updateById(topics);
        return Result.succ("");
    }

//    删除课题
    @PostMapping("/deletetopic")
    public Result deletaTopic(@RequestBody Topics topics){
        topicsSrevice.removeById(topics);
        return Result.succ("删除成功","");
    }



}
