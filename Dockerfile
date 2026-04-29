FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy everything
COPY . .

# Build inside container
RUN ./mvnw clean package

# Run jar
ENTRYPOINT ["java", "-jar", "target/*.jar"]
