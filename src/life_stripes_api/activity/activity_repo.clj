(ns life-stripes-api.activity.activity-repo
  (:require [life-stripes-api.config.db :as db]
            [yesql.core :refer [defquery]]))

;; functions to CRUD activities
(defquery get-by-user-id "queries/activity/get-by-user-id.sql"
          {:connection db/db-spec})

(defquery create! "queries/activity/insert.sql"
          {:connection db/db-spec})
