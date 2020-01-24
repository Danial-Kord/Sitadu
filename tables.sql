# create database sitadu_DataBase character set = utf8;
# show databases;
# show tables ;




create table address(
    id  char(10),
    name varchar(20),
    address text,
    house_phone_number int(7),
    primary key (id)
);

create table customer_id(
  id    char(15),
  primary key (id)
);
create table customer
(
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    melli_code char(10) not null,
    customer_id char(15)not null ,
    phone      int(7),
    age        int(3),
    address_id char(10),
    foreign key (address_id) references address(id),
    foreign key (customer_id) references customer_id(id),
    primary key (melli_code)
);

create table factor(
    customer_id char(15),
    id  char(15) ,
    name char(30) default null,
    date date,
    primary key (id),
    foreign key (customer_id) references customer_id(id)#TODO
);

create table menu(
id  char(10),
name    varchar(30),
price   int(7),
primary key (id)
);
create table menu_factor(
    id char(20),
    factor_id char(15),
    food_id char(10),
    food_name    varchar(30),
    food_price   int(7),
    primary key (id),
    foreign key (factor_id) references factor(id),
    foreign key (food_id) references menu(id)
);

create table peyk(
    id char(5),
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    phone     int(7),
    primary key (id)
);

create table delivery(
    id char(5),
    peyk_id char(5),
    factor_id char(15),
    primary key (id),
    foreign key (factor_id) references factor(id),
    foreign key (peyk_id) references peyk(id)
)


create table log(
    id  int(30) auto_increment,
    information text,
    primary key (id)
)
