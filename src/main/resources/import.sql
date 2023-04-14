#this file is a SQL script to insert into DB

INSERT INTO tb_kitchens (id, kitchen_name) VALUES (1, 'indian');
INSERT INTO tb_kitchens (id, kitchen_name) VALUES (2, 'Japanese');
INSERT INTO tb_kitchens (id, kitchen_name) VALUES (3, 'Brazilian');

INSERT INTO tb_states (id, state_name) VALUES (1, 'RS');
INSERT INTO tb_states (id, state_name) VALUES (2, 'PR');

#Relation with state many cities to one state
INSERT INTO tb_cities (city_name, state_id) VALUES ('Porto Alegre', 1);
INSERT INTO tb_cities (city_name, state_id) VALUES ('Curitiba', 2);
INSERT INTO tb_cities (city_name, state_id) VALUES ('Viamão', 1);
INSERT INTO tb_cities (city_name, state_id) VALUES ('Campo Largo', 2);

#Relation with kitchen and address
#many restaurants has one kitchen type
#one restaurant has one address. Address is a embedded type, that's why as no table, just added
#utc_timestamp takes hour, date now of your machine
#UTC is a timezone GMT+0, so add more 3 hours, could use GMT-3

INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, cep_address, public_area_address, number_address, complement_address, street_address, city_address_id, creation_date, update_date) VALUES ('Ganesh_Fast_Food', 3.50, 1, '12345-999', 'Near to the elephant', '95', 'center of india', 'cow street', 4, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date) VALUES ('Haiku_Tales', 5.60, 2, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date) VALUES ('SamuraiSushi', 2.50, 2, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date) VALUES ('Indian_Hero', 3.20, 1, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date) VALUES ('Speed Galeto BR', 0, 3, utc_timestamp, utc_timestamp);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, creation_date, update_date) VALUES ('Marmita Do Zé', 0, 3, utc_timestamp, utc_timestamp);

INSERT INTO tb_payment_methods (id, description) VALUES (1, 'cash');
INSERT INTO tb_payment_methods (id, description) VALUES (2, 'credit card');
INSERT INTO tb_payment_methods (id, description) VALUES (3, 'debit card');

INSERT INTO tb_permissions (id, name, description) VALUES (1, 'consult of products', 'this user got permission to see products');
INSERT INTO tb_permissions (id, name, description) VALUES (2, 'consult of states', 'this user got permission to see states');
INSERT INTO tb_permissions (id, name, description) VALUES (3, 'consult of restaurants', 'this user got permission to see restaurants');

#Relation many to many with restaurants and payments
#that's why we got a table here with that information
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (1,1);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (1,2);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (1,3);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (2,1);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (2,2);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (3,2);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (3,3);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (4,1);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (5,2);
INSERT INTO tb_restaurant__payment_method (restaurant_id, payment_method_id) VALUES (6,1);

