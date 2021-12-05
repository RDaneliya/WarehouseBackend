package com.warehouse.inventory.infrastructure.database.repositories;

import com.warehouse.inventory.infrastructure.database.entity.CounterpartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface CounterpartyRepository extends JpaRepository<CounterpartyEntity, Long> {
    CounterpartyEntity getCounterpartyEntityById(@NonNull Long id);

    CounterpartyEntity findCounterpartyEntityByName(String name);

    boolean existsByName(String name);
}