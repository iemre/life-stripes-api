(ns life-stripes-api.common.date-utils
  (:import (java.text SimpleDateFormat)
           (java.sql Timestamp)))

(defn parse-iso8601-as-timestamp [date-str]
  (if date-str
    (let [parsed-date (.parse (SimpleDateFormat. "yyyy-MM-dd'T'HH:mm:ss'Z'") date-str)]
      (Timestamp. (.getTime parsed-date)))
    nil))

(defn add-hours-to-timestamp [timestamp hours]
  (Timestamp. (+ (.getTime timestamp) (* hours 3600000))))
