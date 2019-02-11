//本项目所使用的数据库的建表语句和一些相关操作

create database secKill;

use secKill;

create table user_info(
	id int not null primary key auto_increment,
    name varchar(64) not null,
	gender int not null check(gender='1' or gender='2'), 
    age int not null,
    telephone varchar(11) not null,
    register_mode varchar(64) not null,
    third_party_id varchar(64) not null
)

select *
from user_info;

delete from user_info where id = '1';

delete from user_password where id = '1';

delete from order_info where id = '2019-01-30T14:43:06.0600100';


create table user_password(
	id int not null primary key auto_increment,
    encrpt_password varchar(128) not null,
    user_id int default 0
)

select*
from user_password;

create table item(
	id int not null primary key auto_increment,
    title varchar(64) not null default '',
    price double not null default 0,
	description varchar(500) not null default '',
    sales int not null default 0,
    img_url varchar(500) not null default ''
)


select * from item;

create table item_stock(
	id int not null primary key auto_increment,
    stock int not null default 0,
    item_id int not null default 0 
)

select * from item_stock;


create table order_info(
	id varchar(32) not null primary key,
    user_id int not null,
    item_id int not null,
    item_price double not null,
    amount int not null,
    order_price double
)


select * from order_info;

create table sequence_info(
	name varchar(255) not null primary key,
    current_value int not null default 0,
    step int not null default 0
)

select * from sequence_info;

insert into sequence_info values("order_info", "0", "1");

create table promo(
	id int not null primary key auto_increment,
    promo_name varchar(64) not null default '',
    start_time datetime not null default '0000-00-00 00:00:00',
    end_time datetime not null default '0000-00-00 00:00:00',
    item_id int not null default 0,
    promo_item_price double not null default 0
)


select * from promo;

insert into promo values(0, "华为荣耀8抢购活动", "2019-1-30 15:30:00",  "2019-2-1 6:00;00","3", "1800");

insert into promo values(0, "iphone10抢购活动", "2019-2-3 22:30:00",  "2019-2-4 20:00;00","1", "6000");



