package com.warehouse.inventory.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Objects;

@Table(
    name = "commodity",
    indexes = @Index(name = "commodity_name_key", columnList = "name", unique = true)
)
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommodityEntity {
    @Id
    @Column(name = "vendor_code", nullable = false, unique = true)

    private Long vendorCode;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommodityEntity that = (CommodityEntity) o;
        return vendorCode != null && Objects.equals(vendorCode, that.vendorCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}