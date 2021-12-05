package com.warehouse.inventory.api.waybill;

import lombok.Data;

@Data
public class WaybillCommodityRequestItem {
    private final String name;
    private final Long amount;
}
