(ns invobi.db.invoices
  (:require
   [invobi.db :refer [datasource]]
   [invobi.utils.encryption :refer [decrypt encrypt]]
   [clojure.data.json :as json]
   [clojure.string :as string]
   [hugsql.adapter.next-jdbc :as next-adapter]
   [hugsql.core :as hugsql]))

(hugsql/def-db-fns 
  "sql/invoices.sql" 
  {:quoting :mysql
   :adapter (next-adapter/hugsql-adapter-next-jdbc)})

(declare get-invoice-sql)
(declare update-invoice-sql)
(declare create-invoice-sql)
(declare delete-invoice-sql)

(defn- parse-float 
  ([input]
   (parse-float input 0))
  ([input fallback]
   (if (string/blank? (str input))
     fallback
     (Float/parseFloat (str input)))))

(defn get-invoice [id]
  (when-let [invoice (get-invoice-sql datasource {:id id})]
    {:id id
     :nr (decrypt (-> invoice :nr) id)
     :from-name (decrypt (-> invoice :from_name) id)
     :from-fields (-> (decrypt (-> invoice :from_fields) id)
                      (json/read-str :key-fn keyword))
     :to-name (decrypt (-> invoice :to_name) id)
     :to-fields (-> (decrypt (-> invoice :to_fields) id)
                    (json/read-str :key-fn keyword))
     :items (-> (decrypt (-> invoice :items) id)
                (json/read-str :key-fn keyword))
     :due-date (decrypt (-> invoice :due_date) id)
     :date-issued (decrypt (-> invoice :date_issued) id)
     :qty-type (decrypt (-> invoice :qty_type) id)
     :currency (decrypt (-> invoice :currency) id)
     :payment-details (decrypt (-> invoice :payment_details) id)
     :discount-name (decrypt (-> invoice :discount_name) id)
     :discount-percentage (-> (decrypt (-> invoice :discount_percentage) id)
                              (parse-float nil)) 
     :tax-type (decrypt (-> invoice :tax_type) id)
     :tax-name (decrypt (-> invoice :tax_name) id)
     :tax-percentage (-> (decrypt (-> invoice :tax_percentage) id)
                         (parse-float nil))}))

(defn update-invoice [id data]
  (update-invoice-sql
    datasource
    {:id id
     :nr (encrypt (-> data :nr) id)
     :from-name (encrypt (-> data :from-name) id)
     :from-company-name (encrypt (-> data :from-company-name) id)
     :from-company-address (encrypt (-> data :from-company-address) id)
     :from-company-extra (encrypt (-> data :from-company-extra) id)
     :to-name (encrypt (-> data :to-name) id)
     :to-company-name (encrypt (-> data :to-company-name) id)
     :to-company-address (encrypt (-> data :to-company-address) id)
     :to-company-extra (encrypt (-> data :to-company-extra) id)
     :items (encrypt (json/write-str (-> data :items)) id)
     :due-date (encrypt (-> data :due-date) id)
     :date-issued (encrypt (-> data :date-issued) id)
     :qty-type (encrypt (-> data :qty-type) id)
     :currency (encrypt (-> data :currency) id)
     :payment-details (encrypt (-> data :payment-details) id)
     :discount-name (encrypt (-> data :discount-name) id)
     :discount-percentage (encrypt (-> data :discount-percentage str) id)
     :tax-type (encrypt (-> data :tax-type) id)
     :tax-name (encrypt (-> data :tax-name) id)
     :tax-percentage (encrypt (-> data :tax-percentage str) id)}))
                 
(defn create-invoice [data]
  (let [id (str (random-uuid))]
    (create-invoice-sql
      datasource
      {:id id
       :nr (encrypt (-> data :nr str) id)
       :from-name (encrypt (-> data :from-name) id)
       :from-fields (encrypt (-> data :from-fields) id)
       :to-name (encrypt (-> data :to-name) id)
       :to-fields (encrypt (-> data :to-fields) id)
       :items (encrypt (-> data :items json/write-str) id)
       :due-date (encrypt (-> data :due-date) id)
       :date-issued (encrypt (-> data :date-issued) id)
       :qty-type (encrypt (-> data :qty-type) id)
       :currency (encrypt (-> data :currency) id)
       :payment-details (encrypt (-> data :payment-details) id)
       :discount-name (encrypt (-> data :discount-name) id)
       :discount-percentage (encrypt (-> data :discount-percentage str) id)
       :tax-type (encrypt (-> data :tax-type) id)
       :tax-name (encrypt (-> data :tax-name) id)
       :tax-percentage (encrypt (-> data :tax-percentage str) id)})
    id))
                 
(defn delete-invoice [id]
  (delete-invoice-sql datasource {:id id}))
