# Currency Exchanger

A Spring Boot REST API for currency exchange operations using real-time exchange rates from the National Bank of Poland (NBP).

## Overview

Currency Exchanger is a secure REST API that handles currency exchange operations with user authentication and role-based access control. The application fetches exchange rate data from the NBP API and caches it in memory.

### Core Functionality
- Secure user registration and login with JWT token authentication
- Convert amounts between different currencies
- Retrieve available currencies and their exchange rates
- Get specific currency information by code
- Role-based access control (USER, ADMIN)

The application converts currencies through Polish Zloty (PLN) as the base currency, using mid-rates from table A provided by NBP. All sensitive operations are protected with Spring Security and JWT tokens.

## Features

- **User Authentication**: Secure user registration and login with JWT tokens (1 hour expiration)
- **Role-Based Access Control**: Different permission levels for USER and ADMIN roles
- **Currency Exchange**: Convert amounts between any supported currency with real-time NBP rates
- **Request Validation**: Input validation with detailed error responses
- **Caching**: In-memory caching of NBP exchange rates (loaded at application startup)
- **Database Persistence**: PostgreSQL database for storing user accounts and roles
- **Security**: Spring Security integration with encrypted passwords

## Technology Stack

- **Spring Boot** 4.1.0
- **Java** 21
- **Spring Security** with JWT (JJWT 0.12.3)
- **PostgreSQL** - Database
- **Spring Data JPA with Hibernate** - ORM
- **Lombok** - Code generation
- **MapStruct** - Data mapping
- **Spring Validation** - Input validation
- **Spring WebMVC** - REST endpoints
- **SLF4J** - Logging

## API Endpoints

### Authentication
- `POST /auth/register` - Register a new user
- `POST /auth/login` - Login and receive JWT token

### Currency Exchange (Secured)
- `POST /api/exchange` - Exchange currency amount *(Requires: USER or ADMIN role)*
- `GET /api/currencies` - Get all available currencies *(Requires: ADMIN role)*
- `GET /api/currency?code={code}` - Get specific currency by code *(Requires: ADMIN role)*
