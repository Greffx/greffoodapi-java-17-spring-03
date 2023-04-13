INSERT INTO tb_kitchens (id, kitchen_name) VALUES (1, 'indian');
INSERT INTO tb_kitchens (id, kitchen_name) VALUES (2, 'Japanese');
INSERT INTO tb_kitchens (id, kitchen_name) VALUES (3, 'Brazilian');


INSERT INTO tb_states (id, state_name) VALUES (1, 'RS');
INSERT INTO tb_states (id, state_name) VALUES (2, 'PR');

INSERT INTO tb_cities (city_name, state_id) VALUES ('Porto Alegre', 1);
INSERT INTO tb_cities (city_name, state_id) VALUES ('Curitiba', 2);
INSERT INTO tb_cities (city_name, state_id) VALUES ('Viamão', 1);
INSERT INTO tb_cities (city_name, state_id) VALUES ('Campo Largo', 2);

INSERT INTO tb_payment_methods (id, description) VALUES (1, 'cash');
INSERT INTO tb_payment_methods (id, description) VALUES (2, 'credit card');
INSERT INTO tb_payment_methods (id, description) VALUES (3, 'debit card');

INSERT INTO tb_permissions (id, name, description) VALUES (1, 'consult of products', 'this user got permission to see products');
INSERT INTO tb_permissions (id, name, description) VALUES (2, 'consult of states', 'this user got permission to see states');
INSERT INTO tb_permissions (id, name, description) VALUES (3, 'consult of restaurants', 'this user got permission to see restaurants');


INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, payment_method_id) VALUES ('Ganesh_Fast_Food', 3.50, 1, 1);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, payment_method_id) VALUES ('Haiku_Tales', 5.60, 2, 1);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, payment_method_id) VALUES ('SamuraiSushi', 2.50, 2, 2);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, payment_method_id) VALUES ('Indian_Hero', 3.20, 1, 3);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, payment_method_id) VALUES ('Speed Galeto BR', 0, 3, 3);
INSERT INTO tb_restaurants (restaurant_name, delivery_tax, kitchen_id_code, payment_method_id) VALUES ('Marmita Do Zé', 0, 3, 3);
