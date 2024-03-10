FROM node:18-alpine as frontend

WORKDIR /app

COPY package.json ./
RUN npm install
COPY . .
RUN npm run build:prod

FROM nginx:1.21.3-alpine as prod
COPY --from=frontend /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80