package com.warehouse.inventory.api.counterparty;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class CounterpartyRequest {
    @NonNull
    private final String name;
}
