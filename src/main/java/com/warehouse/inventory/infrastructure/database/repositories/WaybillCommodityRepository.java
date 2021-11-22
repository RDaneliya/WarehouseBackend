package com.warehouse.inventory.infrastructure.database.repositories;

import com.warehouse.inventory.infrastructure.database.entity.WaybillCommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaybillCommodityRepository extends JpaRepository<WaybillCommodityEntity, Long> {
}