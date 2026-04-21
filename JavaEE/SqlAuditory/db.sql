create table auditory
(
    id                    integer primary key auto_increment,
    number_a              integer not null,
    total_price_equipment double default 0
);

insert into auditory(number_a)
values (1);
insert into auditory(number_a)
values (2);
insert into auditory(number_a)
values (3);
insert into auditory(number_a)
values (4);
insert into auditory(number_a)
values (5);

create table worker
(
    id    integer primary key auto_increment,
    fio   varchar(30) not null unique,
    phone varchar(30) not null unique
);

insert into worker(fio, phone)
values ('Иванов Иван Иванович', '1234578');
insert into worker(fio, phone)
values ('Петров Петр Петрович', '8765321');
insert into worker(fio, phone)
values ('Сидоров Сидор Сидорович', '7456275');
insert into worker(fio, phone)
values ('Березин Костя Константинович', '85471145');
insert into worker(fio, phone)
values ('Алекс Александр Александрович', '4158635');

create table accounding_book
(
    id        integer primary key auto_increment,
    id_a      integer  not null,
    id_w      integer  not null,
    note_numb integer  not null unique,
    note_date DATETIME not null,
    foreign key (id_a) references auditory (id)
        on delete cascade on update cascade,
    foreign key (id_w) references worker (id)
        on delete cascade on update cascade,
    unique (id_a, id_w)
);

insert into accounding_book(id_a, id_w, note_numb, note_date)
values (1, 1, 101, '2020-01-21 00:00:00');
insert into accounding_book(id_a, id_w, note_numb, note_date)
values (2, 2, 202, '2020-02-21 00:00:00');
insert into accounding_book(id_a, id_w, note_numb, note_date)
values (3, 3, 303, '2020-03-21 00:00:00');
insert into accounding_book(id_a, id_w, note_numb, note_date)
values (4, 4, 404, '2020-04-21 00:00:00');
insert into accounding_book(id_a, id_w, note_numb, note_date)
values (5, 5, 505, '2020-05-21 00:00:00');


insert into accounding_book(id_a, id_w, note_numb, note_date)
values (4, 1, 123, '2020-07-21 00:00:00');
insert into accounding_book(id_a, id_w, note_numb, note_date)
values (5, 1, 456, '2020-08-21 00:00:00');

insert into accounding_book(id_a, id_w, note_numb, note_date)
values (1, 3, 789, '2020-09-21 00:00:00');
insert into accounding_book(id_a, id_w, note_numb, note_date)
values (4, 3, 987, '2020-10-21 00:00:00');

insert into accounding_book(id_a, id_w, note_numb, note_date)
values (1, 2, 888, '2020-04-21 00:00:00');

create table equipment
(
    id     integer primary key auto_increment,
    name_e varchar(30) not null unique,
    price  double      not null
);

insert into equipment(name_e, price)
values ('Хрен моржовый', 8000);
insert into equipment(name_e, price)
values ('Стол', 9000);
insert into equipment(name_e, price)
values ('Стул', 10000);
insert into equipment(name_e, price)
values ('Парта', 12000);
insert into equipment(name_e, price)
values ('Кресло', 15000);

create table complectation_auditory
(
    id      integer primary key auto_increment,
    id_a    integer not null,
    id_e    integer not null,
    count_e integer not null,
    foreign key (id_a) references auditory (id)
        on delete cascade on update cascade,
    foreign key (id_e) references equipment (id)
        on delete cascade on update cascade,
    unique (id_a, id_e)
);

insert into complectation_auditory(id_a, id_e, count_e)
values (1, 1, 1);
insert into complectation_auditory(id_a, id_e, count_e)
values (2, 2, 2);
insert into complectation_auditory(id_a, id_e, count_e)
values (3, 3, 3);
insert into complectation_auditory(id_a, id_e, count_e)
values (4, 4, 4);
insert into complectation_auditory(id_a, id_e, count_e)
values (5, 5, 5);

/*
  добавить к 1 аудитории 3 2 оборуд
  2 аудит 1 и 4 и 5
  3 аудит 1 и 5
 */
insert into complectation_auditory(id_a, id_e, count_e)
values (1, 3, 6);
insert into complectation_auditory(id_a, id_e, count_e)
values (1, 2, 7);

insert into complectation_auditory(id_a, id_e, count_e)
values (2, 4, 8);
insert into complectation_auditory(id_a, id_e, count_e)
values (2, 5, 9);

insert into complectation_auditory(id_a, id_e, count_e)
values (3, 1, 10);
insert into complectation_auditory(id_a, id_e, count_e)
values (3, 5, 11);


/**
  •	Показать какие сотрудники заказывали какие аудитории
 */
SELECT *
FROM accounding_book ab
         JOIN worker w on ab.id_w = w.id
         JOIN auditory a on ab.id = a.id;

