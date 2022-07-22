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
public class Teachers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("teacherid")
    private String teacherid;

    private String password;

    private String username;

    private String teachertitle;

    private String teachertelephone;

    private String address;


}
