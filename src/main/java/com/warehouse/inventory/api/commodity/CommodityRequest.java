package com.warehouse.inventory.api.commodity;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class CommodityRequest {
    @NonNull
    @NotBlank
    private final String vendorCode;
    @NonNull
    @NotBlank
    private final String name;
}
