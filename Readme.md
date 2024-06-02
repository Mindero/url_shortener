# url_shortener

## to create jar file
```
./gradlew bootJar
```
## to run docker:
```
docker-compose rm\
docker-compose --env-file .\local_var\local.env up
```
## to check postgres:
```
docker exec -it db psql -U user -d url
```