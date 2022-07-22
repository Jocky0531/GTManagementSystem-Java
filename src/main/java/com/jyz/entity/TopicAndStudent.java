package com.jyz.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TopicAndStudent {

    private String topictitle;

    /**
     * 课题类型（老师发布/学生自拟）
     */
    private String topictype;

    /**
     * 课题描述
     */
    private String topicdepict;

    /**
     * 课题状态（已审核/未审核）
     */
    private String topicstatus;

    /**
     * 选择此课题的学生id
     */
    private String studentid;

    /**
     * 管理此课题的老师
     */
    private String teacherid;


    private String username;

    private String department;

    private String specialty;

    private String studentclass;

    private String studenttelephone;

}
