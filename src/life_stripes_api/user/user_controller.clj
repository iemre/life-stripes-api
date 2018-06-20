(ns life-stripes-api.user.user-controller
  (:use compojure.core)
  (:require [life-stripes-api.stripe.stripe-service :as stripe-service]))

;; functions to handle http requests for users
(defn get-routes
  ([] (context "/user" []
        (defroutes stripe-routes
          (GET "/:id/stripes" [id] (stripe-service/get-by-user-id id))))))
