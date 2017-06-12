(defproject sandwich-api "0.1.0-SNAPSHOT"
  :description "An API for sandwiches."
  :url "https://github.com/SebastianKG/sandwich-api"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [org.clojure/java.jdbc "0.7.0-alpha3"]
                 [org.postgresql/postgresql "9.4-1206-jdbc42"]
                 [ring-middleware-format "0.7.2"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.2.1"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler sandwich-api.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
