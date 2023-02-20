-- This is a script for initializing test data.
-- Always put statements in ONE LINE
insert into app_user(id, created_at, email, password, preferred_user_name, role) values(1, CURRENT_TIMESTAMP(), 'cliff@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Cliff Chan','CLIENT');
insert into app_user(id, created_at, email, password, preferred_user_name, role) values(2, CURRENT_TIMESTAMP(), 'adam@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Adam Au','ADMIN');
insert into app_user(id, created_at, email, password, preferred_user_name, role) values(3, CURRENT_TIMESTAMP(), 'bucky@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Bu Thomson','CLIENT');

insert into product(id, product_condition, description, name, status, price, user_id)
    values (1, '95% new', 'A mountain bike that I bought from CostCo last week, only used for a day', 'Mountain bike', 'LISTED', 200.00, 1);
insert into product_image(path, product_id)
    values ('6fadada5-87c2-43d3-8002-fe2596b18d43.jpg', 1);

insert into product(id, product_condition, description, name, status, price, user_id)
    values (2, '90% new', 'Only wear once. With tags and receipt ', 'Snow Jacket', 'LISTED', 99.00, 1);
insert into product_image(path, product_id)
    values ('05f36f60-4edf-42cb-81c9-e1d550dcfc31.jpg', 2);

insert into product(id, product_condition, description, name, status, price, user_id)
    values (3, '100% new', 'Does not fit my face, never wore', 'Oakley Goggle', 'LISTED', 120.00, 1);
insert into product_image(path, product_id)
    values ('675991b5-2325-4d19-a144-560100aaf60d.jpg', 3);

insert into product(id, product_condition, description, name, status, price, user_id)
    values (4, 'Like new', 'Upgraded to a better bindings, selling it for cheap', 'Burton Bindings', 'LISTED', 50.00, 1);
insert into product_image(path, product_id)
    values ('05595596-dd2e-44a0-a5bc-980bfac06d52.jpg', 4);

insert into product(id, product_condition, description, name, status, price, user_id)
    values (5, 'OK', 'Old helmet I purchased from 5 years ago', 'Oakley Helmet', 'LISTED', 10.00, 1);
insert into product_image(path, product_id)
    values ('fad65619-bba9-4ebc-ab48-1593ccd44220.jpg', 5);

insert into product(id, product_condition, description, name, status, price, user_id)
    values (6, '85%', 'Old snow gloves of my son bought 2 years ago', 'Ski Gloves', 'UNLISTED', 10.00, 1);
insert into product_image(path, product_id)
    values ('dcb4bf91-9923-498d-a864-40a93d15f9df.jpg', 6);



-- Configure balance accounts for client users
insert into balance_account(id, available_balance, held_balance, last_update, user_id) VALUES (1,1000.00, 20.00, CURRENT_TIMESTAMP, 1);
insert into balance_account(id, available_balance, held_balance, last_update, user_id) VALUES (2, 0, 0, CURRENT_TIMESTAMP, 3);