create table number_pair
(
    id   bigserial primary key,
    fizz integer not null,
    buzz integer not null
);

insert into number_pair
values (1, 3, 5);
