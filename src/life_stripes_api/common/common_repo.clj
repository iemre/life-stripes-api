(ns life-stripes-api.common.common-repo
  (:import (java.sql Timestamp)))

(defn enrich-with-created-at [data-map]
  (merge data-map {:created_at (Timestamp. (System/currentTimeMillis))}))
