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
              "EUR" "euro-sign"
              "USD" "dollar-sign"
              "GBP" "sterling-sign"
              "JPY" "yen-sign"
              "euro-sign")
      :style "margin-top: -70px;"}
     (for [currency currencies]
       (when-not (= (:name currency) (:currency invoice))
         (option
           {:href (str "/" lang "/invoice/" (:id invoice) "/currency/" (:name currency))}
           [:span.symbol (:symbol currency)] (:name currency)))))])
