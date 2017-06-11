(ns sandwich-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.format-response :refer [wrap-json-response]]
            [ring.util.response :refer [response]]
            
            [sandwich-api.storage :as storage]))

(defroutes app-routes
  (GET "/sandwich/:name" [name]
    (let [sandwich (storage/get-sandwich name)]
      {:body {"sandwich" sandwich}}))

  (route/not-found "Not Found"))

(def app
  (-> (wrap-defaults app-routes api-defaults)
      (wrap-json-response)))
