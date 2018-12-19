package com.shimh.repository;

import com.shimh.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface PageRepository extends JpaRepository<Page, Integer> {
}
