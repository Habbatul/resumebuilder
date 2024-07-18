FROM eclipse-temurin:21-jre-alpine
COPY /target/resumebuilder-0.0.1-SNAPSHOT.jar /app/resumebuilder.jar
CMD ["java", "-jar", "/app/resumebuilder.jar"]
