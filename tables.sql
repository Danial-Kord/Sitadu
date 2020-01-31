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
    melli_code char(10) not null check ( melli_code  like '[0-9]'),
    phone      char(7)  ,
    check ( phone  like '[0-9]'),
    age        int(3),

    primary key (user)
);

insert into customer values ("sdwdsad", "pass", "first_name", "last_name", "melfgcode", "phone", 22) ;
select * from customer;


create table address(
    id  int auto_increment,
    name varchar(20),
    address text,
    house_phone_number char(7),
    customer_id varchar(20) ,
    check ( house_phone_number like '%[^0-9]%'),
    foreign key (customer_id) references customer(user)on delete cascade on update cascade ,
    primary key (id)
);

create table peyk(
    id int auto_increment,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    phone     char(7),
    check ( phone like '%[^0-9]%'),
    primary key (id)
);
create table factor(
    id int auto_increment,
    customer_id varchar(20)  null,
    name char(30) default null,
    time timestamp default current_timestamp,
    peyk_id int null,
    total_price varchar(50) null,
    foreign key (peyk_id) references peyk(id) on delete set null  on update cascade ,
    primary key (id),
        FOREIGN KEY (customer_id)
       REFERENCES customer(user) on delete cascade on update cascade
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
    foreign key (factor_id) references factor(id) on delete cascade on update cascade ,
    foreign key (food_id) references menu(id)on delete cascade on update cascade
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
    mtable varchar(20),
    time DATETIME default current_timestamp,
    primary key (id)
);

CREATE TRIGGER log  after insert ON customer
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("insertion to table customer with username :",new.user),"customer");
       END;

CREATE TRIGGER log2  after insert ON factor
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("insertion to factor address with id :",new.id),"factor");
       END;

CREATE TRIGGER log3  after insert ON address
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("insertion to table address with id :",new.id),"address");
       END;

CREATE TRIGGER log4  after insert ON peyk
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("insertion to table peyk with id :",new.id),"peyk");
       END;

CREATE TRIGGER log5  after insert ON menu
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("insertion to table menu with id :",new.id),"menu");
       END;

CREATE TRIGGER log6  after insert ON raw_material
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("insertion to table raw_material with id :",new.id),"raw_material");
       END;



CREATE TRIGGER log11  after delete ON customer
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("deletation to table customer with username :",OLD.user),"customer");
       END;

CREATE TRIGGER log21  after delete ON factor
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("deletation to factor address with id :",OLD.id),"factor");
       END;

CREATE TRIGGER log31  after delete ON address
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("deletation to table address with id :",OLD.id),"address");
       END;

CREATE TRIGGER log41  after delete ON peyk
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("deletation to table peyk with id :",OLD.id),"peyk");
       END;

CREATE TRIGGER log51  after delete ON menu
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("deletation to table menu with id :",OLD.id),"menu");
       END;

CREATE TRIGGER log61  after delete ON raw_material
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("deletation to table raw_material with id :",OLD.id),"raw_material");
       END;




CREATE TRIGGER log111  after update ON customer
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("update to table customer with username :",OLD.user,"and new username is :",new.user),"customer");
       END;

CREATE TRIGGER log211  after update ON factor
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("update to factor address with id :",OLD.id,"and new id is :",new.id),"factor");
       END;

CREATE TRIGGER log311  after update ON address
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("update to table address with id :",OLD.id,"and new id is :",new.id),"address");
       END;

CREATE TRIGGER log411  after update ON peyk
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("update to table peyk with id :",OLD.id,"and new id is :",new.id),"peyk");
       END;

CREATE TRIGGER log511  after update ON menu
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("update to table menu with id :",OLD.id,"and new id is :",new.id),"menu");
       END;

CREATE TRIGGER log611  after update ON raw_material
       FOR EACH ROW
       BEGIN
            insert into log(information, `mtable`)  values (concat("update to table raw_material with id :",OLD.id,"and new id is :",new.id),"raw_material");
       END;

