create table if not exists finance
(
    id                    serial primary key,
    bezeichnung           text not null,
    description           text not null,
    summe                decimal not null,
    generated_at  varchar(20)         not null
);

