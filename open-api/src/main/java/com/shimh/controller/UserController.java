package com.shimh.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.base.Result;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.entity.User;
import com.shimh.security.SecurityUtil;
import com.shimh.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/listUsers")
    @RequiresRoles(Base.ADMIN_ROLE_CODE)
    public Result listUsers(String username) {
        List<User> users = userService.listUsers(username);
        return Result.success(users);
    }

    @PostMapping("/add")
    @RequiresRoles(Base.ADMIN_ROLE_CODE)
    public Result add(User user) {

        User old_user = userService.getByUsername(user.getUsername());
        if(null != old_user) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }
        User new_user = userService.add(user);

        return Result.success();
    }

    @PostMapping("/delete")
    @RequiresRoles(Base.ADMIN_ROLE_CODE)
    public Result delete(Integer id) {

        userService.deleteByIsDelete("sys_user", "id", id);

        return Result.success();
    }

    @GetMapping("/info")
//    @FastJsonView(
//            include = {@FastJsonFilter(clazz = User.class, props = {"id", "account", "nickname", "avatar"})})
    public Result info() {

        Result r = new Result();

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(SecurityUtil.getCurrentUser());
        return r;
    }

    @PostMapping("/resetPassword")
    @RequiresAuthentication
    public Result resetPassword(String new_password, String password) {
        boolean wrong_password = userService.checkPassword(password);
        if(wrong_password) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }
        User user = SecurityUtil.getCurrentUser();
        userService.resetPassword(user.getUsername(), new_password);
        return Result.success();
    }

    @PostMapping("/changePassword")
    @RequiresRoles(Base.ADMIN_ROLE_CODE)
    public Result changePassword(String username, String password) {
        userService.resetPassword(username, password);
        return Result.success();
    }

}
