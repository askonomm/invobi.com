(ns invobi.db
  (:require
   [ragtime.jdbc :as ragtime.jdbc]
   [ragtime.repl :as ragtime.repl]))

(def datasource
  (if (System/getenv "DEV")
    {:dbtype "sqlite"
     :dbname "db/invo.db"}
    {:dbtype "sqlite"
     :dbname "/dbs/invo.db"}))

(defn migrator-config []
  {:datastore (ragtime.jdbc/sql-database (-> datasource
                                             (assoc :subname (:dbname datasource))
                                             (assoc :classname "org.sqlite.JDBC")
                                             (assoc :subprotocol "sqlite")
                                             (dissoc :dbname)
                                             (dissoc :dbtype)))
   :migrations (ragtime.jdbc/load-resources "migrations")})

(defn migrate []
  (ragtime.repl/migrate (migrator-config)))

(defn rollback []
  (ragtime.repl/rollback (migrator-config)))
