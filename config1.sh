#!/bin/bash

# initialize
curl -X DELETE http://localhost:8080/initialize

# create user A
curl -X PUT -H "Content-Type: application/json" -d {\"name\": \"A\", \"email\": \"A@example.com\"} http://localhost:8080/user

# create user B
curl -X PUT -H "Content-Type: application/json" -d {\"name\": \"B\", \"email\": \"B@example.com\"} http://localhost:8080/user

#
