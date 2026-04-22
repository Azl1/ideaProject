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

# Quiz System

Backend для системы тестирования пользователей.

## 🚀 Стек
- Java
- Spring Boot
- JPA / Hibernate
- MySQL / PostgreSQL

## ⚙️ Функционал
- CRUD для вопросов и ответов
- Хранение результатов тестирования
- Подсчёт результатов
- REST API

## 📌 Примеры API
POST /quiz  
GET /questions  
POST /answers  
GET /results


# Telegram Bot

Telegram-бот для обработки пользовательских команд.

## 🚀 Стек
- Java
- Telegram Bot API

## ⚙️ Функционал
- Обработка команд (/start, /help)
- Ответы пользователю
- Интеграция с backend API
