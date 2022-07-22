package com.jyz.service.impl;

import com.jyz.entity.Students;
import com.jyz.mapper.StudentsMapper;
import com.jyz.service.StudentsSrevice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jocky
 * @since 2022-04-08
 */
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements StudentsSrevice {

}
