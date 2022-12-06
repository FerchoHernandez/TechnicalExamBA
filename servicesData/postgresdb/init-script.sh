#!/bin/bash

psql -v ON_ERROR_STOP=1 --username "postgres" <<-EOSQL

    CREATE USER administrator PASSWORD 'S3cr#t4dm1n';

    CREATE DATABASE social_network OWNER administrator;
    GRANT ALL PRIVILEGES ON DATABASE social_network TO administrator;
EOSQL
