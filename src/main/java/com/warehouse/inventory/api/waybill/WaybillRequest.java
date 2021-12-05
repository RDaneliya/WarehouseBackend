package com.warehouse.inventory.api.waybill;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class WaybillRequest {
    private final String type;
    private final String counterparty;
    private final List<WaybillCommodityRequestItem> commodities;
    private final Timestamp createdAt;
}
