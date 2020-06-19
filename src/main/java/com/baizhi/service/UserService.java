package com.baizhi.service;

import com.baizhi.dao.UserMapper;
import com.baizhi.pojo.User;
import com.baizhi.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 注册用户
     * @param user
     */
    public void insertUser(User user) {

        //根据随机盐工具类生成
        String salt = SaltUtils.getSalt(8);
        //将随机盐添加到user
        user.setSalt(salt);
        //进行MD5加密
        Md5Hash md5Hash=new Md5Hash(user.getPassword(),salt,1024);
        //将加密的密码
        user.setPassword(md5Hash.toHex());

        userMapper.insert(user);

    }


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User findUserByUserName(String username){
        User user=new User();
        user.setUsername(username);
      return  userMapper.selectOne(user);
    }
}
