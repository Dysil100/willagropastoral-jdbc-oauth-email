create table if not exists avis
(
    id                    serial primary key,
    email           text not null,
    telephone           text not null,
    comment                text not null,
    generated_at  varchar(20)         not null
);

