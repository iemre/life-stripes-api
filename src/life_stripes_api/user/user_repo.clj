(ns life-stripes-api.user.user-repo
  (:require [yesql.core :refer [defquery]]
            [life-stripes-api.config.db :as db]))

;; functions to CRUD users

(defquery create! "queries/user/insert.sql"
  {:connection db/db-spec})

(defquery get-by-email "queries/user/get-by-email.sql"
  {:connection db/db-spec})