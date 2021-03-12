FROM adoptopenjdk:11-jre-hotspot as builder
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8060
CMD echo Starting Gateway Docker Container
ENTRYPOINT ["java","-cp","app:app/lib/*","com.shell.siep.gto.services.GatewayApplication"]