# Key Technologies used 

- Axon Framework And Axon Server 
- MongoDB Installed Locally
- Java 11
- Swagger

## Running Locally

- First we need to run the Axon Server locally using command below

 
    `docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver`

Check and verify Axon Server is running on URL: http://localhost:8024/

- Navigate to 'customer.cmd' folder and "customer.query" folder and execute below command 

    ` gradle bootRun  --args='--spring.profiles.active=local'`


Check customer.cmd application is running on port 9091 and customer.query application is running on port 9092, 
also you should on the screen a message which says   ` Successfully connected to localhost:8124`


## Running with Docker Compose

* Build the "customer.query" project and "customer.cmd" by executing below gradle command

        ` gradle build `

* To run the project with  a Docker Compose, go to the main folder and execute below command

    `docker-compose build `
    `docker-compose up`

## Testing The endpoints with CURL

- To add a customer run below CURL command 


`curl --location --request POST 'localhost:9091/api/v1/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Customer1",
"preference" : "EMAIL"
}' `


- To update a customer profile preference run below CURL command 

`curl --location --request PUT 'localhost:9091/api/v1/customers/480f230c-8862-4e3d-a8ee-abfb6f3b05f9' \
--header 'Content-Type: application/json' \
--data-raw '{
"preference" : "EMAIL"
}'`



- To Delete a customer run below CURL command


    `curl --location --request DELETE 'http://localhost:9091/api/v1/customers/39781b63-7f5a-4710-971f-168ac257bf2e' --data-raw ''`


- To fetch all customers run below CURL command 

   
     `curl --location --request GET 'localhost:9092/api/v1/customers/'`


## Additional Feature 

- Spring Cloud Gateway to be added - work in progress 
- Authorization Server to be added - work in progress 


## References 

- More info about Axon: https://docs.axoniq.io/reference-guide/
- http://progressivecoder.com/implementing-event-sourcing-with-axon-and-spring-boot-part-3/



docker run --name mongo-db --host mongo-db -d mongo:latest
