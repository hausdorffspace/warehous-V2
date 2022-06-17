INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(created_at,updated_at,email,name,password,username) VALUES('2020-12-08 14:56:01','2020-12-08 14:56:01','rafal.antas96@gmail.com','Rafal Antas','$2a$10$StuLvpvTsyLxeqZoYIa0G.79b5PNLR46zMrVUw7wQXn2bxPBLrNV2','test');
INSERT INTO users(created_at,updated_at,email,name,password,username) VALUES('2020-12-08 14:56:01','2020-12-08 14:56:01','test@gmail.com','Emilka','$2a$10$/fF1BgDzB1/tXBI6V4iFueAU0nhBbXB2rKTAeKyWYGoJ4w4XcRxaK','emilka');

INSERT INTO user_roles(user_id,role_id) VALUES(1,1);
INSERT INTO user_roles(user_id,role_id) VALUES(1,2);


INSERT INTO piano(sku,available_now,model,price,producer) VALUES('QWERTY',true,'D_274',599999,'STAINWAY_SONS');
INSERT INTO piano(sku,available_now,model,price,producer) VALUES('QWERT',true,'S_155',99999,'STAINWAY_SONS');
INSERT INTO piano(sku,available_now,model,price,producer) VALUES('QWER',true,'D_274',599999,'STAINWAY_SONS');
INSERT INTO piano(sku,available_now,model,price,producer) VALUES('QWE',false,'D_274',599999,'STAINWAY_SONS');

INSERT INTO reservation(start_reservation_date,end_reservation_date,piano_id,user_id) VALUES ('2022-12-08','2023-12-08',1,1);
INSERT INTO reservation(start_reservation_date,end_reservation_date,piano_id,user_id) VALUES ('2067-12-08','2088-12-08',1,1);
INSERT INTO reservation(start_reservation_date,end_reservation_date,piano_id,user_id) VALUES ('2099-12-08','2100-12-08',2,1);

