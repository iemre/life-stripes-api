(ns life-stripes-api.user.user-repo
  (:require [yesql.core :refer [defqueries]]
            [life-stripes-api.config.db :as db]))

;; functions to CRUD users
(defqueries "queries/user.sql"
  {:connection db/db-spec})
