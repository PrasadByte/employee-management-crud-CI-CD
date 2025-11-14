# ---------- Stage 1: Build Angular app ----------
FROM node:20-alpine AS build
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the source code  #this is prasad
COPY . .

# Build the Angular app in production mode
RUN npm run build -- --configuration production

# ---------- Stage 2: Serve with Nginx ----------
FROM nginx:stable-alpine
# Copy build output from the previous stage
COPY --from=build /app/dist/inventry-project/browser /usr/share/nginx/html


# Expose port 80 (Nginx default)
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
