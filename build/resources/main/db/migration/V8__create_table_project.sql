create table if not exists project(
    id                    serial primary key,
    name          text not null,
    start_date          text not null,
    end_date          text
);
