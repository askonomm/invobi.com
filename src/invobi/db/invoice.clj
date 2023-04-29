(ns invobi.db.invoice
  (:require
    [clojure.data.json :as json]
    [invobi.db :refer [datasource]]
    [invobi.utils.encryption :refer [decrypt encrypt]]
    [hugsql.adapter.next-jdbc :as next-adapter]
    [hugsql.core :as hugsql]))

(hugsql/def-db-fns
  "sql/invoice.sql"
  {:quoting :mysql
   :adapter (next-adapter/hugsql-adapter-next-jdbc)})

(declare update-nr-sql)
(declare update-from-name-sql)
(declare get-from-fields-sql)
(declare update-from-fields-sql)
(declare update-to-name-sql)
(declare get-to-fields-sql)
(declare update-to-fields-sql)
(declare update-date-issued-sql)
(declare update-due-date-sql)
(declare get-currency-sql)
(declare update-currency-sql)
(declare get-items-sql)
(declare update-items-sql)
(declare update-qty-type-sql)

(defn update-nr 
  "Update invoice number"
  [id value]
  (update-nr-sql
    datasource
    {:id id
     :value (encrypt value id)}))

(defn update-from-name 
  "Update from name"
  [id value]
  (update-from-name-sql
    datasource
    {:id id
     :value (encrypt value id)}))

(defn get-from-fields 
  "Get from fields"
  [id]
  (let [{:keys [from_fields]} (get-from-fields-sql
                                datasource
                                {:id id})]
    (-> (decrypt from_fields id)
        (json/read-str :key-fn keyword))))

(defn update-from-fields
  "Update from fields"
  [id value]
  (update-from-fields-sql
    datasource
    {:id id
     :value (-> (json/write-str value)
                (encrypt id))}))

(defn update-to-name [id value]
  (update-to-name-sql
    datasource
    {:id id
     :value (encrypt value id)}))

(defn get-to-fields [id]
  (let [{:keys [to_fields]} (get-to-fields-sql
                              datasource
                              {:id id})]
    (-> (decrypt to_fields id)
        (json/read-str :key-fn keyword))))

(defn update-to-fields [id value]
  (update-to-fields-sql
    datasource
    {:id id
     :value (-> (json/write-str value)
                (encrypt id))}))

(defn update-date-issued [id value]
  (update-date-issued-sql
    datasource
    {:id id
     :value (encrypt value id)}))

(defn update-due-date [id value]
  (update-due-date-sql
    datasource
    {:id id
     :value (encrypt value id)}))

(defn get-currency [id]
  (let [{:keys [currency]} (get-currency-sql
                             datasource
                             {:id id})]
    (decrypt currency id)))

(defn update-currency [id value]
  (update-currency-sql
    datasource
    {:id id
     :value (encrypt value id)}))

(defn get-items [id]
  (let [{:keys [items]} (get-items-sql
                          datasource
                          {:id id})]
    (-> (decrypt items id)
        (json/read-str :key-fn keyword))))

(defn update-items [id value]
  (update-items-sql
    datasource
    {:id id
     :value (-> (json/write-str value)
                (encrypt id))}))

(defn update-qty-type [id value]
  (update-qty-type-sql
    datasource
    {:id id
     :value (encrypt value id)}))
