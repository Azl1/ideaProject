INSERT IGNORE INTO users (id, login, surname, name, patronymic, password, reg_date)
VALUES (1, 'kirill', 'kirill', 'kotov', '1', '$2a$10$IfRN9gGx.xFOVaI3513oIe5SGBmmhMAGEB4a1iy13x2b3Hc.n0Ua6', '2025.08.16');

INSERT IGNORE INTO admins (id, email, salary)
VALUES (1, '1', 1);

INSERT IGNORE INTO users (id, login, surname, name, patronymic, password, reg_date)
VALUES (2, '222', '222', '222', '222', '$2a$10$xNVmO3qCIS.G5kett8qm7OXJVfeC4F.7iTpuj6z35nYjDVBOckq3a', '2025.08.17');

INSERT IGNORE INTO admins (id, email, salary)
VALUES (2, '2', 2);
