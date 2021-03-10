#FROM openjdk:11-jdk-alpine
FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=./build/libs/gateway-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8060
ENTRYPOINT ["java","-cp","app:app/lib/*","com.shell.siep.gto.services.GatewayApplication"]

