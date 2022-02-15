insert into users(id, first_name, last_name, email, password, locked, enabled, role)
VALUES (1, 'Silatsam', 'Dylan', 'silatsamdylan@gmail.com', '$2a$10$pyl6CgMFyP.nVBJ4lgCZLOcCq5zUPQipTXKGq54JJLe11CHCTDDRe', false, true, 'ROLE_ADMIN'),
       (2, 'Will', 'Agropastoral', 'willagropastoral@gmail.com', '$2a$10$iJG90J7mJIlnt3UCkAYTWurprc4SCDebKeYSe.yTM7rF.yIvPAcBe', false, true, 'ROLE_LEADER');
insert into confirmation_token(id, token, created_at, expired_at, confirmed_at, username)
VALUES (1, 'da1323c1-ccb4-408f-a315-86234c1726c4', '2022-02-15 02:23:50', '2022-02-15 02:38:50', '2022-02-15 02:24:11', 'silatsamdylan@gmail.com'),
       (2, '56e6634c-e8ea-4792-baec-5e6e904c0c69', '2022-02-15 02:53:12', '2022-02-15 03:08:12', '2022-02-15 02:53:30', 'willagropastoral@gmail.com');
insert into archiv(id, first_name, last_name, email, password)
VALUES (1, 'Silatsam', 'Dylan', 'silatsamdylan@gmail.com', 'programmierung2.0'),
       (2, 'Will', 'Agropastoral', 'willagropastoral@gmail.com', 'duo.cmr.willagropastoral');