drop database if exists waiting;
create database waiting;
use waiting;

create table waitingList(
wno int not null auto_increment,
phone varchar(15) not null,
count int not null,
wdate datetime default now(),
constraint primary key(wno)
);

select * from waitingList;

insert into waitingList(phone, count) values("010-0000-0000", 3);