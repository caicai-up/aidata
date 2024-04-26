package com.weng.aidata.service.impl;

import com.weng.aidata.dao.PropertyDao;
import com.weng.aidata.entity.relation.Property;
import com.weng.aidata.service.PropertyService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyDao propertyDao;
    @Override
    public void save(Property property) {
        propertyDao.save(property);
    }
}