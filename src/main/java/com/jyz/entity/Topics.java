package com.jyz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jocky
 * @since 2022-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Topics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课题名
     */
    @TableId("topictitle")
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


}
