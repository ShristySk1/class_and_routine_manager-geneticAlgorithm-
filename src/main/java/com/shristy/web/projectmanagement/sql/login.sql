/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Administrator
 * Created: May 7, 2019
 */

create table APP_USER(USER_ID BIGINT not null primary key,USER_NAME VARCHAR(36) not null unique,ENCRYPTED_PASSWORD VARCHAR(100) NOT NULL,ENABLED INT NOT NULL);
create table APP_ROLE(ROLE_ID BIGINT not null primary key,ROLE_NAME VARCHAR(36) not null unique);
create table USER_ROLE(ID BIGINT not null primary key,USER_ID BIGINT not null unique,ROLE_ID BIGINT NOT NULL UNIQUE);
ALTER TABLE USER_ROLE add foreign key(USER_ID) REFERENCES APP_USER(USER_ID);
ALTER TABLE USER_ROLE add foreign key(ROLE_ID) REFERENCES APP_ROLE(ROLE_ID);

-----------INSERT VALUES

insert into App_User (USER_ID, USER_NAME, ENCRYPTED_PASSWORD, ENABLED)
values (2, 'admin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into App_User (USER_ID, USER_NAME, ENCRYPTED_PASSWORD, ENABLED)
values (1, 'admin2', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
---
 
insert into app_role (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');
 
insert into app_role (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');
 
---
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (1, 1, 1);
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (2, 1, 2);
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (3, 2, 2);