
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

ADD . vorto-middleware-frontend

RUN npm i -g @angular/cli@latest
RUN cd vorto-middleware-frontend/frontend-server && npm install
RUN cd vorto-middleware-frontend && npm install --save-dev && ng build --prod --aot && npm audit fix

ENTRYPOINT cd vorto-middleware-frontend/frontend-server  &&\
 node frontend-server.js -p 4200 --path ../dist

EXPOSE 4200 

