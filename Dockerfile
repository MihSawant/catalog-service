FROM eclipse-temurin:17 AS extract
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog-service.jar
RUN java -Djarmode=layertools -jar catalog-service.jar extract

FROM eclipse-temurin:17
RUN useradd toor
USER toor
WORKDIR workspace
COPY --from=extract workspace/dependencies/ ./
COPY --from=extract workspace/spring-boot-loader/ ./
COPY --from=extract workspace/snapshot-dependencies/ ./
COPY --from=extract workspace/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

