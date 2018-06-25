(ns life-stripes-api.activity.activity-controller
  (:use compojure.core)
  (:require [life-stripes-api.activity.activity-service :as service]
            [life-stripes-api.common.common-controller :refer [http_status]]
            [compojure.coercions :refer [as-int]]
            [life-stripes-api.common.date-utils :refer [parse-iso8601-as-timestamp]]))

(defn activity-payload-for-update [id req]
  {:note (get (:body req) "note")
   :start_date  (parse-iso8601-as-timestamp (get (:body req) "start_date"))
   :length_in_minutes (get (:body req) "length_in_minutes")
   :id (Integer/parseInt id)})

(defn get-routes
  ([] (context "/activity" [] (defroutes activity-routes
                                (PUT "/:id" [id :as req] (service/update-activity (activity-payload-for-update id req)) {:status (http_status :ok)})
                                (DELETE "/:id" [id :<< as-int] (service/archive-activity id) {:status (http_status :ok)})))))

