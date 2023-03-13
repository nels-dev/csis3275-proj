-- This is a script for initializing test data.
-- Always put statements in ONE LINE
insert into app_user(created_at, email, password, preferred_user_name, role) values(CURRENT_TIMESTAMP(), 'cliff@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Cliff Chan','CLIENT');
insert into app_user(created_at, email, password, preferred_user_name, role) values(CURRENT_TIMESTAMP(), 'adam@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Adam Au','ADMIN');
insert into app_user(created_at, email, password, preferred_user_name, role) values(CURRENT_TIMESTAMP(), 'bucky@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Bu Thomson','CLIENT');

insert into product(product_condition, description, name, status, price, user_id)
    values ('95% new', 'A mountain bike that I bought from CostCo last week, only used for a day', 'Mountain bike', 'LISTED', 200.00, 1);
insert into product_image(path, product_id)
    values ('6fadada5-87c2-43d3-8002-fe2596b18d43.jpg', 1);

insert into product(product_condition, description, name, status, price, user_id)
    values ('90% new', 'Only wear once. With tags and receipt ', 'Snow Jacket', 'LISTED', 99.00, 1);
insert into product_image(path, product_id)
    values ('05f36f60-4edf-42cb-81c9-e1d550dcfc31.jpg', 2);

insert into product(product_condition, description, name, status, price, user_id)
    values ('100% new', 'Does not fit my face, never wore', 'Oakley Goggle', 'LISTED', 120.00, 1);
insert into product_image(path, product_id)
    values ('675991b5-2325-4d19-a144-560100aaf60d.jpg', 3);

insert into product(product_condition, description, name, status, price, user_id)
    values ('Like new', 'Upgraded to a better bindings, selling it for cheap', 'Burton Bindings', 'LISTED', 50.00, 1);
insert into product_image(path, product_id)
    values ('05595596-dd2e-44a0-a5bc-980bfac06d52.jpg', 4);

insert into product(product_condition, description, name, status, price, user_id)
    values ('OK', 'Old helmet I purchased from 5 years ago', 'Oakley Helmet', 'LISTED', 10.00, 3);
insert into product_image(path, product_id)
    values ('fad65619-bba9-4ebc-ab48-1593ccd44220.jpg', 5);

insert into product(product_condition, description, name, status, price, user_id)
    values ('85%', 'Old snow gloves of my son bought 2 years ago', 'Ski Gloves', 'UNLISTED', 10.00, 3);
insert into product_image(path, product_id)
    values ('dcb4bf91-9923-498d-a864-40a93d15f9df.jpg', 6);



-- Configure balance accounts for client users
insert into balance_account(available_balance, held_balance, last_update, user_id) VALUES (1000.00, 20.00, CURRENT_TIMESTAMP, 1);
insert into balance_account(available_balance, held_balance, last_update, user_id) VALUES (500, 0, CURRENT_TIMESTAMP, 3);


-- Configure financial requests for client users
insert into deposit_request(amount, bank_reference, created_at, deposit_date, reject_reason, status, created_by)
values (100.00, 'R-105102401248', CURRENT_TIMESTAMP, '2022-02-25',null, 'PENDING',1);
insert into deposit_request(amount, bank_reference, created_at, deposit_date, reject_reason, status, created_by)
values (250.00, 'R-120095192441', CURRENT_TIMESTAMP, '2022-02-26',null, 'PENDING',3);
insert into withdrawal_request(amount, bank_account_number, bank_institution_number, bank_transit_number, beneficiary_name, created_at, reject_reason, status, created_by)
values (100.00, '82850129', '001', '98765', 'Cliff Chan',CURRENT_TIMESTAMP, null,'PENDING',1);
insert into withdrawal_request(amount, bank_account_number, bank_institution_number, bank_transit_number, beneficiary_name, created_at, reject_reason, status, created_by)
values (300.00, '41024824', '003', '12598', 'Nelson',CURRENT_TIMESTAMP, null,'PENDING',3);