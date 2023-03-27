create table auto_post
(
    id serial primary key,
    description varchar not null,
    crated timestamp,
    auto_user_id int references auto_user(id)
);