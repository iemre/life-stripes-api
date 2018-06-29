(ns life-stripes-api.authn.authn-repo
  (:require [life-stripes-api.config.db :as db]
            [yesql.core :refer [defqueries]]))

(defqueries "queries/authn.sql"
  {:connection db/db-spec})

