docker run -d ^
  --name docker-http-db ^
  -p 3307:3306 ^
  -e MYSQL_DATABASE=docker_db ^
  -e MYSQL_ROOT_PASSWORD=docker_pass ^
  -v c:/Users/Sergey/IdeaProjects/kotlin-course/http/src/main/resources/db-init:/docker-entrypoint-initdb.d/ ^
  mysql:8.0.25