CREATE TABLE tb_orders
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    subtotal_per_item   DECIMAL(9, 2)         NOT NULL,
    delivery_tax        DECIMAL(9, 2)         NOT NULL,
    total               DECIMAL(9, 2)         NOT NULL,
    creation_date       DATETIME              NOT NULL,
    confirmed_date      DATETIME,
    canceled_date       DATETIME,
    delivered_date      DATETIME,
    status              VARCHAR(10)           NOT NULL,
    restaurant_id       BIGINT                NOT NULL,
    payment_method_id   BIGINT                NOT NULL,
    user_id             BIGINT                NOT NULL,
    city_address_id     BIGINT                NOT NULL,
    cep_address         VARCHAR(8),
    complement_address  VARCHAR(255),
    number_address      VARCHAR(20),
    public_area_address VARCHAR(255),
    street_address      VARCHAR(80),

    CONSTRAINT pk_tb_orders PRIMARY KEY (id)
);
ALTER TABLE tb_orders
    ADD CONSTRAINT fk_tb_orders_restaurants FOREIGN KEY (restaurant_id) REFERENCES tb_restaurants (id);

ALTER TABLE tb_orders
    ADD CONSTRAINT fk_tb_orders_payment_methods FOREIGN KEY (payment_method_id) REFERENCES tb_payment_methods (id);

ALTER TABLE tb_orders
    ADD CONSTRAINT fk_tb_orders_users FOREIGN KEY (user_id) REFERENCES tb_users (id);

ALTER TABLE tb_orders
    ADD CONSTRAINT fk_tb_orders_cities_address FOREIGN KEY (city_address_id) REFERENCES tb_cities (id);

#ADD CONSTRAINT is like adding a nickname, FOREIGN KEY is referencing property name in <tableName> to be a foreign key and making reference
# to another table id column

#CONSTRAINT in primary ket is the same thing, just a nike name, a index name
#when we want to create a FK(foreign key) need to create property at table, with bigint to be a legit idz