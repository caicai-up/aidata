package com.weng.aidata.entity.relation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "property")
@Data
public class Property {
    @Id
    private String id;
    private String labels;
    private String description;
    private String aliases;
}