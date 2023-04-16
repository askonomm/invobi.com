(ns invobi.routes.api.invoices
  (:require
    [clojure.java.shell :as sh]
    [invobi.db.invoices :as db]
    [invobi.utils :refer [<-json]]
    [invobi.utils.response :refer [->json ->pdf]]
    [clojure.string :as string])
  (:import (java.io FileInputStream)))

(defn- download-invoice [{params :params}]
  (when-let [invoice (db/get-invoice (-> params :id))]
    (let [dev? (boolean (System/getenv "DEV"))
          path (if dev? "./" "/apps/invo/pdfs/")
          url (if dev? "http://localhost:8080" "https://invo.ee")
          id (-> invoice :id)
          lang (-> params :lang)
          file-name (-> invoice :nr (string/replace #" " "-"))
          stored-file-name (str (random-uuid))
          pdfjs-path (if dev? "cli/pdf.js" "api/pdf.js")]
      (sh/sh "node" pdfjs-path "--url" (str url "/" lang "/invoice/" id "?pdf=1") "--name" stored-file-name)
      (->pdf file-name (FileInputStream. (str path stored-file-name ".pdf"))))))

(defn- get-invoice [{params :params}]
  (let [invoice (db/get-invoice (-> params :id))]
    (->json invoice)))

(defn- update-invoice [data]
  (db/update-invoice (-> data :params :id) (<-json data))
  (->json {:msg :invoice-updated}))

(defn- create-invoice [data]
  (let [id (db/create-invoice (<-json data))]
    (->json {:id id
             :msg :invoice-created})))

(defn- delete-invoice [{params :params}]
  (db/delete-invoice (-> params :id))
  (->json {:msg :invoice-deleted}))

(def routes
  [{:path "/api/:lang/invoice/:id/download"
    :method :get
    :response download-invoice}
   {:path "/api/invoice/:id"
    :method :delete
    :response delete-invoice}
   {:path "/api/invoice/:id"
    :method :get
    :response get-invoice}
   {:path "/api/invoice/:id"
    :method :patch
    :response update-invoice}
   {:path "/api/invoice"
    :method :post
    :response create-invoice}])
