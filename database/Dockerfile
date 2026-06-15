FROM postgres:16-alpine

# Add any custom database initialization scripts here
COPY ./data.sql /docker-entrypoint-initdb.d/
