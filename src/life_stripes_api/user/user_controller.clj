(ns life-stripes-api.user.user-controller
  (:use compojure.core)
  (:require [life-stripes-api.stripe.stripe-service :as stripe-service]
            [life-stripes-api.activity.activity-service :as activity-service]
            [life-stripes-api.user.user-service :as user-service]
            [life-stripes-api.common.common-controller :refer [http_status]]
            [life-stripes-api.common.common-controller :refer [call-if-token-has-access-to-user]]
            [life-stripes-api.common.date-utils :refer [parse-iso8601-as-timestamp]]))

(defn user-map-from [req]
  {:email (get (:body req) "email")
   :password (get (:body req) "password")})

(defn activity-payload-from [user_id req]
  {:note (get (:body req) "note")
   :start_date  (parse-iso8601-as-timestamp (get (:body req) "start_date"))
   :length_in_minutes (get (:body req) "length_in_minutes")
   :user_id (Integer/parseInt user_id)
   :stripe_id (get (:body req) "stripe_id")})

(defn stripe-payload-from [user_id req]
  {:title (get (:body req) "title")
   :relative_priority (get (:body req) "relative_priority")
   :colour_code (get (:body req) "colour_code")
   :user_id (Integer/parseInt user_id)})

(defn get-routes
  ([] (context "/user" []
        (defroutes stripe-routes
          (GET "/:id/stripes" [id :as req] (call-if-token-has-access-to-user id req #(stripe-service/get-by-user-id id) (http_status :ok)))
          (POST "/" req (user-service/create-user (user-map-from req)) {:status (http_status :created)})
          (PUT "/" [action key] (when (.equalsIgnoreCase action "verify")
                                  (user-service/activate-user key)) "")
          (POST "/:id/activities" [id :as req] (call-if-token-has-access-to-user id req #(activity-service/create-activity (activity-payload-from id req)) (http_status :created)))
          (POST "/:id/stripes" [id :as req] (call-if-token-has-access-to-user id req #(stripe-service/create-stripe (stripe-payload-from id req)) (http_status :created)))
          (GET "/:id/activities" [id :as req] (call-if-token-has-access-to-user id req #(activity-service/get-by-user-id id) (http_status :ok)))))))
