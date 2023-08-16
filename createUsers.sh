#!/bin/bash

curl -X PUT -i -H "Content-Type: application/json" -d '{"name": "Anna", "email": "anna@example.com"}' http://localhost:8080/user
curl -X PUT -i -H "Content-Type: application/json" -d '{"name": "Betty", "email": "betty@example.com"}' http://localhost:8080/user
curl -X PUT -i -H "Content-Type: application/json" -d '{"name": "Cathy", "email": "cathy@example.com"}' http://localhost:8080/user
curl -X PUT -i -H "Content-Type: application/json" -d '{"name": "Don", "email": "don@example.com"}' http://localhost:8080/user
curl -X PUT -i -H "Content-Type: application/json" -d '{"name": "Eric", "email": "eric@example.com"}' http://localhost:8080/user
