package com.warehouse.inventory.extensions.mappers;

import com.warehouse.inventory.api.counterparty.CounterpartyRequest;
import com.warehouse.inventory.api.counterparty.CounterpartyResponseItem;
import com.warehouse.inventory.infrastructure.database.entity.CounterpartyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CounterpartyMapper {
    CounterpartyResponseItem toResponseItem(CounterpartyEntity entity);

    @Mapping(target = "id", ignore = true)
    CounterpartyEntity toEntity(CounterpartyRequest request);
}
