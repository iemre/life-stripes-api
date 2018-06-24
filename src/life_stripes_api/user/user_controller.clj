(ns life-stripes-api.user.user-controller
  (:use compojure.core)
  (:require [life-stripes-api.stripe.stripe-service :as stripe-service]
            [life-stripes-api.activity.activity-service :as activity-service]
            [life-stripes-api.user.user-service :as user-service]
            [life-stripes-api.common.common-controller :refer [http_status]]))

(defn user-map-from-request [req]
  {:email (get (:body req) "email")
   :password (get (:body req) "password")})

(defn get-routes
  ([] (context "/user" []
        (defroutes stripe-routes
          (GET "/:id/stripes" [id] (stripe-service/get-by-user-id id))
          (POST "/" req (user-service/create-user (user-map-from-request req)) {:status (http_status :created)})
          (PUT "/" [action key] (when (.equalsIgnoreCase action "verify")
                                  (user-service/activate-user key)) "")
          (GET "/:id/activities" [id] (activity-service/get-by-user-id id))))))
