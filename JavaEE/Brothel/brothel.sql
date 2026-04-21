create table clients
(
    id integer primary key auto_increment,
    fio varchar(100) not null unique ,
    numberTel integer not null unique ,
    age integer not null,
    preferences varchar(100)  not null
);

create table prostitutes
(
    id integer primary key auto_increment,
    fio varchar(100) not null unique ,
    age integer not null,
    weight integer not null,
    specialization varchar(100)  not null,
    pricePerHour double precision not null,
    id_cl integer not null,
    foreign key (id_cl) references clients (id)
    on delete cascade on update cascade
);

insert into clients(fio, numberTel, age, preferences)
VALUES ('Igor', 1234567, 25, 'Предрочитает БДСМ');


select * from clients where clients.id = ?
