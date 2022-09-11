# Fraud Services

Implement fraud services using the microservice architecture and the following technologies:
- Spring Boot 2.0
- Spring Cloud
- Docker
- PostgreSQL
- Apache Kafka

## How to run this service with Docker

### Prerequisites
- Clone or download this repository with your preferred method

- Make sure you have Docker or Docker Desktop installed on your machine

- Change to the directory containing the *docker-compose.yml*
  - Enter the following Docker command: ```docker compose up -d``` to create the required containers
  - Wait for containers to be built and finish running

### Docker containers are up and running
- Visit [pgadmin](http://localhost:5050) to access the GUI for PostgreSQL
  - Enter ```password``` and click OK if a prompt appears asking for the master password
  - Right-click on Servers > click Register > Server to create a new server
  - Select the General tab and use any name for the Name field
  - Head to the Connection tab, enter the following and click Save afterwards:
    - ```postgres``` for the Host name/address field
    - ```username``` for the Username field
    - ```password``` for the Password field

- Use an API client, such as [Postman](https://www.postman.com/downloads/), to test the APIs for this service

- Head to [Zipkin](http://localhost:9411) for distributed tracing

- Use a tool, such as [Offset Explorer 2.0](https://www.kafkatool.com/download.html), to quickly view the messages stored in the topics of the Apache Kafka cluster
  - Right-click to add a new connection on the Cluster section
  - On the Properties tab, specify the name of the cluster
  - Head to the Advanced tab to specify *localhost:29092* for Bootstrap servers
  - Click the test button and add the connection
  - Double-click on the cluster you created to view information about the Brokers, Topics, and Consumers
  - Expand Topics > click notification > Data tab > Retrieve Messages button to view the messages stored in Hex within the Value tab
  - Copy the Hex values for a message and search online for a tool to convert from hexadecimal to text to see the JSON response

### REST APIs
```
GET  /api/v1/fraud-check-history/{customerID} 
POST /api/v1/customers 
POST /api/v1/notifications
```