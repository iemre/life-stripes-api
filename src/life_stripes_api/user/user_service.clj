(ns life-stripes-api.user.user-service
  (:require [life-stripes-api.user.user-repo :as repo]
            [life-stripes-api.common.common-repo :as common])
  (require digest))

(defn pwhash [password]
  (digest/sha-512 password))

(def registration-key-char-set "0123456789ABCDEFGHHIJKLMNOPQRSTUVWXYZ")

(defn random-registration-key []
  (apply str (take 36 (repeatedly
                       #(.charAt registration-key-char-set
                                 (rand-int (- (.length registration-key-char-set) 1)))))))

(defn create-user [{:keys [email password]}]
  (repo/insert! (common/enrich-with-created-at
                 {:email email :password_hash (pwhash password)
                  :registration_key (random-registration-key)})))

(defn activate-user [registration_key]
  (repo/update-state-by-registration-key! {:state "activated"
                                           :registration_key registration_key}))

(defn get-user-by-email [email]
  (repo/get-by-email {:email email}))

