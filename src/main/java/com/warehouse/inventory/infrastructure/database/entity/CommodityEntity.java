package com.warehouse.inventory.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(
    name = "commodity", indexes = {
    @Index(name = "idx_commodity_vendor", columnList = "vendor_code"),
    @Index(name = "idx_commodity_name", columnList = "name")
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommodityEntity {
    @Id
    @Column(name = "vendor_code", nullable = false, unique = true)
    private String vendorCode;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "commodity")
    private List<WaybillCommodityEntity> waybillEntity = new ArrayList<>();

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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "vendorCode = " + vendorCode + ", " +
            "name = " + name + ")";
    }
}