docker run -d ^
  --name docker-http2 ^
  --link docker-http2-db:database2 ^
  -e LOG_LEVEL=TRACE ^
  -e JDBC_URL=jdbc:postgresql://database2:5432/docker_db ^
  -e JDBC_USER=docker_user ^
  -e JDBC_PASS=docker_pass ^
  -p 9090:9090 docker-http2:1.0