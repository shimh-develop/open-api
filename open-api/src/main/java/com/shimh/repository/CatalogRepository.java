package com.shimh.repository;

import com.shimh.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
