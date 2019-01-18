package com.jk.service;

import com.jk.bean.UserBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@FeignClient("founder")
public interface UserService {
    @RequestMapping("userQuery")
    List<UserBean> userQuery(UserBean userBean);
}
