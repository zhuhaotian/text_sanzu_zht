package com.jk.controller;

import com.jk.bean.UserBean;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("userQue")
    @ResponseBody
    public List<UserBean> userQuery(UserBean userBean){

        System.out.println(userService.userQuery(userBean));
        return userService.userQuery(userBean);
    }
    @RequestMapping("toList")
    public String toList(){

        return "list";
    }
}
