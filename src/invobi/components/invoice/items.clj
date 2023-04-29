(ns invobi.components.invoice.items 
  (:require
     [invobi.components :refer [button options option]]
     [invobi.components.invoice.common :as common]
     [invobi.components.table :as table]
     [invobi.utils :refer [translate]]))

(defn- qty [qty-type id lang]
  (prn "qty type: " qty-type)
  [:div.qty-type 
    (button
      {:size "small"
       :type "transparent"
       :onclick "toggleQtyTypeOptions()"}
      (translate lang (keyword qty-type)))
    (options
      (when-not (= qty-type "qty")
        (option
          {:href (str "/" lang "/invoice/" id "/qty-type/qty")}
          (translate lang :qty)))
      (when-not (= qty-type "hr")
        (option
          {:href (str "/" lang "/invoice/" id "/qty-type/hr")} 
          (translate lang :hr))))])

(defn main [{:keys [lang invoice]}]
  (list
    [:h3.invoice-heading (translate lang :items)]
    (table/main
      {:id "items-table"
       :cols "items"}
      (table/row
        {:heading? true}
        (table/column {} (translate lang :name))
        (table/column {} (qty (:qty-type invoice) (:id invoice) lang))
        (table/column {} (translate lang :price))
        (table/column {:align "right"} (translate lang :total)))
      (for [i (-> invoice :items)]
        (common/item i (:id invoice) (:currency invoice) lang)))
    (button
      {:style "margin-top: 25px;"
       :hx {:name "add-item"
            :hx-post (str "/api/" lang "/invoice/" (:id invoice) "/add-item")
            :hx-swap "beforeend"
            :hx-target "#items-table"}}
      (translate lang :add-item))))
