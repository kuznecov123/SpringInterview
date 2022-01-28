create table sessions
(
    id          bigserial primary key,
    film_id bigint references films (id),
    begin_film timestamp ,
    price       integer ,
);

create table films
(
    id          bigserial primary key,
    name         varchar(60) not null unique,
    duration    decimal,

);
create table tickets
(
    id          bigserial primary key,
    session_id bigint references sessions (id),
);
insert into films (id, name, duration)
values (1, 'film1', '60'),
       (2, 'film2', '90'),
       (3, 'film3', '120'),
       (4, 'film4', '60');

insert into tickets (id, session_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 3),
       (7, 3),
       (6, 4),
       (8, 4),
       (9, 5),
       (10, 5);
insert into sessions (id, film_id, begin_film, price)
values (1, 1, 2006-01-01 08:00:00 ,100),
       (2, 1, 2006-01-01 09:30:00,200),
       (3, 2, 2006-01-01 10:00:00,100),
       (4, 3, 2006-01-01 13:30:00,300),
       (5, 4, 2006-01-01 14:00:00,200);
