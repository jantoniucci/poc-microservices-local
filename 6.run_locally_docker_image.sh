#!/usr/bin/env bash
docker run -p 8080:8080 -d -t poc.raml/accounts-api.impl java -Djava.security.egd=file:/dev/./urandom -jar /app.jar