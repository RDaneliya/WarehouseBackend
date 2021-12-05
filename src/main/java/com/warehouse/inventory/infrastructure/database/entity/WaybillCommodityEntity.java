package com.warehouse.inventory.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "waybill_commodity")
@Entity
@Getter
@Setter
@ToString(exclude = {"commodity", "waybill"})
@AllArgsConstructor
@NoArgsConstructor
public class WaybillCommodityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "vendor_code")
    private CommodityEntity commodity;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "waybill_id")
    private WaybillEntity waybill;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WaybillCommodityEntity that = (WaybillCommodityEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}