INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(created_at,updated_at,email,name,password,username) VALUES("2020-12-08 14:56:01","2020-12-08 14:56:01","rafal.antas96@gmail.com","Rafal Antas","$2a$10$StuLvpvTsyLxeqZoYIa0G.79b5PNLR46zMrVUw7wQXn2bxPBLrNV2","test");

INSERT INTO piano(sku,avaliable,model,price,producer) VALUES("QWERTY",1,"D_274",599999,"STAINWAY_SONS");
INSERT INTO piano(sku,avaliable,model,price,producer) VALUES("QWERT",1,"S_155",99999,"STAINWAY_SONS");
INSERT INTO piano(sku,avaliable,model,price,producer) VALUES("QWER",1,"D_274",599999,"STAINWAY_SONS");

INSERT INTO reservation(start_reservation_date,end_reservation_date,piano_id,user_id) VALUE ("2020-12-08","2021-12-08",1,1);

