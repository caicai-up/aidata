package com.weng.aidata.dao;

import com.weng.aidata.entity.relation.Item;
import com.weng.aidata.entity.relation.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

@Resource
public interface PropertyDao extends JpaRepository<Property, String> {
}
