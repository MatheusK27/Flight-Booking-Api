# ✈️ Flight Booking API

🚧 Projeto em desenvolvimento

API REST para reserva de passagens aéreas com autenticação,
cadastro de voos, reservas e passageiros.

---

## 🚀 Tecnologias

| Tecnologia | Descrição |
|---|---|
| Java | Linguagem principal |
| Spring Boot | Framework backend |
| Spring Security | Segurança da aplicação |
| JWT | Autenticação |
| JPA / Hibernate | Persistência de dados |
| MySQL | Banco de dados |
| Lombok | Redução de boilerplate |

---

## ✅ Funcionalidades

- 🔐 Cadastro e autenticação com JWT
- ✈️ Cadastro de voos
- 🎫 Reserva de passagens
- 👤 Cadastro de passageiros
- 🛡️ Rotas protegidas por autenticação

---

## ⚙️ Como executar

### Clone o projeto
git clone https://github.com/MatheusK27/Flight-Booking-Api

### Configure o banco
spring.datasource.url=jdbc:mysql://localhost:3306/flight_booking
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}

### Execute
./mvnw spring-boot:run

---

## 👨‍💻 Autor
Matheus Klein
