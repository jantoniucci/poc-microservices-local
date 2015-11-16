#!/usr/bin/env bash


curl -Lo containerbuddy-0.0.1-alpha.tar.gz \
https://github.com/joyent/containerbuddy/releases/download/0.0.1-alpha/containerbuddy-0.0.1-alpha.tar.gz
tar -xf containerbuddy-0.0.1-alpha.tar.gz
cp ./build/containerbuddy ./opt/containerbuddy/
