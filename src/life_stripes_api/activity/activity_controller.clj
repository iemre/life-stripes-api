(ns life-stripes-api.activity.activity-controller
  (:use compojure.core)
  (:require [life-stripes-api.activity.activity-service :as service]
            [life-stripes-api.common.common-controller :refer [http_status]]
            [life-stripes-api.common.date-utils :refer [parse-iso8601-as-timestamp]]))

(defn activity-payload-from-req [req]
  {:note (get (:body req) "note")
   :start_date  (parse-iso8601-as-timestamp (get (:body req) "start_date"))
   :length_in_minutes (get (:body req) "length_in_minutes")
   :user_id (get (:body req) "user_id")
   :stripe_id (get (:body req) "stripe_id")})

(defn get-routes
  ([] (context "/activity" [] (defroutes activity-routes
                                (POST "/" req (service/create-activity (activity-payload-from-req req)) {:status (http_status :created)})))))

