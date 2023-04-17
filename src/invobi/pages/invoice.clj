(ns invobi.pages.invoice
  (:require
    [invobi.components :refer [header footer]]
    [invobi.components.invoice.nr :as invoice.nr]
    [invobi.components.invoice.head :as invoice.head]
    [invobi.components.invoice.currency :as invoice.currency]
    [invobi.components.invoice.items :as invoice.items]))

(defn- invoice [data]
  [:div.invoice
   (invoice.nr/main data)
   [:hr.invoice-separator]
   (invoice.head/main data)
   [:hr.invoice-separator]
   (invoice.currency/main data)
   (invoice.items/main data)])

(defn main [data]
  (list
    (header data)
    (invoice data)
    (footer data)))