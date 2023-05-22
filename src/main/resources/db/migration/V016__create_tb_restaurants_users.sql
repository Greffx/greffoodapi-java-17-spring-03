CREATE TABLE tb_restaurants_users (
    restaurant_id BIGINT NOT NULL,
    user_id       BIGINT NOT NULL,
    CONSTRAINT pk_tb_restaurants_users PRIMARY KEY (restaurant_id, user_id)
);

ALTER TABLE tb_restaurants_users
    ADD CONSTRAINT fk_tb_restaurant_users_restaurants FOREIGN KEY (restaurant_id) REFERENCES tb_restaurants (id);

ALTER TABLE tb_restaurants_users
    ADD CONSTRAINT fk_tb_restaurant_users_users FOREIGN KEY (user_id) REFERENCES tb_users (id);