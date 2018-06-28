(ns life-stripes-api.common.common-controller
  (:require [life-stripes-api.authn.authn-service :as authn-service]))

(def http_status
  {:created 201
   :ok 200
   :unauthorised 401})

(defn- authenticate-and-respond-for-user [user_id token function status]
  (if (authn-service/token-has-access-to-user {:token token :user_id (Integer/parseInt user_id)})
    (let [result (function)]
      (if (or (map? result) (vector? result) (seq? result))
        {:body result
         :status status}
        {:body {}
         :status status}))
    {:body {}
     :status (http_status :unauthorised)}))

(defn- authenticate-and-respond-for-resource [token function resource resource_id status]
  (if (authn-service/authorised-for-resource {:token token :resource resource :resource_id resource_id})
    (let [result (function)]
      (if (or (map? result) (vector? result) (seq? result))
        {:body result
         :status status}
        {:body {}
         :status status}))
    {:body {}
     :status (http_status :unauthorised)}))

(defn- get-token-from [req]
  (do
    (let [cookie-token (get (:cookies req) "lifestripes-token")]
      (if (not (empty? cookie-token))
        cookie-token))
    (let [bearer-token (get (:headers req) "authorization")]
      (if (not (empty? bearer-token))
        (second (.split bearer-token " "))
        nil))))

(defn call-if-token-has-access-to-resource [req function resource resource_id status]
  (let [token (get-token-from req)]
    (if (not (empty? token))
      (authenticate-and-respond-for-resource token function resource resource_id status)
      {:body {} :status (http_status :unauthorised)})))

(defn call-if-token-has-access-to-user [user_id req function status]
  (let [token (get-token-from req)]
    (if (not (empty? token))
      (authenticate-and-respond-for-user user_id token function status)
      {:body {} :status (http_status :unauthorised)})))

