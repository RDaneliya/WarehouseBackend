package com.warehouse.inventory.extensions.mappers;

import com.warehouse.inventory.api.counterparty.CounterpartyRequest;
import com.warehouse.inventory.api.counterparty.CounterpartyResponseItem;
import com.warehouse.inventory.infrastructure.database.entity.CounterpartyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CounterpartyMapper {
    CounterpartyResponseItem toResponseItem(CounterpartyEntity entity);

    CounterpartyEntity toEntity(CounterpartyRequest request);
}
