# life-stripes-api

## Endpoints

* `/user`

* `/activity`

* `/stripe`

## Project structure

- user
  - user controller
  - user service
  - user repo

- stripe
  - stripe controller
  - stripe service
  - stripe repo

- activity
  - activity controller
  - activity service
  - activity repo

## DB

Start db server with `docker-compose up`

Connect to it with `psql -h 0.0.0.0 -p 5432 -U postgres`

Create db with `create database lifestripes` in psql console

Exit psql console and run migrations with `lein migratus migrate`
