package com.jk.controller;

import com.jk.bean.UserBean;
import com.jk.service.LoginService;
import com.jk.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.remPwd.equals(cookie.getName())) {
                    String msg = cookie.getValue();
                    String[] userAndPwd = msg.split(Constant.splitChar);
                    model.addAttribute("name", userAndPwd[0]);
                    model.addAttribute("pwd", userAndPwd[1]);
                }
            }
        }
        return "inquiryLogin";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(UserBean userBean, HttpSession session, ModelMap model, HttpServletResponse response){
        UserBean log = loginService.login(userBean);
        if(log != null){
            session.setAttribute("log",log);
            if (userBean.getCheck()!=null){
                Cookie nameCookie = new Cookie(Constant.remPwd,userBean.getName()+Constant.splitChar+userBean.getPwd());
                //当前应用
                nameCookie.setPath("/");
                //设置过期时间
                nameCookie.setMaxAge(6048000);
                response.addCookie(nameCookie);
            }else {
                //没有勾选-->清除密码
                Cookie cookie = new Cookie(Constant.remPwd, "");
                //maxAge=0会删除cookie
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            return "1";
        }else {
            model.addAttribute("msg","用户名或密码错误");
            return "0";
        }

    }

}
