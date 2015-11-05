#!/usr/bin/env bash

. env.sh

#Register new api entry point
curl -XDELETE $kong_admin/apis/poc-ssl-api
curl -XPOST $kong_admin/apis/ \
    --data name=poc-ssl-api \
    --data request_host=poc-ssl-api \
    --data upstream_url=http://$kong_host:8080/api/ \
    --data request_path=/ssl/music/api \
    --data strip_request_path=true


# Check configuration without ssl
curl -XGET $kong_api/ssl/music/api/albums

# Enable ssl plugin
curl -X POST $kong_admin/apis/poc-ssl-api/plugins \
    --form "name=ssl" \
    --form "config.cert=@./certs/server.csr" \
    --form "config.key=@./certs/server.key" \
    --form "config.only_https=true"

# Checks configuration with ssl
curl -XGET $kong_api/ssl/music/api/albums

curl -XGET $kong_secure_api/ssl/music/api/albums --insecure

