create table users
(
    id        integer primary key auto_increment,
    name      varchar(40) not null,
    user_name varchar(10) not null unique,
    age       integer     not null,
    salary    double
);

insert into users(name, user_name, age, salary)
values ('Ivan', 'Iv', 10, 10000);
insert into users(name, user_name, age, salary)
values ('Petr', 'Pt', 20, 35000);
insert into users (name, user_name, age, salary)
values ('Roman', 'ROMA', 29, 56000);

create table cars
(
    id      integer primary key auto_increment,
    number  varchar(30) not null unique,
    brand   varchar(20) not null,
    model   varchar(20) not null,
    id_user integer     not null,
    foreign key (id_user) references users (id)
        on delete cascade on update cascade,
    unique (brand, model)
);

insert into cars(id, number, brand, model, id_user)
values (1, 536, 'Toyota', 'Corolla', 1);
insert into cars(id, number, brand, model, id_user)
values (2, 314, 'Volkswagen', 'Polo', 2);
insert into cars(id, number, brand, model, id_user)
values (3, 918, 'Renault', 'Megane', 3);
insert into cars(id, number, brand, model, id_user)
values (4, 389, 'Volkswagen', 'Tiguan', 1);

SELECT *
FROM users;

SELECT *
FROM cars;

SELECT *
FROM users
WHERE salary > 20000;

SELECT MIN(salary) AS minSalary
FROM users;

select *
from users
         join cars c on users.id = c.id_user;

WITH users_with_cars AS (select name, number
                         from users
                                  join cars c on users.id = c.id_user)
SELECT name, COUNT(number) AS number_of_cars
FROM users_with_cars
GROUP BY name;

select name, count(*) AS number_of_cars
from users
         join cars c on users.id = c.id_user
group by users.id
having number_of_cars = 2;

update cars set brand='Mercedes', model='Vito Tourer' where id=1;

SELECT *
FROM cars;

update users set age = age + 10 where age <= 20;

SELECT *
FROM users;

delete from users where salary < 15000;

SELECT *
FROM users;

delete from cars where brand != 'Volkswagen';

SELECT *
FROM cars;



