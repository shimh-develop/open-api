package com.shimh.repository;

import com.shimh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUsername(String username);

    List<User> getByUsernameLike(String username);
}
