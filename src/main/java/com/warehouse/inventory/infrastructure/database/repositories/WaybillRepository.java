package com.warehouse.inventory.infrastructure.database.repositories;

import com.warehouse.inventory.infrastructure.database.entity.WaybillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;

public interface WaybillRepository extends JpaRepository<WaybillEntity, Long> {
    WaybillEntity getByCreatedAt(@NonNull Timestamp date);
}