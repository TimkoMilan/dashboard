FROM  java:openjdk-8u111-jdk-alpine

COPY sovy-dashboard-core.jar /usr/sovy/core.jar
WORKDIR /usr/sovy

CMD ["java", \
    "-jar", "/usr/sovy/core.jar"]