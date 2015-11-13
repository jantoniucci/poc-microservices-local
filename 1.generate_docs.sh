#!/usr/bin/env bash

raml2html definitions/accounts-api/accounts-api.raml > documentation/accounts-api.html
open documentation/accounts-api.html
