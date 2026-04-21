package org.abdullaevaziz.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Student and Auto Management System API",
                description = "API для работы с студентами и автомобилями",
                version = "1.0.0",
                contact = @Contact(
                        name = "Abdullaev Aziz",
                        email = "abdullaev12@bk.ru",
                        url = "https://abdullaevaziz.dev"
                )
        )
)
public class OpenApiConfig {
}
