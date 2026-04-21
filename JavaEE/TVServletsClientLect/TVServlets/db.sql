CREATE TABLE tvs
(
    id             integer(11) primary key auto_increment,
    brand          varchar(30),
    model          varchar(30) unique,
    color          varchar(30),
    timeExpectancy integer(2),
    price          double(10, 2)
);

INSERT INTO tvs(brand, model, color, timeExpectancy, price)
VALUES ('Sumsung', 's100', 'black', 3, 5500.00),
       ('Sumsung', 't200', 'black', 3, 6500.00),
       ('Zarya', 'Z100', 'yellow', 5, 4500.00),
       ('Zarya', 'Z200', 'yellow', 5, 5500.00);
