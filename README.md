# delivery-address-crud-services

Technologies used:
- Maven (for management dependency)
- Spring Boot (container application)
- Junit (for tests)
- Java8
- Mock (for metadata information)

This operation involves the following steps (1-create, 2-update, 3-get and 4-delete) and can be executed defined below:


#### Create

```sh
curl -H "Accept: application/json" -H "Content-Type: application/json"  -X POST -d '{"street":"Rua 2","number":"02","zipCodeNumber":"06753161","city":"Abc","state":"RJ"}' http://localhost:8090/
```

#### Update

```sh
curl -H "Accept: application/json" -H "Content-Type: application/json"  -X PUT -d '{"id":"51813861-cf5e-449b-b622-ecfe55e59543","street":"palm parkway","number":"55555","zipCodeNumber":"06753163","neighbourhood":null,"city":"miami","state":"FL","extraField":null}' http://localhost:8090/
```

#### Get

```sh
curl -H "Accept: application/json" -H "Content-Type: application/json"  -X GET http://localhost:8090/51813861-cf5e-449b-b622-ecfe55e59543
```

#### Delete

```sh
curl -H "Accept: application/json" -H "Content-Type: application/json"  -X DELETE http://localhost:8090/51813861-cf5e-449b-b622-ecfe55e59543
```