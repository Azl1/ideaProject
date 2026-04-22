# File Storage System (аналог Google Drive)

Backend-приложение для загрузки, хранения и управления файлами.

## 🚀 Стек
- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate (JPA)
- MySQL

## ⚙️ Функционал
- Регистрация и аутентификация пользователей (JWT)
- Роли пользователей (USER / ADMIN)
- Загрузка файлов (MultipartFile)
- Скачивание файлов
- Удаление файлов
- REST API (20+ endpoints)
- Валидация и обработка ошибок

## 📌 Примеры API

POST /auth/login  
POST /files/upload  
GET /files/{id}  
DELETE /files/{id}

## 🧠 Что реализовано
- Аутентификация через JWT
- Разделение на слои (Controller / Service / Repository)
- Работа с базой данных через JPA
- Обработка исключений

## ▶️ Запуск
1. Клонировать репозиторий
2. Настроить application.properties
3. Запустить Spring Boot приложение
