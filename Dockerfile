FROM openjdk:17
COPY ./target /usr/src/valpower
WORKDIR /usr/src/valpower
EXPOSE 8080/tcp
CMD ["java", "-jar", "valpower-0.1.jar"]
