package com.example;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.User;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * UserService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void getById() {
        User user = userService.getById(1);
        System.out.println("user:" + JSON.toJSONString(user));
    }

    @Test
    public void getPageByName() {
        //如果条件查询到>=2条数据，则取第一条
        User user = userService.getOneUserByName("ydp");
        System.out.println("user:" + JSON.toJSONString(user));
    }

    @Test
    public void getPageByAddTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 0, 15);
        Date addTimeBegin = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.set(2019, 0, 17);
        Date addTimeEnd = calendar.getTime();
        //如果条件查询到>=2条数据，则取第一条
        IPage<User> userPage = userService.getPageByAddTime(addTimeBegin, addTimeEnd, 1, 3);
        System.out.println("user:" + JSON.toJSONString(userPage) + ", addTimeBegin:" + sdf.format(addTimeBegin) + ", addTimeEnd:" + sdf.format(addTimeEnd));
    }

    @Test
    public void getByRoleId() {
        List<User> userList = userService.getUserListByRoleId("r1");
        System.out.println("userList:" + JSON.toJSONString(userList));
    }

    @Test
    public void getUserListByUserIdList() {
        List<String> userIdList = new ArrayList();
        userIdList.add("u1");
        userIdList.add("u3");
        List<User> userList = userService.getUserListByUserIdList(userIdList);
        userList.forEach(System.out::println);

    }

}
