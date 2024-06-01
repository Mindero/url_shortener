FROM postgres:latest
COPY src/main/resources/sql/init.sql /docker-entrypoint-initdb.d/
RUN echo "database"