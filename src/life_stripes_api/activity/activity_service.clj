(ns life-stripes-api.activity.activity-service
  (:require [life-stripes-api.activity.activity-repo :as repo]))

;; functions to crud / do computations related to activities

(defn get-by-user-id [user_id]
  (repo/get-by-user-id {:user_id (Integer/parseInt user_id)}))

(defn create-activity [{:keys [note user_id stripe_id start_date end_date]}]
  (repo/create! {:note note :user_id user_id :stripe_id stripe_id :start_date start_date :end_date end_date}))

