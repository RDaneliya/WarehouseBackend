ALTER TABLE commodity
    ADD CONSTRAINT uc_commodity_vendor_code UNIQUE (vendor_code);