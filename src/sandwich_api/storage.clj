(ns sandwich-api.storage
  (:require [clojure.java.jdbc :as sql] 
  
            [sandwich-api.sandwich :refer :all]))

(def ^:private db-host "localhost")
(def ^:private db-port 5432)
(def ^:private db-name "sandwich")

(def ^:private db 
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname (str "//" db-host ":" db-port "/" db-name)
   :user "postgres"
   :password "admin"})

(defn get-sandwich [name]
  (sql/query db
    ["select * from sandwich.sandwiches where name = ?" name]))
