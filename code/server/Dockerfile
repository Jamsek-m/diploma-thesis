FROM openjdk:11-jre-slim

LABEL maintainer="miha_jamsek@windowslive.com"

ENV JAVA_ENV=PRODUCTION
ENV KUMULUZEE_ENV_NAME=prod
ENV KUMULUZEE_ENV_PROD=true
ENV KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://localhost:5432/metrics-monitor
ENV KUMULUZEE_DATASOURCES0_USERNAME=postgres
ENV KUMULUZEE_DATASOURCES0_PASSWORD=postgres
ENV KUMULUZEE_LOGS_LOGGERS0_LEVEL=INFO

RUN mkdir /app
WORKDIR /app

ADD ./api/target/metrics-monitor.jar /app

EXPOSE 8080

CMD ["java", "-jar", "metrics-monitor.jar"]
