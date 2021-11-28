package com.warehouse.inventory.api.zone;

import lombok.Data;

@Data
public class ZoneRequest {
    private final String commodityVendorCode;
    private final Long amount;
    private final Long row;
    private final Long column;
}
