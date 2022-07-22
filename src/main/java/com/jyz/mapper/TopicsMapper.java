package com.jyz.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.jyz.entity.TopicAndStudent;
import com.jyz.entity.Topics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jocky
 * @since 2022-04-08
 */
@Repository
public interface TopicsMapper extends MPJBaseMapper<Topics> {



}
