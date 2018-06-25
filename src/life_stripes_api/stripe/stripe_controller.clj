;; functions to handle http requests for stripes
(ns life-stripes-api.stripe.stripe-controller
  (:use compojure.core)
  (:require [life-stripes-api.stripe.stripe-service :as service]
            [life-stripes-api.common.common-controller :refer [http_status]]))

(defn get-routes
  ([] (context "/stripe" [] (defroutes stripe-routes))))
