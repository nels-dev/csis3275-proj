-- This is a script for initializing test data.
-- Always put statements in ONE LINE
insert into app_user(id, created_at, email, password, preferred_user_name, role) values(1, CURRENT_TIMESTAMP(), 'cliff@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Cliff Chan','CLIENT');
insert into app_user(id, created_at, email, password, preferred_user_name, role) values(2, CURRENT_TIMESTAMP(), 'adam@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Adam Au','ADMIN');
insert into app_user(id, created_at, email, password, preferred_user_name, role) values(3, CURRENT_TIMESTAMP(), 'bucky@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Bu Thomson','CLIENT');

insert into product(id, product_condition, description, name, status, price, user_id) values (1, '95% new', 'A mountain bike that I bought from CostCo last week, only used for a day', 'Mountain bike', 'LISTED', 200.00, 1);
insert into product_image(path, product_id) values ('6fadada5-87c2-43d3-8002-fe2596b18d43.jpg', 1);


-- Configure balance accounts for client users
insert into balance_account(id, available_balance, held_balance, last_update, user_id) VALUES (1,1000.00, 20.00, CURRENT_TIMESTAMP, 1);
insert into balance_account(id, available_balance, held_balance, last_update, user_id) VALUES (2, 0, 0, CURRENT_TIMESTAMP, 3);