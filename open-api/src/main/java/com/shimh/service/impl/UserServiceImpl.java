package com.shimh.service.impl;

import com.shimh.common.base.service.BaseServiceImpl;
import com.shimh.entity.User;
import com.shimh.repository.UserRepository;
import com.shimh.security.SecurityUtil;
import com.shimh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Service
public class UserServiceImpl  extends BaseServiceImpl<User, Integer, UserRepository> implements UserService {

    @Override
    public User getByUsername(String username) {
        return repository.getByUsername(username);
    }

    @Override
    public boolean checkPassword(String password) {
        User user = SecurityUtil.getCurrentUser();
        boolean wrong_password = true;
        if(user.getPassword().equals(SecurityUtil.getEncryptPassword(password, user.getSalt()))) {
            wrong_password = false;
        }
        return wrong_password;
    }

    @Override
    @Transactional
    public void resetPassword(String username, String new_password) {
        User saved_user = getByUsername(username);
        saved_user.setPassword(new_password);
        SecurityUtil.encryptPassword(saved_user);
        save(saved_user);
    }

    @Override
    public List<User> listUsers(String username) {
        if(null != username && !"".equals(username)) {
            return repository.getByUsernameLike( "%" + username + "%");
        } else {
            return repository.findAll();
        }
    }

    @Override
    @Transactional
    public User add(User user) {
        SecurityUtil.encryptPassword(user);
        user.setCreateDate(new Date());
        user.setAdmin(false);
        user.setIs_del(0);
        return save(user);
    }
}
