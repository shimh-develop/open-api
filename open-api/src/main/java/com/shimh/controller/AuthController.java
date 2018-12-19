package com.shimh.controller;

import com.shimh.common.base.Result;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.redis.RedisUtil;
import com.shimh.config.AppProperties;
import com.shimh.entity.User;
import com.shimh.security.JwtFactory;
import com.shimh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFactory jwtFactory;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public Result login(User user) {

        User userDetail = userService.getByUsername(user.getUsername());

        String token = jwtFactory.generateToken(user);

        // 放到redis  key: token   value: 用户名
        redisUtil.set(token, user.getUsername(), appProperties.getSecurity().getTimeout());

        Result r = Result.success();
        r.simple().put(Base.TOKEN_NAME, token);

        return r;
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        Result r = new Result();
        String token = request.getHeader(appProperties.getSecurity().getJwt().getHeader());
        redisUtil.del(token);

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }


}
