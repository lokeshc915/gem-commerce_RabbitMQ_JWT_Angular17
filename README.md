# GEM Commerce Microservices (No Docker) — Angular 17 + Spring Boot 3 + RabbitMQ + JWT

This repo is a **complete, runnable** reference project demonstrating:
- Angular 17: **routing, guard, resolver, interceptor, custom directive, custom pipe**
- Spring Boot 3 microservices: **gateway + order + inventory + product + notification**
- RabbitMQ: **domain events** (publish/consume)
- JWT security: **gateway issues JWT**, all services **validate JWT**

## Ports
- gateway-service: **8080**
- order-service: **8081**
- inventory-service: **8082**
- product-service: **8083**
- notification-service: **8084**
- Angular UI: **4200**

## Prerequisites
- Java 17+, Maven 3.9+
- Node 18+
- RabbitMQ running locally (default guest/guest):
  - AMQP: `localhost:5672`
  - UI: `http://localhost:15672`

## Start backend
```bash
mvn -q -DskipTests clean package
mvn -pl services/gateway-service spring-boot:run
mvn -pl services/order-service spring-boot:run
mvn -pl services/inventory-service spring-boot:run
mvn -pl services/product-service spring-boot:run
mvn -pl services/notification-service spring-boot:run
```

## Start UI
```bash
cd gateway-ui
npm install
npm start
```
Open `http://localhost:4200`.

## Login users
- `admin / admin123` (roles: ADMIN, USER)
- `user / user123` (roles: USER)

## Diagrams
See `docs/diagrams/*.mmd` (Mermaid):
- architecture
- event topology
- order happy-path sequence
- order compensation sequence
- UI navigation flow
