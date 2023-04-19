CREATE TABLE tb_order_items
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    quantity    SMALLINT(6)           NOT NULL,
    unity_price DECIMAL(9, 2)         NOT NULL,
    total_price DECIMAL(9, 2)         NOT NULL,
    observation VARCHAR(255),
    order_id    BIGINT                NOT NULL,
    product_id  BIGINT                NOT NULL,

    CONSTRAINT pk_tb_orders_items PRIMARY KEY (id)
);

ALTER TABLE tb_order_items
    ADD CONSTRAINT fk_tb_orders_items_products FOREIGN KEY (product_id) REFERENCES tb_products (id);

#ADD CONSTRAINT is like adding a nickname, FOREIGN KEY is referencing property name in <tableName> to be a foreign key and making reference
# to another table id column

#CONSTRAINT in primary ket is the same thing, just a nike name, a index name
#when we want to create a FK(foreign key) need to create property at table, with bigint to be a legit idz