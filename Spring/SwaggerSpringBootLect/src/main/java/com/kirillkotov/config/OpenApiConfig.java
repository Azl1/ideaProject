package com.kirillkotov.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Users with TVs System Api",
                description = "API для работы с пользователями и телевизорами",
                version = "1.0.0",
                contact = @Contact(
                        name = "Kotov Kirill",
                        email = "kotovit-soft@mail.ru",
                        url = "https://kirillkotov.dev"
                )
        )
)
public class OpenApiConfig {
}
