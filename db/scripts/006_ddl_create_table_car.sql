create table car
(
    id serial primary key,
    name varchar not null,
    price varchar not null
    engine_id int references engine(id)
);