FROM maven:3.6.3-jdk-11 as builder
COPY . /source
WORKDIR /source
RUN mvn install

FROM openjdk:11
ARG TOKEN_SECRET=\b4JyfbqGhrjY)r
ENV TOKEN_SECRET=${TOKEN_SECRET}
ARG JAR_NAME=users-registry
COPY --from=builder /source/target /app
WORKDIR /app
CMD java -jar ./${JAR_NAME}
