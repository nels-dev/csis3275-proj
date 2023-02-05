-- This is a script for initializing test data.
-- Always put statements in ONE LINE
insert into app_user(created_at, email, password, preferred_user_name, role) values(CURRENT_TIMESTAMP(), 'cliff@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Cliff Chan','CLIENT');
insert into app_user(created_at, email, password, preferred_user_name, role) values(CURRENT_TIMESTAMP(), 'adam@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Adam Au','ADMIN');

insert into product(id, product_condition, description, name, status) values (1, '95% new', 'A mountain bike that I bought from CostCo last week, only used for a day', 'Mountain bike', 'LISTED');
insert into product_image(path, product_id) values ('6fadada5-87c2-43d3-8002-fe2596b18d43.jpg', 1);