# Currency Exchanger

A Spring Boot REST API for currency exchange operations using real-time exchange rates from the National Bank of Poland (NBP).

## Overview

Currency Exchanger fetches exchange rate data from the NBP API and caches it in memory. Users can:
- Convert amounts between different currencies
- Retrieve available currencies and their exchange rates
- Get specific currency information by code

The application converts currencies through Polish Zloty (PLN) as the base currency, using mid-rates from table A provided by NBP.

## Features

- **Currency Exchange**: Convert amounts between any supported currency
- **Request Validation**: Input validation with detailed error responses
- **Caching**: In-memory caching of NBP exchange rates (loaded at application startup)

## Technology Stack

- **Spring Boot**
- **Java 21**
- **Lombok**
- **MapStruct**
- **Spring Validation**
- **RestClient**
- **SLF4J**

## API Endpoints

- `POST /exchange` - Exchange currency amount
- `GET /currencies` - Get all available currencies
- `GET /currency?code={code}` - Get specific currency by code