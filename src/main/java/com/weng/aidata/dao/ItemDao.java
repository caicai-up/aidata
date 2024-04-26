package com.weng.aidata.dao;

import com.weng.aidata.entity.relation.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

@Resource
public interface ItemDao extends JpaRepository<Item, String> {
}
