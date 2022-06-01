FROM adoptopenjdk/openjdk11:alpine-slim

WORKDIR /app
COPY ./target/employee.jar  /app

ENTRYPOINT [ "java", "-jar", "/app/employee.jar" ]