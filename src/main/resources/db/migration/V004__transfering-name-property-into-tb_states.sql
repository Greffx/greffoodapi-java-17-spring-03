#inserting in state table, into property 'name' by distinct selection of column property 'state name' from cities table
#select distinct will no repeat the same name to insert, like if has 2 texas, will only get one time
INSERT INTO tb_states (name) SELECT DISTINCT state_name FROM tb_cities;