package com.weng.aidata.service.impl;

import com.weng.aidata.dao.PPropertyDao;
import com.weng.aidata.entity.relation.PProperty;
import com.weng.aidata.service.PPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PPropertyServiceImpl implements PPropertyService {
    @Autowired
    private PPropertyDao pPropertyDao;
    @Override
    public void save(PProperty pProperty) {
        pPropertyDao.save(pProperty);
    }
}
