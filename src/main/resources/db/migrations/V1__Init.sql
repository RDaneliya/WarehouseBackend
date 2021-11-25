CREATE TABLE commodity
(
    vendor_code VARCHAR PRIMARY KEY,
    name        VARCHAR NOT NULL UNIQUE
);

CREATE TABLE zone
(
    id             BIGSERIAL PRIMARY KEY,
    commodity_type VARCHAR,
    amount         BIGINT,
    row            BIGINT,
    "column"       BIGINT,
    FOREIGN KEY (commodity_type) REFERENCES commodity (vendor_code)
);

CREATE TABLE counterparty
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE waybill
(
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR,
    counterparty BIGINT,
    FOREIGN KEY (counterparty) REFERENCES counterparty(id)
);

CREATE TABLE waybill_commodity
(
    id          BIGSERIAL PRIMARY KEY,
    vendor_code VARCHAR NOT NULL,
    waybill_id  BIGINT  NOT NULL,
    amount      BIGINT  NOT NULL,
    FOREIGN KEY (vendor_code) REFERENCES commodity (vendor_code),
    FOREIGN KEY (waybill_id) REFERENCES waybill (id)
)