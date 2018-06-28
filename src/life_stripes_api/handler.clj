(ns life-stripes-api.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [ring.middleware.json :as json-middleware]
            [ring.middleware.cookies :as cookies-middleware]
            [compojure.route :as route]
            [life-stripes-api.stripe.stripe-controller :as stripes]
            [life-stripes-api.user.user-controller :as users]
            [life-stripes-api.activity.activity-controller :as activities]
            [life-stripes-api.authn.authn-controller :as authn]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defroutes app-routes
  (stripes/get-routes)
  (users/get-routes)
  (activities/get-routes)
  (authn/get-routes)
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (cookies-middleware/wrap-cookies)
      (json-middleware/wrap-json-body)
      (json-middleware/wrap-json-response)))

(defn -main [& args]
  (run-jetty app {:port (Integer/valueOf (or (System/getenv "port") "3000"))}))
