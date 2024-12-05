# BookMyShow

A Spring Boot application for movie ticket booking with support for multiple databases, batch processing, and payment providers.

## Tech Stack

- **Framework**: Spring Boot 3.4.2
- **Java**: 21
- **Databases**: MongoDB (multiple databases), MySQL, Elasticsearch
- **Cache**: Redis
- **Batch**: Spring Batch
- **Cloud**: Spring Cloud OpenFeign

## Project Structure

```
src/main/java/com/bookMyShow/bookmyshow/
├── BatchConfig/       # Spring Batch configuration
├── config/            # Multi-database and Redis configurations
├── controller/        # REST controllers (Admin, User, Config)
├── dataloader/         # Data loading utilities
├── dto/                # Data transfer objects
├── entity/             # JPA/MongoDB entities
├── exceptions/         # Global exception handling
├── payment/            # Payment provider abstraction
├── repository/         # Data repositories
├── services/           # Business logic
│   └── serviceimplementation/
└── userfilter/         # User context filtering
```

## Configuration

Key properties in `application.properties`:
- MongoDB: `spring.data.mongodb.test.uri`, `spring.data.mongodb.test2.uri`
- Elasticsearch: `spring.data.elasticsearch.cluster-nodes`, `spring.data.elasticsearch.cluster-name`
- Redis Cache: `spring.cache.type`, `spring.cache.host`, `spring.cache.port`

## Build

```bash
./gradlew build
```

## Run

```bash
./gradlew bootRun
```

## API Context Path

`/book-my-show`

## Payment Providers

The application uses a factory pattern for payment providers:
- PaytmPaymentProvider
- PhonePePaymentProvider