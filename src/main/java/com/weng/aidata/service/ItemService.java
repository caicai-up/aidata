package com.weng.aidata.service;

import com.weng.aidata.entity.relation.Item;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public interface ItemService {
    void save(Item item);
}
