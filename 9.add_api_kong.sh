#!/usr/bin/env bash


curl -XDELETE 192.168.99.100:8001/apis/accounts-api

curl -XPOST 192.168.99.100:8001/apis \
--data name=accounts-api \
--data request_host=http://accounts.demo/api \
--data upstream_url=http://192.168.99.100/api \
--data request_path=/accounts-demo \
--data strip_request_path=true

