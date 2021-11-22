package com.warehouse.inventory.api.commodity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityResponseItem {
    private Long vendorCode;
    private String name;
}
