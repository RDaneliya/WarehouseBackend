package com.warehouse.inventory.infrastructure.database.repositories;

import com.warehouse.inventory.infrastructure.database.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CommodityRepository extends JpaRepository<CommodityEntity, String> {
    List<CommodityEntity> findByNameContaining(String substring);

    List<CommodityEntity> findByName(String name);

    CommodityEntity getByVendorCode(@NonNull String id);

}