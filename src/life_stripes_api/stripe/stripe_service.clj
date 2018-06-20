(ns life-stripes-api.stripe.stripe-service
  (:require [life-stripes-api.stripe.stripe-repo :as repo]))

;; functions to crud / do computations related to stripes

(defn create-stripe [{:keys [title user_id relative_priority]}]
  (repo/create! {:title title :user_id user_id :relative_priority relative_priority :state "created"}))

(defn get-by-user-id [user_id]
  (repo/get-by-user-id {:user_id (Integer/parseInt user_id)}))