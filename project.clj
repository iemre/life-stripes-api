(defproject life-stripes-api "0.1.0-SNAPSHOT"
  :description "Life stripes backend"
  :license {:name "Commercial Licence"
            :url "#"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-devel "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [org.clojure/java.jdbc, "0.6.1"]
                 [migratus, "1.0.6"]
                 [ring/ring-json "0.4.0"]
                 [yesql "0.5.3"]
                 [digest "1.4.8"]
                 [metosin/ring-http-response "0.9.0"]
                 [org.clojure/data.json "0.2.6"]
                 ]

  :plugins [[lein-ring "0.9.7"]
            [migratus-lein "0.5.7"]
            [lein-cljfmt "0.5.7"]]
  :ring {:handler life-stripes-api.handler/app}

  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}
   :uberjar {:aot :all :main life-stripes-api.handler}
   }


  :migratus {:store :database
   :migration-dir "migrations"
   :init-script   "init.sql"
   :db {:classname "org.postgresql.Driver"
        :subprotocol "postgresql"
        :subname "//localhost:5432/lifestripes"
        :user "postgres"
        :password ""}}
  )

