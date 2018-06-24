(ns life-stripes-api.stripe.stripe-repo
  (:require [yesql.core :refer [defqueries]]
            [life-stripes-api.config.db :as db]))

;; functions to CRUD stripes

(defqueries "queries/stripe.sql"
  {:connection db/db-spec})

