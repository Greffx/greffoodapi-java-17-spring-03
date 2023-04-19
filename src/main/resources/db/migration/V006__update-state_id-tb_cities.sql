UPDATE tb_cities c SET c.state_id = (SELECT e.id FROM tb_states e WHERE e.name = c.state_name);
#update table cities (which is id by 'c', is like a nickname), c.state_id will receive =
# select id (with is recognized by var 'e') from tb states where table states name property is equal to state_name from tb cities