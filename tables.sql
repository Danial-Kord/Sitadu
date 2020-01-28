show databases;
show tables ;
# drop database sitadu_DataBase;
# create database sitadu_DataBase character set = utf8;




create table address(
    id  int auto_increment,
    name varchar(20),
    address text,
    house_phone_number char(7),
    primary key (id)
);

create table customer_id(
  id    char(15),
  primary key (id)
);
create table customer
(
    user    varchar(20) not null ,
    pass    varchar(30) not null ,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    melli_code char(10) not null,
    phone      char(7),
    address_id int,
    age        int(3),
    foreign key (address_id) references address(id),
    primary key (user)
);

create table factor(
    customer_id char(15) default null ,
    id int auto_increment,
    name char(30) default null,
    date date,
    foreign key (customer_id) references customer(user),#TODO
    primary key (id)
);

create table menu(
id int auto_increment,
name    varchar(30),
total_price   int(7),
primary key (id)
);
create table menu_factor(
    id  int auto_increment,
    factor_id int,
    food_id int,
    food_name    varchar(30),
    food_price   int(7),
    primary key (id),
    foreign key (factor_id) references factor(id),
    foreign key (food_id) references menu(id)
);

create table peyk(
    id int auto_increment,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    phone     int(7),
    primary key (id)
);

create table delivery(
    id int auto_increment,
    peyk_id int,
    factor_id int,
    primary key (id),
    foreign key (factor_id) references factor(id),
    foreign key (peyk_id) references peyk(id)
);


create table log(#TODO
    id  int(30) auto_increment,
    information text,
    primary key (id)
);
