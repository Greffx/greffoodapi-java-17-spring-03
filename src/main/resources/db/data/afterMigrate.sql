SET foreign_key_checks = 0;

DELETE
FROM tb_cities;
DELETE
FROM tb_states;
DELETE
FROM tb_kitchens;
DELETE
FROM tb_users_groups;
DELETE
FROM tb_groups_permissions;
DELETE
FROM tb_groups;
DELETE
FROM tb_users;
DELETE
FROM tb_payment_methods;
DELETE
FROM tb_permissions;
DELETE
FROM tb_products;
DELETE
FROM tb_restaurants;
DELETE
FROM tb_restaurants__payment_methods;
DELETE
FROM tb_restaurants_users;
DELETE
FROM tb_orders;
DELETE
FROM tb_order_items;

SET foreign_key_checks = 1;

ALTER TABLE tb_cities
    AUTO_INCREMENT = 1;
ALTER TABLE tb_states
    AUTO_INCREMENT = 1;
ALTER TABLE tb_kitchens
    AUTO_INCREMENT = 1;
ALTER TABLE tb_groups
    AUTO_INCREMENT = 1;
ALTER TABLE tb_users
    AUTO_INCREMENT = 1;
ALTER TABLE tb_payment_methods
    AUTO_INCREMENT = 1;
ALTER TABLE tb_permissions
    AUTO_INCREMENT = 1;
ALTER TABLE tb_products
    AUTO_INCREMENT = 1;
ALTER TABLE tb_restaurants
    AUTO_INCREMENT = 1;
ALTER TABLE tb_orders
    AUTO_INCREMENT = 1;
ALTER TABLE tb_order_items
    AUTO_INCREMENT = 1;


INSERT IGNORE INTO tb_kitchens (id, name)
VALUES (1, 'Indian');
INSERT IGNORE INTO tb_kitchens (id, name)
VALUES (2, 'Japanese');
INSERT IGNORE INTO tb_kitchens (id, name)
VALUES (3, 'Brazilian');

INSERT INTO tb_states (id, name)
VALUES (1, 'RS');
INSERT INTO tb_states (id, name)
VALUES (2, 'PR');

INSERT INTO tb_cities (name, state_id)
VALUES ('Porto Alegre', 1);
INSERT INTO tb_cities (name, state_id)
VALUES ('Curitiba', 2);
INSERT INTO tb_cities (name, state_id)
VALUES ('Viamão', 1);
INSERT INTO tb_cities (name, state_id)
VALUES ('Campo Largo', 2);

INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, cep_address, public_area_address,
                            number_address, complement_address, street_address, city_address_id, creation_date,
                            update_date, active, open)
VALUES ('Ganesh_Fast_Food', 3.50, 1, '12345999', 'Near to the elephant', '95', 'center of india', 'cow street', 4,
        utc_timestamp, utc_timestamp, true, true);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date, active, open)
VALUES ('Haiku_Tales', 5.60, 2, utc_timestamp, utc_timestamp, true, true);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date, active, open)
VALUES ('SamuraiSushi', 2.50, 2, utc_timestamp, utc_timestamp, true, true);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date, active, open)
VALUES ('Indian_Hero', 3.20, 1, utc_timestamp, utc_timestamp, true, true);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date, active, open)
VALUES ('Speed Galeto BR', 0, 3, utc_timestamp, utc_timestamp, true, true);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date, active, open)
VALUES ('Marmita Do Zé', 0, 3, utc_timestamp, utc_timestamp, true, true);

INSERT INTO tb_payment_methods (id, description)
VALUES (1, 'cash');
INSERT INTO tb_payment_methods (id, description)
VALUES (2, 'credit card');
INSERT INTO tb_payment_methods (id, description)
VALUES (3, 'debit card');

INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (1, 1);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (1, 2);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (1, 3);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (2, 1);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (2, 2);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (3, 2);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (3, 3);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (4, 1);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (5, 2);
INSERT INTO tb_restaurants__payment_methods (restaurant_id, payment_method_id)
VALUES (6, 1);

INSERT INTO tb_products (active, description, name, price, restaurant_id)
VALUES (true, 'goes with a delicious cream', 'Salad', 15, 1);
INSERT INTO tb_products (active, description, name, price, restaurant_id)
VALUES (true, 'goes with a delicious ham', 'Big chew stuff', 15, 1);
INSERT INTO tb_products (active, description, name, price, restaurant_id)
VALUES (true, 'hottest curry around the block', 'Curry', 15, 1);
INSERT INTO tb_products (active, description, name, price, restaurant_id)
VALUES (true, 'goes with a soda and fries', 'Vegan Burger', 45.20, 3);
INSERT INTO tb_products (active, description, name, price, restaurant_id)
VALUES (true, 'Most fast and worm food piece', 'Pizza slice', 7.45, 2);
INSERT INTO tb_products (active, description, name, price, restaurant_id)
VALUES (false, 'test to not active product', 'stuff', 7.45, 4);

INSERT INTO tb_permissions (id, name, description)
VALUES (1, 'CONSULT_KITCHENS', 'permission to consult kitchens');
INSERT INTO tb_permissions (id, name, description)
VALUES (2, 'EDIT_KITCHENS', 'permission to edit kitchens');
INSERT INTO tb_permissions (id, name, description)
VALUES (3, 'CONSULT_RESTAURANTS', 'permission to consult restaurants');

