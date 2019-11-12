
# middleware frontend & server
FROM node:10 AS middleware-frontend
LABEL maintainer="Tim Tenckhoff tmtnckff@icloud.com"
WORKDIR /code
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

RUN mkdir -p vorto-middleware-frontend
RUN mkdir -p vorto-middleware-server


ADD ./frontend-server /code/vorto-middleware-server
ADD ./ /code/vorto-middleware-frontend

RUN npm i -g @angular/cli@latest

RUN cd /code/vorto-middleware-server && npm install
RUN cd /code/vorto-middleware-frontend && npm install --save-dev && ng build --prod --aot && npm audit fix

ENTRYPOINT cd /code/vorto-middleware-server  &&\
 node frontend-server.js -p 4200 --path ../vorto-middleware-frontend/dist

EXPOSE 4200 

