(ns life-stripes-api.stripe.stripe-repo
  (:require [yesql.core :refer [defquery]]
            [life-stripes-api.config.db :as db]))

;; functions to CRUD stripes

(defquery get-by-id "queries/stripe/get-by-id.sql"
  {:connection db/db-spec})

(defquery get-by-user-id "queries/stripe/get-by-user-id.sql"
  {:connection db/db-spec})

(defquery create! "queries/stripe/insert.sql"
  {:connection db/db-spec})

