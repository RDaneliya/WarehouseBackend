package com.warehouse.inventory.api.waybill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaybillResponseItem {
    private Long id;
    private String type;
    private String counterparty;
    private List<WaybillCommodityResponseItem> commodities;
    private Timestamp createdAt;
}
