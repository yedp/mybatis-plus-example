package com.example;

import com.alibaba.fastjson.JSON;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTests {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertNotNull(userList);
        String result = JSON.toJSONString(userList);
        logger.info(result);
    }

}

