#!/bin/bash

#
curl -X PUT -i -H "Content-Type: application/json" -d '{"name": "joey", "email": "joey@example.com"}' http://localhost:8080/user

