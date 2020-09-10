FROM maven:3.6.3-jdk-11 as builder

RUN mkdir -p /source

WORKDIR /source

ADD pom.xml /source
RUN mvn verify clean --fail-never

ARG TOKEN_SECRET=\b4JyfbqGhrjY)r
ARG JAR_NAME=users-registry.jar

ENV TOKEN_SECRET=${TOKEN_SECRET}
ENV JAR_NAME=${JAR_NAME}

ADD . /source

RUN mvn package

EXPOSE 8080

CMD java -jar target/${JAR_NAME}
