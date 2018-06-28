(ns life-stripes-api.common.random-utils)

(defn generate-random-string [charset length]
  (apply str (take length (repeatedly
                           #(.charAt charset
                                     (rand-int (- (.length charset) 1)))))))

