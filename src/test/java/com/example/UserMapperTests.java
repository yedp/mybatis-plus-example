package com.example;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.User;
import com.example.mapper.db1.UserMapper1;
import com.example.mapper.db2.UserMapper2;
import com.example.mapper.db3.UserMapper3;
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
public class UserMapperTests {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper1 userMapper1;
    @Resource
    private UserMapper2 userMapper2;
    @Resource
    private UserMapper3 userMapper3;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList1 = userMapper1.selectList(null);
        List<User> userList2 = userMapper2.selectList(null);
        List<User> userList3 = userMapper3.selectList(null);
        String result1 = JSON.toJSONString(userList1);
        String result2 = JSON.toJSONString(userList2);
        String result3 = JSON.toJSONString(userList3);
        logger.info("result1:" + result1);
        logger.info("result2:" + result2);
        logger.info("result3:" + result3);
    }
    @Test
    public void testDel() {
        System.out.println("----- 普通查询 ------");
        userMapper1.delete(new QueryWrapper<User>().eq("name", "ydp"));
        List<User> plainUsers = userMapper1.selectList(new QueryWrapper<User>().eq("name", "ydp"));
        logger.info("plainUsers:{}", plainUsers);
    }
}

