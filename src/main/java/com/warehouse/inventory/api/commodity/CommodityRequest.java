package com.warehouse.inventory.api.commodity;

import lombok.Data;

@Data
public class CommodityRequest {
    private final String vendorCode;
    private final String name;
}
