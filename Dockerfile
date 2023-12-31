FROM maven:3.8.2-jdk-8
LABEL authors="samanwita"

WORKDIR /ProductServiceMaven
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run

ENTRYPOINT ["top", "-b"]