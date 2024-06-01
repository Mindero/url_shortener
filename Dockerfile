ARG JAVA_IMAGE=openjdk:17-oracle

FROM ${JAVA_IMAGE}
USER nobody
COPY build/libs/*.jar /opt/application/application.jar
WORKDIR /opt/application

ENTRYPOINT java $JAVA_OPTS -jar     /opt/application/application.jar $JAVA_ARGS