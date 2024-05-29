ARG JAVA_IMAGE=openjdk:17-oracle
ARG JAR_FILE=build/libs/*.jar

FROM ${JAVA_IMAGE}
WORKDIR /app

COPY ${JAR_FILE} /application.jar

ENTRYPOINT java $JAVA_OPTS -jar /app/application.jar $JAVA_ARGS