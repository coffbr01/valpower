FROM openjdk:17
COPY ./target /app
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "valpower-0.1.jar"]
