# Stage 1: Build the Angular app
FROM node:18.13 as build-stage

# Set the working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the entire project and build it
COPY . .
RUN npm run build --prod

# Stage 2: Serve the Angular app
FROM nginx:alpine

# Copy the Angular build files from the previous stage to the Nginx server
COPY --from=build-stage /app/dist/angular /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start the Nginx server
CMD ["nginx", "-g", "daemon off;"]