/**
  •	Показать какие аудитории заказывал сотрудник с ФИО = ?
 */
SELECT *
FROM accounding_book ab
         JOIN worker w on ab.id_w = w.id
         JOIN auditory a on ab.id = a.id
WHERE w.fio = ?;

/**
  •	Поменять ФИО в таблице сотрудник
 */
update worker
set fio = 'Пукин Иван Иваныч'
WHERE id = 1;

/**
  показать сколько аудиторий взял каждый человек
  вывести фио сотрудника и справа количество аудиторий сколько он взял
 */
SELECT fio, COUNT(number_a) AS count_of_auditory
FROM worker w
         JOIN accounding_book ab on w.id = ab.id_a
         JOIN auditory a on a.id = ab.id_a
GROUP BY fio;

/**
  •	Определить количество аудиторий, сколько взял
сотрудник с заданным ФИО
 */
SELECT fio, COUNT(number_a) AS count_of_auditory
FROM worker w
         JOIN accounding_book ab on w.id = ab.id_w
         JOIN auditory a on a.id = ab.id_a
GROUP BY fio
having w.fio = ?;

/**
  •	Определение всего оборудования, которое по стоимости такое же,
  как заданное
 */
SELECT*
FROM equipment
WHERE price = ?;

/**
  вывести какое количество записей
  максимальное у какого сотрудника вывести только 1 число
 */
select max(count_accounding_book) AS max_accounding_book
from (SELECT COUNT(ab.id) AS count_accounding_book
      FROM worker w
               JOIN accounding_book ab on w.id = ab.id_w
               JOIN auditory a on a.id = ab.id_a
      GROUP BY w.id) as t1;

/**
  •	Вывести имя одного любого сотрудника,
  у которого количество записей максимально
 */
SELECT w.fio
FROM worker w
         join accounding_book ab on w.id = ab.id_w
GROUP BY w.id
ORDER BY COUNT(ab.id) DESC
LIMIT 1;

/**
  •	Вывести всех сотрудников, у которых количество записей максимально
 */
select max(count_accounding_book) AS max_accounding_book
from (SELECT COUNT(ab.id) AS count_accounding_book
      FROM worker w
               JOIN accounding_book ab on w.id = ab.id_w
               JOIN auditory a on a.id = ab.id_a
      GROUP BY w.id) as t1;

SELECT fio, COUNT(note_numb) AS count
FROM worker w
         JOIN accounding_book ab on w.id = ab.id_w
         JOIN auditory a on a.id = ab.id_a
GROUP BY fio
having count = (select max(count_accounding_book) AS max_accounding_book
                from (SELECT COUNT(ab.id) AS count_accounding_book
                      FROM worker w
                               JOIN accounding_book ab on w.id = ab.id_w
                               JOIN auditory a on a.id = ab.id_a
                      GROUP BY w.id) as t1);

/**
  •	Вывести номер одной любой самой используемой аудитории
 */


SELECT w.id, COUNT(id_a) AS count_auditory
FROM worker w
         JOIN accounding_book ab on w.id = ab.id_a
         JOIN auditory a on a.id = ab.id_a
GROUP BY w.id
HAVING count_auditory = (SELECT MAX(count_numbers_auditory) AS max_numbers_auditory
                         FROM (SELECT COUNT(id_a) AS count_numbers_auditory
                               FROM worker w
                                        JOIN accounding_book ab on w.id = ab.id_a
                                        JOIN auditory a on a.id = ab.id_a
                               GROUP BY w.id) as tabl1)
LIMIT 1;

/**
•	Вывести имя одного любого сотрудника, у которого количество записей максимально
 */
SELECT fio, COUNT(fio) AS note_count
FROM worker w
         JOIN accounding_book ab on w.id = ab.id_w
         JOIN auditory a on a.id = ab.id_a
GROUP BY fio
HAVING note_count = (SELECT MAX(count_note_max) AS max_note
                     FROM (SELECT COUNT(w.fio) AS count_note_max
                           FROM worker w
                                    JOIN accounding_book b on w.id = b.id_w
                                    JOIN auditory a on a.id = b.id_a
                           GROUP BY w.fio) AS tabl2)
LIMIT 1;

/**
  •	Вывести сотрудников, которые заказывали аудитории с наименьшей ценой
  (total_price_equipment) оборудования в них
 */
SELECT w.fio, a.total_price_equipment
FROM worker w
         JOIN accounding_book ab ON w.id = ab.id_w
         JOIN auditory a ON ab.id_a = a.id
WHERE a.total_price_equipment = (SELECT MIN(total_price_equipment)
                                 FROM auditory);


/**
  •	Поменять параметры аудитории(update)
 */
update auditory
SET number_a              = '101',
    total_price_equipment = 5000
WHERE id = 1;