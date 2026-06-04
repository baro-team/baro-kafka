CREATE TABLE IF NOT EXISTS vehicle_data (
    timestamp   TIMESTAMPTZ       NOT NULL,
    car_id      BIGINT            NOT NULL,
    latitude    FLOAT             NOT NULL,
    longitude   FLOAT             NOT NULL,
    speed       INT               NOT NULL,
    battery     INT               NOT NULL,
    status      VARCHAR(50),
    heading     FLOAT,
    tire_pressure  JSONB,
    engine_oil     FLOAT,
    brake_oil      FLOAT,
    washer_fluid   FLOAT,
    ext_temp       FLOAT
);

SELECT create_hypertable('vehicle_data', 'timestamp', if_not_exists => TRUE);
