;; functions to handle http requests for stripes
(ns life-stripes-api.stripe.stripe-controller
  (:use compojure.core)
  (:require [life-stripes-api.stripe.stripe-service :as service]))

(defn get-routes
  ([] (context "/stripe" [] (defroutes stripe-routes
                              (POST "/" {body :body} str "body is " body)))))