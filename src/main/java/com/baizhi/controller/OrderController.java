package com.baizhi.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {



    @RequestMapping("find")
    @RequiresRoles("admin")  //用于设置访问权限
    public String find(){

        System.out.println("访问");
//        //获取主体对象
//        Subject subject = SecurityUtils.getSubject();
//        if(subject.hasRole("admin")){
//            System.out.println("有权访问");
//        }else {
//            System.out.println("无权访问");
//        }


        return "redirect:/index.jsp";
    }
}
