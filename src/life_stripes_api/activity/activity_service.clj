(ns life-stripes-api.activity.activity-service
  (:require [life-stripes-api.activity.activity-repo :as repo]
            [life-stripes-api.common.common-repo :as common-repo]))

(defn get-by-user-id [user_id]
  (repo/get-by-user-id {:user_id (Integer/parseInt user_id)}))

(defn get-by-stripe-id [stripe_id]
  (repo/get-by-stripe-id {:stripe_id (Integer/parseInt stripe_id)}))

(defn create-activity [{:keys [note user_id stripe_id start_date length_in_minutes]}]
  (repo/insert! (common-repo/enrich-with-created-at {:note note
                                                     :user_id user_id
                                                     :stripe_id stripe_id
                                                     :start_date start_date
                                                     :length_in_minutes length_in_minutes})))

(defn update-activity [{:keys [id note start_date length_in_minutes]}]
  (repo/update! {:id id :note note :start_date start_date :length_in_minutes length_in_minutes}))
