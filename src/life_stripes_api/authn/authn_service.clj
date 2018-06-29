(ns life-stripes-api.authn.authn-service
  (:require [life-stripes-api.authn.authn-repo :as repo]
            [life-stripes-api.common.common-repo :as common-repo]
            [life-stripes-api.user.user-service :as user-service]
            [life-stripes-api.common.random-utils :refer [generate-random-string]]))

(def token-charset "0123456789ABCDEFGHIJKLMNOPQRSTUWXYZ")

(defn generate-new-token [user]
  (repo/insert-and-get (common-repo/enrich-with-created-at {:token (generate-random-string token-charset 36)
                                                            :user_id (:id user)
                                                            :duration_in_minutes 360})))

(defn authorised-for-resource [{:keys [token resource resource_id]}]
  (case resource
    "stripes" (not (empty? (repo/stripes-by-token-and-resource-id {:token token :resource resource :resource_id resource_id})))
    "activities" (not (empty? (repo/activities-by-token-and-resource-id {:token token :resource resource :resource_id resource_id})))))

(defn token-has-access-to-user [{:keys [token user_id]}]
  (not (empty? (repo/tokens-by-token-and-user-id {:token token :user_id user_id}))))

(defn authenticate [{:keys [email password]}]
  (let [user (user-service/get-user-by-email-and-password email password)]
    (if (empty? user)
      {}
      (generate-new-token (first user)))))
