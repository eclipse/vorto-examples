
# middleware frontend & server
FROM node:10 AS middleware-frontend
LABEL maintainer="Tim Tenckhoff tmtnckff@icloud.com"
RUN apt-get update
RUN apt-get install --no-install-recommends -y supervisor \
                                            curl\
                                            net-tools\
                                            mlocate\
                                            nano\
                                            vim\
                                            less\
                                            git\
                                            gnupg -yq


RUN node -v
RUN npm -v

RUN mkdir -p /usr/home/vorto-middleware-frontend
RUN mkdir -p /usr/home/vorto-middleware-server


COPY ./ /usr/home/vorto-middleware-frontend
COPY ./frontend-server /usr/home/vorto-middleware-server

RUN npm i -g @angular/cli@latest

RUN cd /usr/home/vorto-middleware-server && npm install
RUN cd /usr/home/vorto-middleware-frontend && npm install --save-dev && ng build --prod --aot && npm audit fix

ENTRYPOINT cd /usr/home/vorto-middleware-server  &&\
 node frontend-server.js -p 4200 --path ../vorto-middleware-frontend/dist

EXPOSE 4200 

