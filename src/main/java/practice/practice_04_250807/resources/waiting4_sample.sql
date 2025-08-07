drop database if exists waiting4;
create database waiting4;
use waiting4;

create table waiting(
wno int auto_increment, 
phone varchar(15), 
count int not null,
constraint primary key(wno)
) 