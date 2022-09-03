# build environment
FROM node:13.12.0-alpine as build
WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY Frontend/package.json ./
COPY Frontend/package-lock.json ./
RUN npm ci --silent
RUN npm install react-scripts@3.4.1 -g --silent

COPY . ./
#RUN npm run build
EXPOSE 3000
CMD ["npm", "start"]

# production environment
#FROM nginx:stable-alpine
#COPY --from=build ./app/build /usr/share/nginx/html
#COPY Frontend/nginx.conf /etc/nginx/nginx.conf
#NEW VERSION
#COPY Frontend/nginx.conf /etc/nginx/conf.d/default.conf
#COPY --from=build ./build /usr/share/nginx/html
#EXPOSE 3000
#CMD ["nginx", "-g", "daemon off;"]
