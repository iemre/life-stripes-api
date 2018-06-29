(ns life-stripes-api.activity.activity-controller
  (:use compojure.core)
  (:require [life-stripes-api.activity.activity-service :as service]
            [life-stripes-api.common.common-controller :refer [http_status]]
            [life-stripes-api.common.common-controller :refer [call-if-token-has-access-to-resource]]
            [compojure.coercions :refer [as-int]]
            [life-stripes-api.common.date-utils :refer [parse-iso8601-as-timestamp]]))

(defn activity-payload-for-update [id req]
  {:note (get (:body req) "note")
   :start_date  (parse-iso8601-as-timestamp (get (:body req) "start_date"))
   :length_in_minutes (get (:body req) "length_in_minutes")
   :id id})

(defn get-routes
  ([] (context "/activity" [] (defroutes activity-routes
                                (PUT "/:id" [id :<< as-int :as req] (call-if-token-has-access-to-resource req #(service/update-activity (activity-payload-for-update id req)) "activities" id (http_status :ok)))
                                (DELETE "/:id" [id :<< as-int :as req] (call-if-token-has-access-to-resource req #(service/archive-activity id) "activities" id (http_status :ok)))))))

