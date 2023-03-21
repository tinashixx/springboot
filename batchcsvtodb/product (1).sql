use mydb;
create  database mydb;
drop database mydb;
drop table product;

create table product(
id int auto_increment PRIMARY KEY,
name varchar(20),
description varchar(100),
price decimal
);

create table product(
id int  PRIMARY KEY,
name varchar(20),
description varchar(100),
price decimal
);


INSERT INTO PRODUCT(ID,NAME,DESCRIPTION,PRICE) VALUES(5, "penna", "buono", 2 );
select * from product;