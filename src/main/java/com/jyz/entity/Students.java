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
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("studentid")
    private String studentid;

    private String password;

    private String username;

    private String department;

    private String specialty;

    private String studentclass;

    private String studenttelephone;

    private String teacherid;


}
