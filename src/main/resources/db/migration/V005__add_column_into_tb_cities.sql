ALTER TABLE tb_cities ADD COLUMN state_id BIGINT NOT NULL;
#altering tb cities, adding column stateId, to table cities bind with tb states
#inside cities, state id, when put not null, it's going to make everything inside that column equals 0, that's not right
#that way can not create foreign key, the reference key to states table with id property, can't make references with 0 id