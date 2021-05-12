docker run -d ^
  --name docker-http2-db ^
  -p 5433:5432 ^
  -e POSTGRES_DB=docker_db ^
  -e POSTGRES_USER=docker_user ^
  -e POSTGRES_PASSWORD=docker_pass ^
  -v c:/Users/Sergey/IdeaProjects/kotlin-course/http2/src/main/resources/db-init:/docker-entrypoint-initdb.d/ ^
  postgres:11