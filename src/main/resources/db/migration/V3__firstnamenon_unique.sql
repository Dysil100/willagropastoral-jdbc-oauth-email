alter table archiv
    alter column first_name  TYPE varchar (60) USING not null;
alter table archiv
    alter column email  TYPE  varchar  (100) USING  not null;
alter table users
    alter column email TYPE varchar (100) USING  not null;
alter table confirmation_token
    alter column username  TYPE varchar (100) USING  not null
