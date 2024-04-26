package com.weng.aidata.service.impl;

import com.weng.aidata.dao.ItemDao;
import com.weng.aidata.entity.relation.Item;
import com.weng.aidata.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public void save(Item item) {
        itemDao.save(item);
    }
}
