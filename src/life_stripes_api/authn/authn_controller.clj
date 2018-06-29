(ns life-stripes-api.authn.authn-controller
  (:use compojure.core)
  (:require [life-stripes-api.authn.authn-service :as service]
            [life-stripes-api.common.common-controller :refer [http_status]]))

(defn authn-payload-from [req]
  {:email (get (:body req) "email"),
   :password (get (:body req) "password")})

(defn auth-response [req]
  (let [auth-result (service/authenticate (authn-payload-from req))]
    (if (empty? auth-result)
      {:status (http_status :unauthorized) :body {}}
      {:status (http_status :created) :body auth-result :cookies {"lifestripes-token" (:token (first auth-result))}}))
    )


(defn get-routes
  ([] (context "/authn" [] (defroutes activity-routes
                             (POST "/" req (auth-response req))))))
