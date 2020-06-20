package com.baizhi.controller;

import com.baizhi.pojo.User;
import com.baizhi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("user")
public class UserController {

    public  static final Logger logger =LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public String login(String username,String password){

        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录
            subject.login(new UsernamePasswordToken(username,password));

            return "redirect:/index.jsp";

        }catch (UnknownAccountException e){
            e.printStackTrace();
            logger.info("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            logger.info("密码错误");
        }


        return "redirect:/login.jsp";

    }

    /**
     * 登出用户
     * @return
     */
    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        //调用登出方法
        subject.logout();
        return "redirect:/login.jsp";
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping("register")
    public String register(User user){

        try {
            //注册
            userService.insertUser(user);
            return "redirect:/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/register.jsp";

    }


}
