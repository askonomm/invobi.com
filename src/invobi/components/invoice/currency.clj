(ns invobi.components.invoice.currency
  (:require
    [invobi.components.invoice.line-options :as line-options]
    [invobi.components :refer [option]]))

(defn main [{:keys [invoice]}]
  (line-options/main
    {:icon (case (:currency invoice)
             "EUR" "euro"
             "USD" "dollar"
             "GBP" "sterling"
             "JPY" "yen"
             "euro")
     :style "margin-top: -70px;"}
    (option
      {:path "/"
       :size "small"
       :type "blank"
       :no-border? true}
      "EUR")))