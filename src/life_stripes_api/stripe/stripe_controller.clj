;; functions to handle http requests for stripes
(ns life-stripes-api.stripe.stripe-controller
  (:use compojure.core))

(defn get-routes
  ([] (context "/stripe" [] (defroutes stripe-routes
                              (GET "/" [] "all-stripes")
                              (POST "/" {body :body} str "body is " body)))))