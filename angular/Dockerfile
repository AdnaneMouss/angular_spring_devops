FROM nginx:alpine

# Copy Angular build files to the Nginx web root
COPY dist/angular /usr/share/nginx/html

# Expose port 80 for the application
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
