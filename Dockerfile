# ----------------------------------------------------------
# 🧩 Step 1: Build Angular frontend
# ----------------------------------------------------------
FROM node:22 AS frontend-build
WORKDIR /app/frontend
COPY frontend/ .
RUN npm install
RUN npm run build --prod   # ✅ This already outputs to backend/static

# ----------------------------------------------------------
# ⚙️ Step 2: Build Spring Boot backend
# ----------------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-17 AS backend-build
WORKDIR /app/backend
COPY backend/ .
RUN mvn clean package -DskipTests

# ----------------------------------------------------------
# 🚀 Step 3: Run final combined app
# ----------------------------------------------------------
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=backend-build /app/backend/target/codebytebackend.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

