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

(defn authenticate [{:keys [email password]}]
  (let [user (user-service/get-user-by-email-and-password email password)]
    (if (empty? user)
      {}
      (generate-new-token (first user)))))
