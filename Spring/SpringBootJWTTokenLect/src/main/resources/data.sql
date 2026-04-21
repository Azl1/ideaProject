INSERT IGNORE INTO roles (id, created, status, updated, name)
VALUES (1, '2023-09-16 16:18:16', 'ACTIVE', '2023-09-16 16:18:32', 'ROLE_ADMIN');

INSERT IGNORE INTO users (id, created, status, updated, user_name, first_name, last_name, email, password)
values (1, '2023-09-16 16:18:16', 'ACTIVE', '2023-09-16 16:18:32','kirill', 'kirill', 'kotov','Kotov@email.ru', '$2a$10$IfRN9gGx.xFOVaI3513oIe5SGBmmhMAGEB4a1iy13x2b3Hc.n0Ua6');

INSERT IGNORE INTO user_roles(user_id, role_id) values (1, 1);
