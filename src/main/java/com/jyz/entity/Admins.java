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
public class Admins implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("adminid")
    private String adminid;

    private String password;

    private String username;


}
