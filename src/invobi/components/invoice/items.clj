(ns invobi.components.invoice.items
  (:require
    [invobi.utils :refer [translate]]))

(defn main [data]
  (let [lang (:lang data)]
    (list
      [:h3.invoice-heading (translate lang :items)])))