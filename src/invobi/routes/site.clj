(ns invobi.routes.site
  (:require
    [invobi.db.invoices :as db]
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
        id (db/create-invoice {})]
    (->redirect (str "/" (:lang data) "/invoice/" id))))

(defn- invoice [request]
  (if-let [invoice (db/get-invoice (-> request :params :id))]
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
   {:path "/:lang/invoice/:id"
    :method :get
    :response invoice}
   {:path "/:lang"
    :method :get
    :response home}])