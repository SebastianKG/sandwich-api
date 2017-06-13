(ns sandwich-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer
              [wrap-defaults api-defaults]]
            [ring.middleware.format-response :refer [wrap-json-response]]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.util.response :refer [response]]
            
            [sandwich-api.storage :as storage]
            [sandwich-api.sandwich :refer :all]))

(defn- to-response [status]
  (case status
        :success {"result" "success"}
        :failure {"result" "failure"}))

(defroutes app-routes
  (GET "/sandwich/:name" [name]
    (let [sandwich-results (storage/get-sandwich name)]
      (when (seq sandwich-results)
        {:body {"sandwich" (first sandwich-results)}})))

  (POST "/sandwich" request
    (let [sandwich-map (clojure.walk/keywordize-keys (:body request))]
      (when (valid-sandwich? sandwich-map)
        (-> (map->Sandwich sandwich-map) 
            (storage/insert-sandwich!)
            ((fn [result] {:body (to-response result)}))))))

  (route/not-found "Not Found"))

(def app
  (-> (wrap-defaults app-routes api-defaults)
      (wrap-json-body)
      (wrap-json-response)))
