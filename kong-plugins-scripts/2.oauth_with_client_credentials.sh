#!/usr/bin/env bash

. env.sh

# Create a new consumer
curl -X DELETE $kong_admin/consumers/oauth-test-consumer/
curl -X POST $kong_admin/consumers/ \
    --data "username=oauth-test-consumer"

curl -X POST $kong_admin/consumers/oauth-test-consumer/oauth2 \
    --data "name=Oauth2.0_test_app" \
    --data "client_id=oauth2_clientID" \
    --data "client_secret=oauth2_clientSecret" \
    --data "redirect_uri=http://www.google.es"

# Configure for client credentials authorization flow
curl -X DELETE $kong_admin/apis/poc-oauth-api
curl -X POST $kong_admin/apis/ \
    --data name=poc-oauth-api \
    --data request_host=poc-oauth-api \
    --data upstream_url=http://$kong_host:8080/api/ \
    --data request_path=/authorized/music/api \
    --data strip_request_path=true

curl -X GET $kong_api/authorized/music/api/albums

curl -X POST $kong_admin/apis/poc-oauth-api/plugins \
    --data "name=oauth2" \
    --data "config.scopes=email,phone,address" \
    --data "config.mandatory_scope=false" \
    --data "config.enable_authorization_code=false" \
    --data "config.enable_client_credentials=true" \
    --data "config.enable_password_grant=false" \
    --data "config.enable_implicit_grant=false" \

# Enable ssl plugin
curl -X POST $kong_admin/apis/poc-oauth-api/plugins \
    --form "name=ssl" \
    --form "config.cert=@./certs/server.csr" \
    --form "config.key=@./certs/server.key" \
    --form "config.only_https=false"


# Invalid credentials
curl -X GET $kong_api/authorized/music/api/albums

# Issue the token
curl -X POST $kong_secure_api/authorized/music/api/oauth2/token --insecure \
    --data "client_id=oauth2_clientID" \
    --data "client_secret=oauth2_clientSecret" \
    --data "grant_type=client_credentials" \


echo "API Access token (copy 'access_token' value from last response) :"
read access_token

curl -X GET $kong_api/authorized/music/api/albums \
    --data "client_id=oauth2_clientID" \
    --data "client_secret=oauth2_clientSecret" \
    --data "grant_type=client_credentials" \

