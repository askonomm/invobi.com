(ns invobi.components.invoice.items
  (:require
    [invobi.components :refer [button]]
    [invobi.components.table :as table]
    [invobi.components.invoice.common :as common]
    [invobi.utils :refer [translate]]))

(defn main [{:keys [lang invoice]}]
  (list
    [:h3.invoice-heading (translate lang :items)]
    (table/main
      {:id "items-table"
       :cols "items"}
      (table/row
        {:heading? true}
        (table/column {} "Name")
        (table/column {} "QTY")
        (table/column {} "Price")
        (table/column {:align "right"} "Total"))
      (for [i (-> invoice :items)]
        (common/item i (:id invoice) lang)))
    (button
      {:style "margin-top: 25px;"
       :hx {:name "add-item"
            :hx-post (str "/api/" lang "/invoice/" (:id invoice) "/add-item")
            :hx-swap "beforeend"
            :hx-target "#items-table"}}
      (translate lang :add-item))))