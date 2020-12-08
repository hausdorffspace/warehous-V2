INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(created_at,updated_at,email,name,password,username) VALUES("2020-12-08 14:56:01","2020-12-08 14:56:01","rafal.antas96@gmail.com","Rafal Antas","$2a$10$StuLvpvTsyLxeqZoYIa0G.79b5PNLR46zMrVUw7wQXn2bxPBLrNV2","test");

INSERT INTO piano(is_borrowed,model,producer,user_id) VALUES(0,"D_274","STAINWAY_SONS",1);