package com.weng.aidata.dao;

import com.weng.aidata.entity.relation.PProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

@Resource
public interface PPropertyDao extends JpaRepository<PProperty, Long> {
}
