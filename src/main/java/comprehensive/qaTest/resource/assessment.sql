drop database if exists assessment;
create database assessment;
use assessment;

create table member(
custno int not null auto_increment,
custname varchar(20),
phone varchar(13),
address varchar(60),
joindate date,
grade char(1),
city char(2),
constraint primary key(custno)
);

create table money(
custno int not null,
salenol int not null auto_increment,
pcost int,
amount int,
price int,
pcode varchar(4),
sdate date,
constraint primary key(salenol),
constraint foreign key(custno) references member(custno)
);

alter table member auto_increment = 100001;
alter table money auto_increment = 20160001;

insert into member(custname, phone, address, joindate, grade, city) values 
("김행복","010-1111-2222","서울 동대문구 휘경1동","2015-12-02",'A','01'),
("이축복","010-1111-3333","서울 동대문구 휘경2동","2015-12-06",'B','01'),
("장믿음","010-1111-4444","울릉군 울릉읍 독도1리","2015-10-01",'B','30'),
("최사랑","010-1111-5555","울릉군 울릉읍 독도2리","2015-11-13",'A','30'),
("진평화","010-1111-6666","제주도 제주시 외나무골","2015-12-25",'B','60'),
("자공단","010-1111-7777","제주도 제주시 감나무골","2015-12-11",'C','60');

insert into money(custno,pcost,amount, price,pcode,sdate) values
(100001,500,5,pcost*amount,"A001","2016-01-01"),
(100001,1000,4,pcost*amount,"A002","2016-01-01"),
(100001,500,3,pcost*amount,"A008","2016-01-01"),
(100002,500,1,pcost*amount,"A001","2016-01-03"),
(100002,500,1,pcost*amount,"A003","2016-01-03"),  -- 삭제
(100003,1500,2,pcost*amount,"A003","2016-01-03"),
(100004,500,2,pcost*amount,"A001","2016-01-04"),
(100004,300,1,pcost*amount,"A005","2016-01-04"),
(100004,600,1,pcost*amount,"A006","2016-01-04"),
(100004,3000,1,pcost*amount,"A007","2016-01-06");

delete from money where salenol=20160004;
select * from member;
select * from money;

select money.custno, member.custName, member.grade, sum(money.price) as totalPrice from money inner join member on member.custNo = money.custno group by member.custNo order by totalPrice desc;

select max(custno) from member;