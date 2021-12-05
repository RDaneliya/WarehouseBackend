ALTER TABLE waybill
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;

CREATE INDEX idx_waybill_counterparty ON waybill (counterparty);

CREATE INDEX idx_waybill_type ON waybill (type);

CREATE INDEX idx_zone_commodity_type ON zone (commodity_type);

ALTER TABLE zone
    ALTER COLUMN commodity_type TYPE VARCHAR(255) USING (commodity_type::VARCHAR(255));

ALTER TABLE commodity
    ALTER COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));

ALTER TABLE counterparty
    ALTER COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));

ALTER TABLE waybill
    ALTER COLUMN type TYPE VARCHAR(255) USING (type::VARCHAR(255));

ALTER TABLE commodity
    ALTER COLUMN vendor_code TYPE VARCHAR(255) USING (vendor_code::VARCHAR(255));

ALTER TABLE waybill_commodity
    ALTER COLUMN vendor_code TYPE VARCHAR(255) USING (vendor_code::VARCHAR(255));

CREATE INDEX idx_commodity_name ON commodity (name);