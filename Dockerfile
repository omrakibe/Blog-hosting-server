FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

# Rename jar to fixed name
RUN mv target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