INSERT INTO tb_users(email, name, password, register_date)
VALUES ('bob23@gmail.com', 'bob', '1234', utc_timestamp);
INSERT INTO tb_users(email, name, password, register_date)
VALUES ('alex24@gmail.com', 'alex', '1232', utc_timestamp);
INSERT INTO tb_users(email, name, password, register_date)
VALUES ('john25@gmail.com', 'john', '1235', utc_timestamp);
INSERT INTO tb_users(email, name, password, register_date)
VALUES ('hawk26@gmail.com', 'hawk', '1231', utc_timestamp);

INSERT INTO tb_groups(name)
VALUES ('manager');
INSERT INTO tb_groups(name)
VALUES ('seller');
INSERT INTO tb_groups(name)
VALUES ('secretary');
INSERT INTO tb_groups(name)
VALUES ('registrar');

INSERT INTO tb_groups_permissions(group_id, permission_id)
VALUES (1, 1);
INSERT INTO tb_groups_permissions(group_id, permission_id)
VALUES (1, 2);
INSERT INTO tb_groups_permissions(group_id, permission_id)
VALUES (1, 3);
INSERT INTO tb_groups_permissions(group_id, permission_id)
VALUES (2, 1);
INSERT INTO tb_groups_permissions(group_id, permission_id)
VALUES (2, 3);
INSERT INTO tb_groups_permissions(group_id, permission_id)
VALUES (3, 2);

INSERT INTO tb_users_groups(user_id, group_id)
VALUES (1, 1);
INSERT INTO tb_users_groups(user_id, group_id)
VALUES (1, 2);
INSERT INTO tb_users_groups(user_id, group_id)
VALUES (1, 4);
INSERT INTO tb_users_groups(user_id, group_id)
VALUES (2, 1);
INSERT INTO tb_users_groups(user_id, group_id)
VALUES (2, 3);
INSERT INTO tb_users_groups(user_id, group_id)
VALUES (3, 2);
INSERT INTO tb_users_groups(user_id, group_id)
VALUES (3, 1);
INSERT INTO tb_users_groups(user_id, group_id)
VALUES (3, 3);
INSERT INTO tb_users_groups(user_id, group_id)
VALUES (4, 4);

INSERT INTO tb_restaurants_users(restaurant_id, user_id)
VALUES (1, 1);
INSERT INTO tb_restaurants_users(restaurant_id, user_id)
VALUES (1, 2);
INSERT INTO tb_restaurants_users(restaurant_id, user_id)
VALUES (2, 3);
INSERT INTO tb_restaurants_users(restaurant_id, user_id)
VALUES (3, 4);
INSERT INTO tb_restaurants_users(restaurant_id, user_id)
VALUES (5, 2);
INSERT INTO tb_restaurants_users(restaurant_id, user_id)
VALUES (6, 1);

INSERT INTO tb_orders(subtotal, delivery_tax, total, creation_date, confirmed_date, canceled_date,
                      delivered_date, status, restaurant_id, payment_method_id, user_id, city_address_id, cep_address,
                      complement_address, number_address, public_area_address, street_address)
VALUES (75, 10, 85, utc_timestamp, utc_timestamp, utc_timestamp, utc_timestamp, 'CREATED', 1, 1, 1, 1, 12345,
        'near to the river', 75, 'river', 'river block street');

INSERT INTO tb_order_items(quantity, unity_price, total_price, observation, order_id, product_id)
VALUES (3, 15, 45, 'cool food', 1, 1);
INSERT INTO tb_order_items(quantity, unity_price, total_price, observation, order_id, product_id)
VALUES (2, 15, 30, 'great food', 1, 2);

INSERT INTO tb_orders(subtotal, delivery_tax, total, creation_date, confirmed_date, canceled_date,
                      delivered_date, status, restaurant_id, payment_method_id, user_id, city_address_id, cep_address,
                      complement_address, number_address, public_area_address, street_address)
VALUES (15, 10, 25, utc_timestamp, utc_timestamp, utc_timestamp, utc_timestamp, 'CREATED', 1, 2, 2, 2, 12346,
        'near the house', 123, 'house', 'house block street');

INSERT INTO tb_order_items(quantity, unity_price, total_price, observation, order_id, product_id)
VALUES (1, 15, 15, 'cool food', 2, 3);

#insert into, but if there's a problem with constraint or anything like it, just ignore command and keep going
#file callback, when flyway  migrations is done will call afterMigration file, IT'S NOT A MIGRATION, it's a simple callback sql file
#we can do anything inside this file
#if change anything in project, will crash
#can also get fresh properties, but always dev field, never prod, you can drop every data and call it again
#'Delete' statement without 'where' clears all data in the table
#SET foreign_key_checks = 0; disable fk validation to be able to delete without problem
#because of binding/relations could be a problem to remove all, would be needed to delete in order or disable fk validator
#then return to 1, enable again, needs to ALTER TABLE tb_cities AUTO_INCREMENT = 1;
#because when deleted and put again, if it has 10 properties, when delete and got a new one, will start with id 11 for example
#to start from 1 again, we put autoincrement to start at 1, associations table don't need, don't have autoincrement
#they only got id from other table
#in package 'testdata' will contain data to test db and migrations, don't mix afterMigration file with migrations scripts

#TIMESTAMP
#utc_timestamp use local date time of your computer system
#utc_timestamp use date time changed with UTC pattern