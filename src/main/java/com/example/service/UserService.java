package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.mapper.db1.UserMapper1;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper1, User> {

    /**
     * 通过姓名获取一条数据
     *
     * @param name
     * @return
     */
    public User getOneUserByName(String name) {
        User user = null;
        //条件查询条件封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        if (name != null) {
            queryWrapper.lambda().eq(User::getUserName, name);
        }
        /**
         * 不过满足条件多少数据，取第一条数据
         */
        user = getOne(queryWrapper, false);
//        user = getOne(queryWrapper);//与user = getOne(queryWrapper, false);一致
//        /**
//         * 获取满足条的一条数据，满足条件的数据量 >= 2,则抛异常
//         */
//        user = getOne(queryWrapper, true);
        return user;
    }

    public IPage<User> getPageByAddTime(Date addTimeBegin, Date addTimeEnd, Integer currentPage, Integer pageSize) {
        Page<User> page = new Page<>(currentPage, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        if (addTimeBegin != null) {
            queryWrapper.lambda().ge(User::getAddTime, addTimeBegin);
        }
        if (addTimeEnd != null) {
            queryWrapper.lambda().le(User::getAddTime, addTimeEnd);
        }

        return baseMapper.selectPage(page, queryWrapper);
    }

    public List<User> getUserListByRoleId(String roleId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        String inSql = String.format("select user_id from user_role where role_id = '%s'", roleId);
        queryWrapper.lambda().inSql(User::getUserId, inSql);
        return baseMapper.selectList(queryWrapper);
    }

    public List<User> getUserListByUserIdList(List<String> userIdList) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda().in(User::getUserId, userIdList);
        return baseMapper.selectList(queryWrapper);
    }

}
