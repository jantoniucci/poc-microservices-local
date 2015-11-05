#!/usr/bin/env bash

# Create a new consumer
curl -X POST http://192.168.99.100:8001/consumers/ \
    --data "username=consumerID"


# Create a API key for the consumer
curl -X POST http://192.168.99.100:8001/consumers/consumerID/key-auth \
    --data "key=customized-key"

# Add key-auth plugin to API
curl -i -X POST \
  --url http://192.168.99.100:8001/apis/poc-gce-manage/plugins/ \
  --data 'name=key-auth' \
  --data 'config.key_names=X-custom-apikey,custom-apikey'

# Test the plugin...

# by query string parameter...
curl -i -XGET http://192.168.99.100:8000/music/manage/trace?apikey=customized-key

# or by header
curl -i -XGET http://192.168.99.100:8000/music/manage/trace \
 -H 'apikey:customized-key'



