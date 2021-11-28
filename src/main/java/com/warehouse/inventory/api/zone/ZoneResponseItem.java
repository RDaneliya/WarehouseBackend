package com.warehouse.inventory.api.zone;

import com.warehouse.inventory.api.commodity.CommodityResponseItem;
import lombok.Data;

@Data
public class ZoneResponseItem {
    private Long id;
    private CommodityResponseItem commodity;
    private Long amount;
    private Long row;
    private Long column;
}
