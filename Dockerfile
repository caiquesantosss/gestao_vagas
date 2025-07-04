# Etapa de build
FROM eclipse-temurin:21-jdk AS build
LABEL authors="Caíque Santos"

WORKDIR /app

# Copia o pom.xml e baixa dependências para aproveitar cache
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline

# Copia o restante do projeto
COPY . .

# Compila o projeto
RUN mvn clean install

FROM eclipse-temurin:21-jdk AS runtime
EXPOSE 8080

WORKDIR /app
COPY --from=build /app/target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
