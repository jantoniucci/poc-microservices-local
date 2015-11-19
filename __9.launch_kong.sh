#!/usr/bin/env bash

echo Parando kong...
docker stop kong
docker rm kong

echo Parando cassandra...
docker stop cassandra
docker rm cassandra

echo Lanzando cassandra...
docker run -p 9042:9042 -d --name cassandra mashape/cassandra
sleep 5

echo Lanzando kong...
docker run -p 8000:8000 -p 8001:8001 -d --name kong --link cassandra:cassandra mashape/kong

echo Probando conectividad....
sleep 10

curl -XGET $1:8001/apis

curl -XPOST 192.168.99.100:8001/apis/ \
--data name=poc-gce-api \
--data request_host=poc-gce-api \
--data upstream_url=http://192.168.99.100:8080/api/ \
--data request_path=/music/api \
--data strip_request_path=true

curl -XPOST 192.168.99.100:8001/apis/ \
--data name=poc-gce-manage \
--data request_host=poc-gce-manage \
--data upstream_url=http://192.168.99.100:8080/manage/ \
--data request_path=/music/manage \
--data strip_request_path=true
