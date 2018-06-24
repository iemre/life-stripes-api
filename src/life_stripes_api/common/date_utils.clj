(ns life-stripes-api.common.date-utils
  (:import (java.text SimpleDateFormat)
           (java.sql Timestamp)))

(defn parse-iso8601-as-timestamp [date-str]
  (if date-str
    (Timestamp. (.getTime (.parse (SimpleDateFormat. "yyyy-MM-dd'T'hh:MM:ss") date-str)))
    nil))
