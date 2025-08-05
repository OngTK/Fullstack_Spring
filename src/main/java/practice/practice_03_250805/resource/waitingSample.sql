drop database if exists waiting;
create database waiting;
use waiting;

create table waiting_list(
wno int not null auto_increment,
phone varchar(15) not null,
count int not null default 1,
constraint primary key(wno));

select * from waiting_List;