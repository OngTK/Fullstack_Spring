drop database if exists spring08;
create database spring08;
use spring08;

create table mvc(
   var1 varchar(30)
);

insert into mvc values("유재석"),("강호동"),("신동엽");

select*from mvc;