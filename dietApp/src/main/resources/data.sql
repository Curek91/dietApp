-- Types sqls
insert into type (id, created_by, creation_timestamp, modification_timestamp, modified_by, name) values (nextval('type_seq'), 'Curek', now(), now(), 'Curek', 'Białko');
insert into type (id, created_by, creation_timestamp, modification_timestamp, modified_by, name) values (nextval('type_seq'), 'Curek', now(), now(), 'Curek', 'Weglowodany');
insert into type (id, created_by, creation_timestamp, modification_timestamp, modified_by, name) values (nextval('type_seq'), 'Curek', now(), now(), 'Curek', 'Tłuszcze');

-- Products sqls
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:35:01.077','2019-06-29 18:35:43.602','Curek',12,0,55,'Ananas',0,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:38:05.154','2019-06-29 18:38:20.521','Curek',22,0,97,'Banan',1,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:39:27.842','2019-06-29 18:39:35.734','Curek',0,1,98,'Pierś z kurczaka',21,1);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:40:09.161','2019-06-29 18:40:17.724','Curek',0,1,78,'Dorsz',18,1);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:41:08.214','2019-06-29 18:41:17.588','Curek',1,10,140,'Jajko',12,1);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:41:58.611','2019-06-29 18:42:06.226','Curek',10,0,50,'Jabłko',0,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:42:33.299','2019-06-29 18:42:37.518','Curek',1,0,16,'Kapusta Kiszona',1,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:43:13.428','2019-06-29 18:43:17.317','Curek',0,14,205,'Karkówka',18,1);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:43:55.253','2019-06-29 18:44:09.189','Curek',63,3,344,'Kasza Gryczana',12,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:44:44.503','2019-06-29 18:44:54.164','Curek',1,3,104,'Wołowina',17,1);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:45:20.333','2019-06-29 18:45:26.968','Curek',0,2,110,'Ligawa wołowa',22,1);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:46:11.466','2019-06-29 18:46:21.387','Curek',72,2,349,'Makaron',12,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:46:56.845','2019-06-29 18:47:11.371','Curek',83,1,365,'Makaron ryżowy',6,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:47:47.821','2019-06-29 18:47:54.259','Curek',10,67,695,'Masło orzechowe',13,3);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:48:34.121','2019-06-29 18:48:42.583','Curek',62,7,376,'Płatki owsiane',13,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:49:25.095','2019-06-29 18:49:32.821','Curek',1,0,12,'Ogórek kiszony',1,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:50:03.167','2019-06-29 18:50:16.946','Curek',0,99,882,'Oliwa z oliwek',0,3);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:50:57.269','2019-06-29 18:51:04.718','Curek',0,1,84,'Pierś z indyka',19,1);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:51:46.662','2019-06-29 18:51:53.905','Curek',3,0,19,'Pomidor',1,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:53:07.897','2019-06-29 18:53:20.514','Curek',76,1,345,'Ryż biały',7,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:53:48.783','2019-06-29 18:53:56.498','Curek',76,1,351,'Ryż basmati',9,2);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:54:35.909','2019-06-29 18:54:46.411','Curek',2,5,97,'Serek wiejski',11,1);
insert into product (id,created_by,creation_timestamp,modification_timestamp,modified_by,carbs,fat,kcal,name,protein,type_id) values (nextval('product_seq'),'Curek','2019-06-29 18:55:34.692','2019-06-29 18:55:43.376','Curek',0,3,112,'Wolowina polędwica',20,1);

-- login user admin admin
INSERT INTO loginuser(
            id, created_by, creation_timestamp, modification_timestamp, modified_by,
            email, enabled, firstname, lastpasswordresetdate, lastname,
            password, username)
    VALUES (nextval('loginuser_seq'), 'Curek', now(), now(), 'Curek',
            'Curek', true, 'Curek', now(), 'Curek',
            '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'Rafal');

INSERT INTO loginuser(
            id, created_by, creation_timestamp, modification_timestamp, modified_by,
            email, enabled, firstname, lastpasswordresetdate, lastname,
            password, username)
    VALUES (nextval('loginuser_seq'), 'Curek', now(), now(), 'Curek',
            'Curek', true, 'Curek', now(), 'Curek',
            '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'Curek');


-- clients sqls
insert into client (id,created_by,creation_timestamp,modification_timestamp,modified_by,age,email,firstname,height,lastname,telephone,weight) values (nextval('client_seq'),'Curek','2019-06-29 19:29:19.799','2019-06-29 19:29:19.796','',27,'tomasz.cur.91@gmail.com','Tomasz',163,'Cur','796455725',68);
insert into client (id,created_by,creation_timestamp,modification_timestamp,modified_by,age,email,firstname,height,lastname,telephone,weight) values (nextval('client_seq'),'Curek','2019-06-29 19:29:19.799','2019-06-29 19:29:19.796','',27,'tomasz.cur.91@gmail.com','Rafał',163,'Czerski','796455725',68);

