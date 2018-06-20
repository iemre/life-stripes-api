(ns life-stripes-api.user.user-service
  (:require [life-stripes-api.user.user-repo :as repo]))

;; functions to crud / do computations related to users


(defn get-user-by-email [email]
  (repo/get-by-email {:email email}))