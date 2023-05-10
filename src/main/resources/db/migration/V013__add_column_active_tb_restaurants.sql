ALTER TABLE tb_restaurants ADD active BOOLEAN NOT NULL;
UPDATE tb_restaurants SET active = TRUE WHERE id > 0;