package com.weng.aidata.entity.relation;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item_property")
@Data
public class IProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemId;
    private String propertyId;
    private String dataType;
    private String dataValue;
}
