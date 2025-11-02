# ----------------------------------------------------------
# 🧩 Step 1: Build Angular frontend
# ----------------------------------------------------------
FROM node:22 AS frontend-build
WORKDIR /app/frontend
COPY frontend/ .
RUN npm install
RUN npm run build --prod

# ----------------------------------------------------------
# ⚙️ Step 2: Build Spring Boot backend (with Angular assets)
# ----------------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-17 AS backend-build
WORKDIR /app/backend
COPY backend/ .

# ✅ Copy Angular build into Spring Boot static resources
COPY --from=frontend-build /app/frontend/dist/empnew ./src/main/resources/static/

RUN mvn clean package -DskipTests

# ----------------------------------------------------------
# 🚀 Step 3: Run final combined app
# ----------------------------------------------------------
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=backend-build /app/backend/target/codebytebackend.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
