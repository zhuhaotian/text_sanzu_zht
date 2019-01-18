package com.jk.service;

import com.jk.bean.UserBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("founder")
public interface LoginService {

    @RequestMapping("loginUser")
    UserBean login(UserBean userBean);
}
