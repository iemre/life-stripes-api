(ns life-stripes-api.config.db)

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :user "postgres"
              :subname "//localhost:5432/lifestripes"})