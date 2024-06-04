FROM postgres:14-alpine
COPY src/main/resources/sql/init.sql /docker-entrypoint-initdb.d/
RUN echo "database"