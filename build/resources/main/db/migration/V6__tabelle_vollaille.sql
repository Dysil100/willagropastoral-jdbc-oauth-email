create table if not exists tagesverlauf
(
    id                    serial primary key,
    alveole           decimal not null,
    consommation           decimal not null,
    appreciation                text not null,
    date_time  varchar(20)         not null
);

