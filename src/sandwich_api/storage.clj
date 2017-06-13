(ns sandwich-api.storage
  (:require [clojure.java.jdbc :as sql]

            [sandwich-api.sandwich :refer :all]))

(import java.sql.SQLException)

(def ^:private db-host "localhost")
(def ^:private db-port 5432)
(def ^:private db-name "sandwich")

(def ^:private db 
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname (str "//" db-host ":" db-port "/" db-name)
   :user "postgres"
   :password "admin"})

(defn row->sandwich [row]
  (let 
    [renamed 
      (clojure.set/rename-keys row
        {:chicken_count :chicken-count 
         :ham_count :ham-count 
         :lettuce_count :lettuce-count 
         :mozzarella_count :mozzarella-count 
         :bacon_count :bacon-count 
         :avocado_count :avocado-count})]
    (when (valid-sandwich? renamed)
      map->Sandwich renamed)))

(defn sandwich->insertable [sandwich]
  (clojure.set/rename-keys 
    sandwich 
    {:name :name
     :chicken-count :chicken_count 
     :ham-count :ham_count 
     :lettuce-count :lettuce_count 
     :mozzarella-count :mozzarella_count 
     :bacon-count :bacon_count 
     :avocado-count :avocado_count}))

(defn get-sandwich [name]
  (sql/query db
    ["select * from sandwich.sandwiches where name = ?" name]
    {:row-fn row->sandwich}))

(defn insert-sandwich! [sandwich]
  (let [insertable (sandwich->insertable sandwich)]
    (when insertable
      (try 
        (do (sql/insert! db "sandwich.sandwiches" insertable)
            (identity :success))
        (catch SQLException e
          (println "Either a key named" 
                   (:name insertable) 
                   "already existed, or something went wrong.")
          (identity :failure))))))         
