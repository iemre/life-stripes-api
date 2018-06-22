(ns life-stripes-api.user.user-controller
  (:use compojure.core)
  (:require [life-stripes-api.stripe.stripe-service :as stripe-service]
            [life-stripes-api.activity.activity-service :as activity-service]
            [life-stripes-api.user.user-service :as user-service])
 )


;; functions to handle http requests for users
(defn get-routes
  ([] (context "/user" []
        (defroutes stripe-routes
          (GET "/:id/stripes" [id] (stripe-service/get-by-user-id id))
                   ; how the hell do you get the fucking json from the request
          (POST "/" [request] (prn request) "Hi")
          (GET "/:id/activities" [id] (activity-service/get-by-user-id id))))))
