# Setup etc

## Messaging server

docker pull rmohr/activemq:5.15.6-alpine

docker run -p 61616:61616 -p 8161:8161 --name msgserver rmohr/activemq:5.15.6-alpine

## Msg server admin UI:
http://localhost:8161/admin/browse.jsp?JMSDestination=payment

## Send payment message with rest interface:
http://127.0.0.1:8166/greeting (port is dynamic)

### Send payment message through LOAD BALANCER
http://127.0.0.1:8762/theapplication/greeting

#### LB routes
http://127.0.0.1:8762/actuator/routes

## Discovery server admin UI
http://localhost:8000/

