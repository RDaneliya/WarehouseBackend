package com.warehouse.inventory.api.waybill;

import lombok.Data;

@Data
public class WaybillCommodityResponseItem {
    private String vendorCode;
    private Long amount;
}
