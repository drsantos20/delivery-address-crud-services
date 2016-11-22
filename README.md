# delivery-address-crud-services
created by drsantos20 (Daniel Santos)

Technologies used:
- Maven (for management dependency)
- Spring Boot (container application)
- Junit (for tests)
- Java8
- Mock (for metadata information)
- Lombok

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


#### Walkthrough
- Configure Lombok

```sh
Download the jar -> lombok-1.14.6.jar.
Using command prompt go to java installed directory and type

java -jar ${your_jar_path}\lombok-1.14.6.jar.
Here ${your_jar_path} is your lombok-1.14.6.jar jar store directory.
After this it will prompt for Eclipse already installed in your system and you need to select where you want to integrate.
After this you need to open eclipse.ini file and make entry below

-vmargs
as
-Xbootclasspath/a:lombok.jar
-javaagent:lombok.jar

see more in http://stackoverflow.com/questions/22310414/how-to-configure-lombok-in-eclipse-luna

```