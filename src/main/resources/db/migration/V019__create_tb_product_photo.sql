CREATE TABLE tb_product_photo(
  product_id    BIGINT                  NOT NULL,
  filename      VARCHAR(100)            NOT NULL,
  description   VARCHAR(150)            NOT NULL,
  contentType   VARCHAR(150)            NOT NULL,
  size          LONG                    NOT NULL,

  PRIMARY KEY (product_id), CONSTRAINT fk_product_photo_product FOREIGN KEY (product_id)
      REFERENCES tb_products (id)
);

#since will exists photo ONLY when exits a product, can use product id in this one, like fk and pk