(ns life-stripes-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
           (GET "/" [] "HELLO WORLD")
           (route/not-found "not found"))

(def app
  (wrap-defaults app-routes site-defaults))
