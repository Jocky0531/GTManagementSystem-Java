package com.jyz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jocky
 * @since 2022-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Papers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "paperid", type = IdType.AUTO)
    private Integer paperid;

    private String filename;

    private String teachermsg;

    private LocalDateTime date;

    private String fileurl;

    private String studentid;

    private String teacherid;



}
