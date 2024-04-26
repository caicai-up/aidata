package com.weng.aidata.dao;

import com.weng.aidata.entity.relation.IProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

@Resource
public interface IPropertyDao extends JpaRepository<IProperty, Long> {
}
