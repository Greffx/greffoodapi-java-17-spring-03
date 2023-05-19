ALTER TABLE tb_restaurants ADD COLUMN open BOOLEAN NOT NULL;
UPDATE tb_restaurants SET open = 1 WHERE id > 0;