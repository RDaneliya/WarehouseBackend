package com.warehouse.inventory.infrastructure.database.repositories;

import com.warehouse.inventory.infrastructure.database.entity.CommodityEntity;
import com.warehouse.inventory.infrastructure.database.entity.ZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ZoneRepository extends JpaRepository<ZoneEntity, Long> {
    ZoneEntity findByCommodityEntityType(CommodityEntity commodityEntity);

    ZoneEntity getZoneEntityById(@NonNull Long id);
}