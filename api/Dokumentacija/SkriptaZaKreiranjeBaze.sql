use samgresdb;

DROP TABLE IF EXISTS user;

CREATE TABLE user(
phone_number varchar(20) primary key,
password varchar(45) not null,
name varchar(20) not null,
surname varchar(30) not null,
email varchar(45) not null
);


DROP TABLE IF EXISTS post;

CREATE TABLE post(
idPost int primary key auto_increment,
phone_number_user varchar(20) not null,
description varchar(100),
category varchar(20),
x decimal(33,30),
y decimal(33,30),
region varchar(45),
foreign key (phone_number_user) references user(phone_number)
);