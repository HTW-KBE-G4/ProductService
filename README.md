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

# API
## Endpoint

Server Port: ```4200```

```
# Product API

## Get all products
GET /product

## Get specific product
GET /product/{id}


# Component API

## Get all components
GET /component

## Get specific component
GET /component/{id}
```

## JSON  Body

Example for component with id 19:
```
GET /components/19 
```

```json5
{
  "product_id": 19,
  "name": "Creative Allrounder",
  "components": [
    {
       "component_id": 4,
       "type": "GPU",
       "model": "GT 1030",
       "description": "Beschleunigen Sie Ihr gesamtes PC-Erlebnis mit der schnellen, leistungsstarken NVIDIA GeForce GT 1030-Grafikkarte.",
       "manufacturer": "MSI",
       "releaseDate": "2018",
       "uvp": 142.78,
       "weight": 400,
       "productName": "2GB MSI GeForce GT 1030 OC",
       "ean": 4719072561413,
       "imageURL": "https://pixabay.com/get/...007ce12a5306009b_1920.jpg" 
    },
    {/*...*/},
    {/*...*/},
    {/*...*/}    
  ] 
}

```