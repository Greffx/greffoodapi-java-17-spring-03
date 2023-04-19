
    CREATE TABLE tb_groups (
       id BIGINT NOT NULL AUTO_INCREMENT,
        description VARCHAR(255),

        PRIMARY KEY (id)
    ) ENGINE=InnoDB;

    CREATE TABLE tb_groups_permissions (
       group_id BIGINT NOT NULL,
        permission_id BIGINT NOT NULL
    ) ENGINE=InnoDB;

    CREATE TABLE tb_payment_methods (
       id BIGINT NOT NULL AUTO_INCREMENT,
        description VARCHAR(255) NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB;

    CREATE TABLE tb_permissions (
       id BIGINT NOT NULL AUTO_INCREMENT,
        description VARCHAR(255) NOT NULL,
        name VARCHAR(80) NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB;

    CREATE TABLE tb_products (
       id BIGINT NOT NULL AUTO_INCREMENT,
        active BIT,
        description VARCHAR(255),
        name VARCHAR(80),
        price DECIMAL(9,2),
        restaurant_id BIGINT NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB;

    CREATE TABLE tb_restaurants (
       id BIGINT NOT NULL AUTO_INCREMENT,
        cep_address VARCHAR(8),
        complement_address VARCHAR(255),
        number_address VARCHAR(20),
        public_area_address VARCHAR(255),
        street_address VARCHAR(80),
        creation_date DATETIME NOT NULL,
        delivery_tax DECIMAL(9,2) NOT NULL,
        restaurant_name VARCHAR(80) NOT NULL,
        update_date DATETIME NOT NULL,
        city_address_id BIGINT,
        kitchen_id_code BIGINT NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB;

    CREATE TABLE tb_restaurants__payment_methods (
       restaurant_id BIGINT NOT NULL,
        payment_method_id BIGINT NOT NULL
    ) ENGINE=InnoDB;

    CREATE TABLE tb_users (
       id BIGINT NOT NULL AUTO_INCREMENT,
        email VARCHAR(80),
        name VARCHAR(80),
        password VARCHAR(80),
        register_date DATETIME NOT NULL,

        PRIMARY KEY (id)
    ) ENGINE=InnoDB;

    CREATE TABLE tb_users_groups (
       user_id BIGINT NOT NULL,
        group_id BIGINT NOT NULL
    ) ENGINE=InnoDB;

    ALTER TABLE tb_groups_permissions
       ADD CONSTRAINT fk_tb_groups_permissions
       FOREIGN KEY (permission_id)
       REFERENCES tb_permissions (id);

    ALTER TABLE tb_groups_permissions
       ADD CONSTRAINT fk_tb_groups_groups
       FOREIGN KEY (group_id)
       REFERENCES tb_groups (id);

    ALTER TABLE tb_products
       ADD CONSTRAINT fk_tb_products_restaurants
       FOREIGN KEY (restaurant_id)
       REFERENCES tb_restaurants (id);

    ALTER TABLE tb_restaurants
       ADD CONSTRAINT fk_tb_restaurants_cities
       FOREIGN KEY (city_address_id)
       REFERENCES tb_cities (id);

    ALTER TABLE tb_restaurants
       ADD CONSTRAINT fk_tb_restaurants_kitchens
       FOREIGN KEY (kitchen_id_code)
       REFERENCES tb_kitchens (id);

    ALTER TABLE tb_restaurants__payment_methods
       ADD CONSTRAINT fk_tb_restaurants__payment_methods_payment_methods
       FOREIGN KEY (payment_method_id)
       REFERENCES tb_payment_methods (id);

    ALTER TABLE tb_restaurants__payment_methods
       ADD CONSTRAINT fk_tb_restaurants__payment_methods_restaurants
       FOREIGN KEY (restaurant_id)
       REFERENCES tb_restaurants (id);

    ALTER TABLE tb_users_groups
       ADD CONSTRAINT fk_tb_users_groups_groups
       FOREIGN KEY (group_id)
       REFERENCES tb_groups (id);

    ALTER TABLE tb_users_groups
       ADD CONSTRAINT fk_tb_users_groups_users
       FOREIGN KEY (user_id)
       REFERENCES tb_users (id);

#it's ok to generate like this, but constraints, fk name is all wrong, random type, VARCHAR default 255 type, don't need like that
#need to verify everything about this file to use, always