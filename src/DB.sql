drop database if exists servlet_test_db;
create database servlet_test_db;
use servlet_test_db;

create table products
(
    id   bigint auto_increment primary key,
    name varchar(255) null
);

create table tariffs
(
    id         bigint auto_increment primary key,
    name       varchar(255) not null,
    product_id bigint       not null,
    price      decimal      null,
    constraint tariffs_ibfk_1
        foreign key (product_id) references products (id)
);


create table users
(
    id         bigint auto_increment primary key,
    first_name varchar(50)  null,
    last_name  varchar(50)  null,
    email      varchar(255) not null,
    password   varchar(255) not null,
    balance    decimal      null default 0,
    blocked    boolean      not null,
    role       varchar(10)  not null
);

create table orders
(
    id        bigint auto_increment primary key,
    user_id   bigint,
    tariff_id bigint,
    date_time datetime null,
    constraint orders_ibfk_1
        foreign key (user_id) references users (id),
    constraint orders_ibfk_2
        foreign key (tariff_id) references tariffs (id)
);

create index service_id
    on tariffs (product_id);

create index tariff_id
    on orders (tariff_id);

create index user_id
    on orders (user_id);

INSERT INTO servlet_test_db.users (first_name, last_name, email, password, balance, blocked, role)
VALUES ('admin', 'admin', 'admin@mail.com', '1', 0, 0,
        'ADMIN');

INSERT INTO servlet_test_db.users (first_name, last_name, email, password, balance, blocked, role)
VALUES ('user', 'user', 'user@mail.com', '1', 0, 0,
        'USER');

INSERT INTO servlet_test_db.products (name)
VALUES ('PHONE');
INSERT INTO servlet_test_db.products (name)
VALUES ('INTERNET');
INSERT INTO servlet_test_db.products (name)
VALUES ('TV');
INSERT INTO servlet_test_db.products (name)
VALUES ('IPTV');

