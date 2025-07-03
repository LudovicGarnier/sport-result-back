FROM openjdk:21-jdk-oracle

WORKDIR /app

# Copier le JAR déjà compilé
COPY target/*.jar app.jar

# Exposer le port
EXPOSE 8080

# Lancer l'application
CMD ["java", "-jar", "app.jar"]