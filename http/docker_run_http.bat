docker run -d ^
  --name docker-http ^
  --link docker-http-db:database ^
  --link docker-http2:rentOfficeService ^
  -e LOG_LEVEL=TRACE ^
  -e JDBC_URL=jdbc:mysql://database:3306/docker_db ^
  -e JDBC_USER=root ^
  -e JDBC_PASS=docker_pass ^
  -e RENT_OFFICE_SERVICE_URL=rentOfficeService:9090 ^
  -p 8080:8080 docker-http:1.0