-- This is a script for initializing test data.
-- Always put statements in ONE LINE
insert into app_user(created_at, email, password, preferred_user_name, role) values(CURRENT_TIMESTAMP(), 'cliff@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Cliff Chan','CLIENT');
insert into app_user(created_at, email, password, preferred_user_name, role) values(CURRENT_TIMESTAMP(), 'adam@test.io','$2a$12$tX81FQUy9CXEiVuEDmWnlOsiko/TtexA5f.RNP3RnKAp5sOTlY7yK','Adam Au','ADMIN');