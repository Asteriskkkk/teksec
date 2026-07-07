# E-Commerce Microservices (Spring2-01)

This project contains:
- Auth Service
- Product Service
- Order Service
- Eureka Discovery Server
- Spring Cloud API Gateway

## Start Order
1. Start `discovery-server`
2. Start `auth-service`
3. Start `product-service`
4. Start `order-service`
5. Start `api-gateway`

## Main Gateway Routes
- `POST /auth/register`
- `POST /auth/login`
- `POST /products`
- `GET /products`
- `GET /products/{id}`
- `PUT /products/{id}/stock/{stock}`
- `POST /orders/rest-template`
- `POST /orders/webclient`
- `GET /orders`

## Notes
- Configure MySQL credentials in each service `application.yml`.
- Zipkin URL defaults to `http://localhost:9411/api/v2/spans`.
- Actuator endpoints exposed: `health`, `metrics`, `info`.
