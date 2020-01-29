show databases;
show tables ;
# drop database sitadu_DataBase;
# create database sitadu_DataBase character set = utf8;






create table customer
(
    user    varchar(20) not null ,
    pass    varchar(30) not null ,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    melli_code char(10) not null,
    phone      char(7),
    age        int(3),
    primary key (user)
);


create table address(
    id  int auto_increment,
    name varchar(20),
    address text,
    house_phone_number char(7),
    customer_id varchar(20) ,
    foreign key (customer_id) references customer(user) on delete cascade,
    primary key (id)
);

create table peyk(
    id int auto_increment,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    phone     int(7),
    primary key (id)
);
create table factor(
    id int auto_increment,
    customer_id varchar(20)  null,
    name char(30) default null,
    time DATETIME,
    peyk_id int null,
    foreign key (peyk_id) references peyk(id) on delete set null ,
    primary key (id),
        FOREIGN KEY (customer_id)
       REFERENCES customer(user) on delete cascade
);

create table menu(
id int auto_increment,
name    varchar(30),
price   int,
primary key (id)
);
create table menu_factor(
    id  int auto_increment,
    factor_id int,
    food_id int null,
    food_name    varchar(30),
    price   int,
    primary key (id),
    foreign key (factor_id) references factor(id) on delete cascade ,
    foreign key (food_id) references menu(id) on delete set null
);




create table raw_material(
id int auto_increment,
name    varchar(30),
price   int,
primary key (id)

);


create table log(#TODO
    id  int(30) auto_increment,
    information text,
    primary key (id)
);


