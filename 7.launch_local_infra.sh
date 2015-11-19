#!/usr/bin/env bash

#Stops all
docker-compose -f docker-compose-files/kong-api-manager/docker-compose.yml stop && \
docker-compose -f docker-compose-files/cassandra-cluster/docker-compose.yml stop && \
docker-compose -f docker-compose-files/accounts-api/docker-compose.yml stop && \
docker-compose -f docker-compose-files/consul-cluster/docker-compose.yml stop

#Launch consul service
echo "y" | docker-compose -f docker-compose-files/consul-cluster/docker-compose.yml rm && \
docker-compose -f docker-compose-files/consul-cluster/docker-compose.yml up -d

#Launch cassandra service
echo "y" | docker-compose -f docker-compose-files/cassandra-cluster/docker-compose.yml rm && \
docker-compose -f docker-compose-files/cassandra-cluster/docker-compose.yml up -d

#Launch accounts-api
echo "y" | docker-compose -f docker-compose-files/accounts-api/docker-compose.yml rm && \
docker-compose -f docker-compose-files/accounts-api/docker-compose.yml up -d


#Launch kong api-manager
echo "y" | docker-compose -f docker-compose-files/kong-api-manager/docker-compose.yml rm && \
docker-compose -f docker-compose-files/kong-api-manager/docker-compose.yml up -d

