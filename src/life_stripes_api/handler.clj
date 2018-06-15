(ns life-stripes-api.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [compojure.route :as route]
            [life-stripes-api.stripe.stripe-controller :as stripes]))

(defroutes app-routes
  (stripes/get-routes)
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))
