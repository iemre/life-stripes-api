;; functions to handle http requests for stripes
(ns life-stripes-api.stripe.stripe-controller
  (:use compojure.core)
  (:require [life-stripes-api.stripe.stripe-service :as service]
            [life-stripes-api.common.common-controller :refer [http_status]]))

(defn stripe-payload-from-req [req]
  {:title (get (:body req) "title")
   :relative_priority (get (:body req) "relative_priority")
   :colour_code (get (:body req) "colour_code")
   :user_id (get (:body req) "user_id")})

(defn get-routes
  ([] (context "/stripe" [] (defroutes stripe-routes
                              (POST "/" req (service/create-stripe (stripe-payload-from-req req)) {:status (http_status :created)})))))
