# Event Sourcing with CQRS using Axon and Spring Boot
Example project to demonstrate the capabilities of these technologies

## Usage
`mvn clean package spring-boot:run`

Swagger UI (send commands/queries): http://localhost:8080/swagger-ui.html

# Under the hood

To see things running behind the scenes...

H2 Console (use jdbc:h2:mem:testdb as jdbc url): http://localhost:8080/h2-console

Axon server dashboard (if running, see section below): http://localhost:8024/

## Running Axon server locally
`docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver`

URL: http://localhost:8024/

To run command and query apps in separate instances:

`mvn spring-boot:run -Dspring-boot.run.profiles=command -Dserver.port=8055`

`mvn spring-boot:run -Dspring-boot.run.profiles=query -Dserver.port=8056`

This can then be seen on http://localhost:8024/#overview

## Links
More info about Axon: https://docs.axoniq.io/reference-guide/

## Credits
Great blog on the subject: http://progressivecoder.com/implementing-event-sourcing-with-axon-and-spring-boot-part-3/


