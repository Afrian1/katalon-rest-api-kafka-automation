# Katalon REST API and Kafka Automation Testing

## Overview

This project contains automated tests developed using Katalon Studio for REST API testing and Kafka message validation.

## Scope of Testing

### 1. REST API Testing
- **TC_API_GET_Post** — Sends a GET request and validates the response.
- **TC_API_POST_CreatePost** — Sends a POST request and validates the response status and JSON fields.

API endpoint: [JSONPlaceholder](https://jsonplaceholder.typicode.com/)

### 2. Kafka Testing
- **TC_Kafka_Validate_OrderCreated** — Consumes a message from the `qa-events` Kafka topic and validates the JSON event fields:
  - `eventType`
  - `orderId`
  - `status`

## Tools and Technologies

- Katalon Studio
- Groovy
- REST API / HTTP
- Apache Kafka
- JSON

## Prerequisites

- Katalon Studio installed
- Apache Kafka running locally
- Kafka topic `qa-events` created
- Kafka broker accessible at `localhost:9092`
- Internet connection for REST API testing

## How to Run

1. Clone or download this repository.
2. Open the project file `Uji Kemampuan Resful API.prj` in Katalon Studio.
3. For REST API tests, run `TC_API_GET_Post` or `TC_API_POST_CreatePost`.
4. For Kafka testing, start the Kafka broker and ensure the `qa-events` topic contains the expected test message.
5. Run `TC_Kafka_Validate_OrderCreated`.
6. Review the test execution results in Katalon Studio.

## Notes

REST API tests and Kafka tests are executed independently. The project does not currently implement an end-to-end integration that publishes REST API requests as Kafka events.

This project was created as part of a technical assessment.