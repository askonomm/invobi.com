(ns invobi.components.invoice.currency
  (:require
    [invobi.components.invoice.line-options :as line-options]
    [invobi.components :refer [option]]))

(def currencies [{:name "USD"
                  :symbol "$"}
                 {:name "EUR"
                  :symbol "€"}
                 {:name "GBP"
                  :symbol "£"}
                 {:name "JPY"
                  :symbol "¥"}])

(defn main [{:keys [invoice lang]}]
  [:div
   (line-options/main
     {:icon (case (:currency invoice)
              "EUR" "euro"
              "USD" "dollar"
              "GBP" "sterling"
              "JPY" "yen"
              "euro")
      :style "margin-top: -70px;"}
     (for [currency currencies]
       (when-not (= (:name currency) (:currency invoice))
         (option
           {:path (str "/" lang "/invoice/" (:id invoice) "/currency/" (:name currency))
            :size "small"
            :type "blank"
            :no-border? true}
           [:span.symbol (:symbol currency)] (:name currency)))))])