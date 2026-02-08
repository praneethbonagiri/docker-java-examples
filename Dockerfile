FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY src/main/Main.java .

# Download PostgreSQL JDBC driver
ADD https://jdbc.postgresql.org/download/postgresql-42.7.3.jar postgresql.jar

RUN javac Main.java

CMD ["java", "-cp", ".:postgresql.jar", "Main"]