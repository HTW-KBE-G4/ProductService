# Product Service

# General Infos

This Spring Service is configured and build to run inside a docker-compose environment.
Furthermore this Service depends on a Connection to the [Warehouse-Backend-Service](https://github.com/HTW-KBE-G4/WarehouseService).

# How to run

First run the WarehouseService with given docker-compose configuration. Then run this Service with
```shell
docker-compose up -d
```
inside the repo root directory. Docker-compose will do following:
- build and instantiate the ProductService docker container based on the `dockerfile`.
- Instantiate a mysqlServer container
- inject the internal network created by the Warehouse environment. This is important because only then the ProductService can call the WarehouseServcie

This Service listens on RabbitMQ RPC-Queues descriped in this [file](src/main/java/de/tanukihardwarestore/ProductService/configurations/RabbitMQConfig.java).

The expected requests and results are located in thsi [folder](src/main/java/de/tanukihardwarestore/ProductService/rabbit).
