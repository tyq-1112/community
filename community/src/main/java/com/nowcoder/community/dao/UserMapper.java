package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    //根据Id查询用户
    User selectById(int id);

    //根据用户名查询用户
    User selectByName(String username);

    //根据邮箱查询用户
    User selectByEmail(String email);

    //增加一个用户
    int insertUser(User user);

    //更新用户状态
    int updateStatus(int id , int status);

    //更新头像路径
    int updateHeader(int id , String headerUrl);

    //更新密码
    int updatePassword(int id , String password);
}
