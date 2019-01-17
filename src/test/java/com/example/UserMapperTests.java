package com.example;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
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

    @Test
    public void testSelectByCondition() {
        System.out.println("----- 普通查询 ------");
        List<User> plainUsers = userMapper.selectList(new QueryWrapper<User>().eq("name", "ydp"));
        List<User> lambdaUsers = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getName, "ydp"));
        Assert.assertEquals(plainUsers.size(), lambdaUsers.size());
        logger.info("plainUsers:{},lambdaUsers:{}", plainUsers, lambdaUsers);

        System.out.println("----- 带子查询(sql注入) ------");
        List<User> plainUsers2 = userMapper.selectList(new QueryWrapper<User>().inSql("name", "select name from user where id = 1"));
        List<User> lambdaUsers2 = userMapper.selectList(new QueryWrapper<User>().lambda().inSql(User::getName, "select name from user where id = 1"));
        Assert.assertEquals(plainUsers2.size(), lambdaUsers2.size());
        logger.info("plainUsers2:{},lambdaUsers2:{}", plainUsers2, lambdaUsers2);

        System.out.println("----- 带嵌套查询 ------");
        List<User> plainUsers3 = userMapper.selectList(new QueryWrapper<User>()
                .nested(i -> i.eq("id", 1).or().eq("name", "ydp"))
                .and(i -> i.ge("age", 20)));
        List<User> lambdaUsers3 = userMapper.selectList(new QueryWrapper<User>().lambda()
                .nested(i -> i.eq(User::getId, 1).or().eq(User::getName, "ydp"))
                .and(i -> i.ge(User::getAge, 20)));
        Assert.assertEquals(plainUsers3.size(), lambdaUsers3.size());
        logger.info("plainUsers3:{},lambdaUsers3:{}", plainUsers3, lambdaUsers3);

        System.out.println("----- 自定义(sql注入) ------");
        List<User> plainUsers4 = userMapper.selectList(new QueryWrapper<User>()
                .apply("id = 2"));
        logger.info("plainUsers4:{}", plainUsers4);

        System.out.println("----- 更新数据 ------");
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.set("email", "");
        uw.eq("id", 4);
        userMapper.update(new User(), uw);
        User u4 = userMapper.selectById(4);
        Assert.assertEquals(u4.getEmail(),"");
        logger.info("u4:{}", u4);
    }

    @Test
    public void testDel() {
        System.out.println("----- 普通查询 ------");
        userMapper.delete(new QueryWrapper<User>().eq("name", "ydp"));
        List<User> plainUsers = userMapper.selectList(new QueryWrapper<User>().eq("name", "ydp"));
        logger.info("plainUsers:{}", plainUsers);
    }
}

