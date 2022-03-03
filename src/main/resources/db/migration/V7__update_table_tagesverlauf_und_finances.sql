ALTER TABLE finance
    ADD project_name text;

ALTER TABLE tagesverlauf
    ADD project_name text;

alter table tagesverlauf
    rename column alveole to production;

