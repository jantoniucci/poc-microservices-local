#!/usr/bin/env bash
cd implementation/java/accounts-api/implementation
mvn clean package docker:build
cd ../../../..