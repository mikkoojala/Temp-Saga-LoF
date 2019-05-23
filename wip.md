# Setup etc

## Messaging server

docker pull rmohr/activemq:5.15.6-alpine

docker run -p 61616:61616 -p 8161:8161 --name msgserver rmohr/activemq:5.15.6-alpine

