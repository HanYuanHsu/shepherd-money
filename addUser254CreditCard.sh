#!/bin/bash

 curl -X POST -i -H "Content-Type: application/json" -d '{"userId": 254, "cardIssuanceBank": "Citi", "cardNumber": "0123456789"}' http://localhost:8080/credit-card
