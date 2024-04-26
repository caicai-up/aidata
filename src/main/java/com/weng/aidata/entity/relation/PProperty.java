package com.weng.aidata.entity.relation;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "property_property")
@Data
public class PProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propertyId;
    private String belongPropertyId;
    private String dataType;
    private String dataValue;
}
