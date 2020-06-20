package com.baizhi.dao;

import com.baizhi.pojo.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    User findByRoleName(String username);
}
