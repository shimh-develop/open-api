package com.shimh.service;

import com.shimh.common.base.service.BaseService;
import com.shimh.entity.User;
import com.shimh.repository.UserRepository;

import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface UserService extends BaseService<User, Integer, UserRepository> {
    User getByUsername(String username);

    boolean checkPassword(String password);

    void resetPassword(String username, String new_password);

    List<User> listUsers(String username);

    User add(User user);
}
