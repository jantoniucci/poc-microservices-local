#!/usr/bin/env bash

docker-compose -f docker-compose-files/accounts-api/docker-compose.yml scale app=3