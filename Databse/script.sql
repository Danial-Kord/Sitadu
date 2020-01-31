create table if not exists sitadu_database.customer
(
    user       varchar(20) not null
        primary key,
    pass       varchar(30) not null,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    melli_code char(10)    not null,
    phone      char(7)     null,
    age        int(3)      null
);

create table if not exists sitadu_database.address
(
    id                 int auto_increment
        primary key,
    name               varchar(20) null,
    address            text        null,
    house_phone_number char(7)     null,
    customer_id        varchar(20) null,
    constraint address_ibfk_1
        foreign key (customer_id) references sitadu_database.customer (user)
            on update cascade on delete cascade
);

create index customer_id
    on sitadu_database.address (customer_id);

create trigger sitadu_database.log3
    after INSERT
    on sitadu_database.address
    for each row
BEGIN
    insert into log(information, `mtable`) values (concat("insertion to table address with id :", new.id), "address");
END;

create trigger sitadu_database.log31
    after DELETE
    on sitadu_database.address
    for each row
BEGIN
    insert into log(information, `mtable`) values (concat("deletation to table address with id :", OLD.id), "address");
END;

create trigger sitadu_database.log311
    after UPDATE
    on sitadu_database.address
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("update to table address with id :", OLD.id, "and new id is :", new.id), "address");
END;

create trigger sitadu_database.log
    after INSERT
    on sitadu_database.customer
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("insertion to table customer with username :", new.user), "customer");
END;

create trigger sitadu_database.log11
    after DELETE
    on sitadu_database.customer
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("deletation to table customer with username :", OLD.user), "customer");
END;

create trigger sitadu_database.log111
    after UPDATE
    on sitadu_database.customer
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("update to table customer with username :", OLD.user, "and new username is :", new.user),
            "customer");
END;

create table if not exists sitadu_database.log
(
    id          int(30) auto_increment
        primary key,
    information text                               null,
    mtable      varchar(20)                        null,
    time        datetime default CURRENT_TIMESTAMP null
);

create table if not exists sitadu_database.menu
(
    id    int auto_increment
        primary key,
    name  varchar(30) null,
    price int         null
);

create trigger sitadu_database.log5
    after INSERT
    on sitadu_database.menu
    for each row
BEGIN
    insert into log(information, `mtable`) values (concat("insertion to table menu with id :", new.id), "menu");
END;

create trigger sitadu_database.log51
    after DELETE
    on sitadu_database.menu
    for each row
BEGIN
    insert into log(information, `mtable`) values (concat("deletation to table menu with id :", OLD.id), "menu");
END;

create trigger sitadu_database.log511
    after UPDATE
    on sitadu_database.menu
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("update to table menu with id :", OLD.id, "and new id is :", new.id), "menu");
END;

create table if not exists sitadu_database.peyk
(
    id         int auto_increment
        primary key,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    phone      char(7)     null
);

create table if not exists sitadu_database.factor
(
    id          int auto_increment
        primary key,
    customer_id varchar(20)                         null,
    name        char(30)                            null,
    time        timestamp default CURRENT_TIMESTAMP null,
    peyk_id     int                                 null,
    total_price varchar(50)                         null,
    constraint factor_ibfk_1
        foreign key (peyk_id) references sitadu_database.peyk (id)
            on update cascade on delete set null,
    constraint factor_ibfk_2
        foreign key (customer_id) references sitadu_database.customer (user)
            on update cascade on delete cascade
);

create index customer_id
    on sitadu_database.factor (customer_id);

create index peyk_id
    on sitadu_database.factor (peyk_id);

create trigger sitadu_database.log2
    after INSERT
    on sitadu_database.factor
    for each row
BEGIN
    insert into log(information, `mtable`) values (concat("insertion to factor address with id :", new.id), "factor");
END;

create trigger sitadu_database.log21
    after DELETE
    on sitadu_database.factor
    for each row
BEGIN
    insert into log(information, `mtable`) values (concat("deletation to factor address with id :", OLD.id), "factor");
END;

create trigger sitadu_database.log211
    after UPDATE
    on sitadu_database.factor
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("update to factor address with id :", OLD.id, "and new id is :", new.id), "factor");
END;

create table if not exists sitadu_database.menu_factor
(
    id        int auto_increment
        primary key,
    factor_id int         null,
    food_id   int         null,
    food_name varchar(30) null,
    price     int         null,
    constraint menu_factor_ibfk_1
        foreign key (factor_id) references sitadu_database.factor (id)
            on update cascade on delete cascade,
    constraint menu_factor_ibfk_2
        foreign key (food_id) references sitadu_database.menu (id)
            on update cascade on delete cascade
);

create index factor_id
    on sitadu_database.menu_factor (factor_id);

create index food_id
    on sitadu_database.menu_factor (food_id);

create trigger sitadu_database.log4
    after INSERT
    on sitadu_database.peyk
    for each row
BEGIN
    insert into log(information, `mtable`) values (concat("insertion to table peyk with id :", new.id), "peyk");
END;

create trigger sitadu_database.log41
    after DELETE
    on sitadu_database.peyk
    for each row
BEGIN
    insert into log(information, `mtable`) values (concat("deletation to table peyk with id :", OLD.id), "peyk");
END;

create trigger sitadu_database.log411
    after UPDATE
    on sitadu_database.peyk
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("update to table peyk with id :", OLD.id, "and new id is :", new.id), "peyk");
END;

create table if not exists sitadu_database.raw_material
(
    id    int auto_increment
        primary key,
    name  varchar(30) null,
    price int         null
);

create trigger sitadu_database.log6
    after INSERT
    on sitadu_database.raw_material
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("insertion to table raw_material with id :", new.id), "raw_material");
END;

create trigger sitadu_database.log61
    after DELETE
    on sitadu_database.raw_material
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("deletation to table raw_material with id :", OLD.id), "raw_material");
END;

create trigger sitadu_database.log611
    after UPDATE
    on sitadu_database.raw_material
    for each row
BEGIN
    insert into log(information, `mtable`)
    values (concat("update to table raw_material with id :", OLD.id, "and new id is :", new.id), "raw_material");
END;


