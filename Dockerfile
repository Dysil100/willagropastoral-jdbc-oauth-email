# syntax=docker/dockerfile:1
FROM gradle:7.3-jdk17 AS BUILD
WORKDIR /willagropastoral_build
COPY . /willagropastoral_build
RUN gradle bootJar

FROM eclipse-temurin:17-alpine
WORKDIR /willagropastoral
COPY --from=BUILD /willagropastoral_build/build/libs/willagropastoral-jdbc-oauth-email-0.0.1-SNAPSHOT.jar \
                  /willagropastoral/willagropastoral.jar
CMD java -jar analyse.jar