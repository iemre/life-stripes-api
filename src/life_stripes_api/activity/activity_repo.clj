(ns life-stripes-api.activity.activity-repo
  (:require [life-stripes-api.config.db :as db]
            [yesql.core :refer [defqueries]]))

;; functions to CRUD activities
(defqueries "queries/activity.sql"
  {:connection db/db-spec})

