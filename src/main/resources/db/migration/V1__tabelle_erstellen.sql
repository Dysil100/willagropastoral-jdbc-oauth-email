create table if not exists users
(
    id         serial primary key,
    first_name varchar(60) unique not null,
    last_name  varchar(60) unique not null,
    email      varchar(100)       not null,
    password   varchar(200)       not null,
    locked     boolean,
    enabled    boolean,
    role       varchar(40)        not null
);

create table if not exists confirmation_token
(
    id             serial primary key,
    token          varchar(200) not null,
    created_at     date,
    expired_at     date,
    confirmed_at   date,
    user_reference int
);

create table ingredient
(
    id                    serial primary key,
    name                  text not null,
    lysine                decimal,
    methyonine            decimal,
    proteine_brute        decimal,
    energie_metabolisable decimal
);

create table standard
(
    id                    serial primary key,
    description           text not null,
    lysine                decimal,
    methyonine            decimal,
    proteine_brute        decimal,
    energie_metabolisable decimal
);
