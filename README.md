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


##  Architecture Diagram
flowchart LR
  U[User] --> UI[Angular 17 UI :4200]
  UI -->|REST + JWT| GW[gateway-service :8080]

  GW -->|REST + JWT| OS[order-service :8081]
  GW -->|REST + JWT| IS[inventory-service :8082]
  GW -->|REST + JWT| PS[product-service :8083]
  GW -->|REST + JWT| NS[notification-service :8084]

  subgraph MQ[RabbitMQ :5672]
    EX[Exchange: domain.events]
    QI[Queue: inventory.q]
    QN[Queue: notification.q]
  end

  OS -->|order.created| EX
  IS -->|inventory.reserved / inventory.rejected| EX
  EX --> QI --> IS
  EX --> QN --> NS
  



##  Event topology Diagram
flowchart LR
  U[User] --> UI[Angular 17 UI :4200]
  UI -->|REST + JWT| GW[gateway-service :8080]

  GW -->|REST + JWT| OS[order-service :8081]
  GW -->|REST + JWT| IS[inventory-service :8082]
  GW -->|REST + JWT| PS[product-service :8083]
  GW -->|REST + JWT| NS[notification-service :8084]

  subgraph MQ[RabbitMQ :5672]
    EX[Exchange: domain.events]
    QI[Queue: inventory.q]
    QN[Queue: notification.q]
  end

  OS -->|order.created| EX
  IS -->|inventory.reserved / inventory.rejected| EX
  EX --> QI --> IS
  EX --> QN --> NS
  
  
 ## order compensation sequence Diagram
 sequenceDiagram
 
  autonumber
  participant UI as Angular UI
  participant GW as Gateway
  participant OS as Order Service
  participant MQ as RabbitMQ
  participant IS as Inventory Service

  UI->>GW: POST /api/proxy/orders
  GW->>OS: create order
  OS->>MQ: order.created
  MQ-->>IS: order.created
  IS->>MQ: inventory.rejected(out_of_stock)

  UI->>GW: POST /api/proxy/orders/{id}/cancel
  GW->>OS: cancel
  OS-->>UI: CANCELLED
  
  
 ## order-happy-path sequence Diagram
sequenceDiagram
  autonumber
  participant UI as Angular UI
  participant GW as Gateway
  participant OS as Order Service
  participant MQ as RabbitMQ
  participant IS as Inventory Service
  participant NS as Notification Service

  UI->>GW: POST /api/proxy/orders JWT
  GW->>OS: POST /api/orders
  OS-->>GW: 201 CREATED
  OS->>MQ: publish order.created

  MQ-->>IS: deliver order.created
  IS->>MQ: publish inventory.reserved or inventory.rejected

  MQ-->>NS: deliver events
  NS-->>NS: store notifications
  
  
 ## UI NAVIGATION
flowchart TD
  L[Login] --> S[Shell-Layout]
  S --> O[Orders List]
  O --> OD[Order Detail -Resolver]
  S --> I[Inventory]
  S --> P[Products]
  S --> N[Notifications]