(ns life-stripes-api.stripe.stripe-controller
  (:use compojure.core)
  (:require [life-stripes-api.stripe.stripe-service :as service]
            [compojure.coercions :refer [as-int]]
            [life-stripes-api.common.common-controller :refer [http_status]]))

(defn stripe-payload-for-update [id req]
  {:title (get (:body req) "title")
   :relative_priority (get (:body req) "relative_priority")
   :colour_code (get (:body req) "colour_code")
   :id (Integer/parseInt id)})

(defn get-routes
  ([] (context "/stripe" [] (defroutes stripe-routes
                              (PUT "/:id" [id :as req] (service/update-stripe (stripe-payload-for-update id req)) {:status (http_status :ok)})
                              (DELETE "/:id" [id :<< as-int] (service/archive-stripe id)  {:status (http_status :ok)})))))
