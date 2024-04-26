package com.weng.aidata.service.impl;

import com.weng.aidata.dao.IPropertyDao;
import com.weng.aidata.entity.relation.IProperty;
import com.weng.aidata.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IPropertyServiceImpl implements IPropertyService {
    @Autowired
    private IPropertyDao iPropertyDao;
    @Override
    public void save(IProperty iProperty) {
        iPropertyDao.save(iProperty);
    }
}
