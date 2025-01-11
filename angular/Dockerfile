# Stage 1: Build the Angular app
FROM node:18.13-alpine AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Stage 2: Serve the Angular app using a lightweight HTTP server
FROM nginx:alpine
COPY --from=build-stage /app/dist/angular/browser /usr/share/nginx/html
EXPOSE 80


CMD ["nginx", "-g", "daemon off;"]






