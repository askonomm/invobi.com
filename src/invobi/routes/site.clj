(ns invobi.routes.site
  (:require
    [clojure.data.json :as json]
    [clojure.string :as string]
    [invobi.db.invoices :as db.invoices]
    [invobi.db.invoice :as db]
    [invobi.utils :refer [data-from-request translate]]
    [invobi.utils.response :refer [->html-page ->redirect]]
    [invobi.pages.home :as pages.home]
    [invobi.pages.invoice :as pages.invoice]))

; TODO: redirect based on preferred language
(defn- redirect-with-lang [_]
  (->redirect "/en"))

(defn- home [request]
  (let [data (data-from-request request {:title "Asdasdasd"})]
    (->html-page data (pages.home/main data))))

(defn- create-invoice [request]
  (let [data (data-from-request request)
        id (db.invoices/create-invoice {:currency "EUR"
                                        :qty-type "qty"
                                        :from-fields (json/write-str [])
                                        :to-fields (json/write-str [])
                                        :items [{:id (str (random-uuid))
                                                 :name ""
                                                 :qty 1
                                                 :price 0}]})]
    (->redirect (str "/" (:lang data) "/invoice/" id))))

(defn- set-currency [request]
  (let [data (data-from-request request)
        id (-> request :params :id)
        allowed-currencies #{"EUR" "USD" "GBP" "JPY"}
        currency (-> request :params :currency string/upper-case)]
    (when (contains? allowed-currencies currency)
      (db/update-currency id currency))
    (->redirect (str "/" (:lang data) "/invoice/" id))))

(defn- set-qty-type [request]
  (let [data (data-from-request request)
        id (-> request :params :id)
        allowed-qty-types #{"qty" "hr"}
        qty-type (-> request :params :qty-type)]
    (when (contains? allowed-qty-types qty-type)
      (db/update-qty-type id qty-type))
    (->redirect (str "/" (:lang data) "/invoice/" id))))

(defn- invoice [request]
  (if-let [invoice (db.invoices/get-invoice (-> request :params :id))]
    (let [data (data-from-request
                 request
                 {:title (translate (-> request :params :lang) :edit-invoice)
                  :invoice invoice})]
      (->html-page data (pages.invoice/main data)))
    (->redirect "/")))

(def routes
  [{:path "/"
    :method :get
    :response redirect-with-lang}
   {:path "/:lang/create-invoice"
    :method :get
    :response create-invoice}
   {:path "/:lang/invoice/:id/currency/:currency"
    :method :get
    :response set-currency}
   {:path "/:lang/invoice/:id/qty-type/:qty-type"
    :method :get
    :response set-qty-type}
   {:path "/:lang/invoice/:id"
    :method :get
    :response invoice}
   {:path "/:lang"
    :method :get
    :response home}])
