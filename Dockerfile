FROM gradle:7.3.3-jdk17-alpine AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build

FROM openjdk:17-alpine
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/cars-0.0.1.jar /app/app.jar

ENV POSTGRES_HOST localhost
ENV POSTGRES_DB db
ENV POSTGRES_USER user
ENV POSTGRES_PASSWORD password

EXPOSE 8080
CMD ["java","-jar", "/app/app.jar"]
