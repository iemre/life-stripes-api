(ns life-stripes-api.user.user-service
  (:require [life-stripes-api.user.user-repo :as repo]
            [life-stripes-api.common.common-repo :as common]
            [life-stripes-api.common.random-utils :refer [generate-random-string]]
            [digest :as digest]))

(defn pwhash [password]
  (digest/sha-512 password))

(def registration-key-char-set "0123456789ABCDEFGHHIJKLMNOPQRSTUVWXYZ")

(defn create-user [{:keys [email password]}]
  (repo/insert! (common/enrich-with-created-at
                 {:email email
                  :password_hash (pwhash password)
                  :registration_key (generate-random-string registration-key-char-set 32)})))

(defn activate-user [registration_key]
  (repo/update-state-by-registration-key! {:state "activated"
                                           :registration_key registration_key}))

(defn get-user-by-email-and-password [email password]
  (repo/get-by-email-and-password-hash {:email email :password_hash (pwhash password)}))

