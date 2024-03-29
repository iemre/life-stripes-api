(ns life-stripes-api.stripe.stripe-service
  (:require [life-stripes-api.stripe.stripe-repo :as repo]
            [life-stripes-api.common.common-repo :as common-repo]))

(defn create-stripe [{:keys [title user_id relative_priority colour_code]}]
  (repo/insert! (common-repo/enrich-with-created-at {:title title :user_id user_id :relative_priority relative_priority :colour_code colour_code})))

(defn get-by-user-id [user_id]
  (repo/get-by-user-id {:user_id (Integer/parseInt user_id)}))

(defn update-stripe [{:keys [title relative_priority colour_code id]}]
  (repo/update! {:title title :relative_priority relative_priority :colour_code colour_code :id id}))

(defn archive-stripe [id]
  (repo/archive! {:id id}))
