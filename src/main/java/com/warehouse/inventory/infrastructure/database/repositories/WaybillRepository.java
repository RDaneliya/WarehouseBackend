package com.warehouse.inventory.infrastructure.database.repositories;

import com.warehouse.inventory.infrastructure.database.entity.WaybillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaybillRepository extends JpaRepository<WaybillEntity, Long> {
}