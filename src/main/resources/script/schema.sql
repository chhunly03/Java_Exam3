-- i use postgressql

CREATE TABLE product(
                        id Int,
                        name VARCHAR(255),
                        price_per_unit DECIMAL(10, 2),
                        active_for_sell BOOLEAN
);