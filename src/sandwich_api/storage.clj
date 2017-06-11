(ns sandwich-api.storage
  (:require [sandwich-api.sandwich :refer :all]))


(defn get-sandwich [name]
  (->Sandwich name 1 1 1 1 1 1)) ; stub
