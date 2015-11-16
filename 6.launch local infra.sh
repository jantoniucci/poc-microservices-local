#!/usr/bin/env bash

#Launch consul service
docker-compose -f docker-compose-files/consul-cluster/docker-compose.yml stop && \
docker-compose -f docker-compose-files/consul-cluster/docker-compose.yml rm && \
docker-compose -f docker-compose-files/consul-cluster/docker-compose.yml up -d

#Launch cassandra service
docker-compose -f docker-compose-files/cassandra-cluster/docker-compose.yml stop && \
docker-compose -f docker-compose-files/cassandra-cluster/docker-compose.yml rm && \
docker-compose -f docker-compose-files/cassandra-cluster/docker-compose.yml up -d

#Launch accounts-api
docker-compose -f docker-compose-files/accounts-api/docker-compose.yml stop && \
docker-compose -f docker-compose-files/accounts-api/docker-compose.yml rm && \
docker-compose -f docker-compose-files/accounts-api/docker-compose.yml up -d

