package com.jyz;

import net.sf.jsqlparser.statement.select.Top;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.jyz.entity.Students;
import com.jyz.entity.TopicAndStudent;
import com.jyz.entity.Topics;
import com.jyz.mapper.TopicsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
class PaperJavaApplicationTests {
    @Autowired
    TopicsMapper topicsMapper;

    @Test
    public void contextLoads() {

    }

}
