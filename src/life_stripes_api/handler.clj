(ns life-stripes-api.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [compojure.route :as route]
            [life-stripes-api.stripe.stripe-controller :as stripes]
            [life-stripes-api.user.user-controller :as users]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defroutes app-routes
  (stripes/get-routes)
  (users/get-routes)
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

(defn -main [& args]
  (run-jetty app {:port (Integer/valueOf (or (System/getenv "port") "3000"))}))