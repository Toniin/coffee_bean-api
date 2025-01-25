CREATE SEQUENCE IF NOT EXISTS roles_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS categories
(
    category_id serial primary key,
    name        VARCHAR(255) NOT NULL
);

create table IF NOT EXISTS products
(
    product_id     serial primary key,
    name           varchar(255) not null,
    description    varchar(255),
    price          double precision,
    image          varchar(255),
    stock          integer,
    fk_category_id integer
        constraint fk_category_id references categories
);

create table IF NOT EXISTS roles
(
    role_id     serial primary key,
    name        varchar(255) unique not null,
    description varchar(255)        not null,
    created_at  timestamp,
    updated_at  timestamp
);

create table IF NOT EXISTS users
(
    user_id    serial primary key,
    full_name  varchar(255)        not null,
    email      varchar(100) unique not null,
    password   varchar(255)        not null,
    created_at timestamp,
    updated_at timestamp,
    fk_role_id integer
        constraint fk_role_id references roles
);