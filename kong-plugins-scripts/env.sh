#!/usr/bin/env bash

export kong_host=`docker-machine ip default`
export kong_admin=http://$kong_host:8001
export kong_api=http://$kong_host:8000
export kong_secure_api=https://$kong_host:8443
