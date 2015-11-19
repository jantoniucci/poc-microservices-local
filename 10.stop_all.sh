#!/usr/bin/env bash

docker-compose -f docker-compose-files/kong-api-manager/docker-compose.yml stop && \
docker-compose -f docker-compose-files/cassandra-cluster/docker-compose.yml stop && \
docker-compose -f docker-compose-files/accounts-api/docker-compose.yml stop && \
docker-compose -f docker-compose-files/consul-cluster/docker-compose.yml stop && \
echo "y" | docker-compose -f docker-compose-files/kong-api-manager/docker-compose.yml rm && \
echo "y" | docker-compose -f docker-compose-files/cassandra-cluster/docker-compose.yml rm && \
echo "y" | docker-compose -f docker-compose-files/accounts-api/docker-compose.yml rm && \
echo "y" | docker-compose -f docker-compose-files/consul-cluster/docker-compose.yml rm
