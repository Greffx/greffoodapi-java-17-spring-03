ALTER TABLE tb_cities CHANGE city_name name varchar(80) NOT NULL;
#to alter table cities, change <columnNameCurrent> <columnNameThatWillChange> needs to specify property, if it's a varchar or something else
# and if it's null or etc