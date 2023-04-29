(ns invobi.components.invoice.common
  (:require
     [invobi.components :refer [button input textarea option]]
     [invobi.components.invoice.line-options :as line-options]
     [invobi.components.table :as table]
     [invobi.utils :refer [format-currency parse-float translate]]))

(defn delete-field 
  "Delete a field from the invoice"
  [direction {:keys [id invoice-id lang]}]
  (button
    {:class "delete-field color-warning"
     :size "small"
     :type "transparent"
     :hx {:hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/delete-" direction "-field")
          :hx-swap "delete"
          :hx-target "closest .field"}}
    (translate lang :delete-field)))

(defn field-value
  "The value of a field"
  [direction {:keys [id invoice-id lang value]}]
  (textarea {:full-width? true
             :placeholder (translate lang :field-label)
             :value value
             :hx {:name "value"
                  :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-" direction "-field-value")
                  :hx-trigger "input changed delay:250ms"}}))

(defn field-label
  "The label of a field"
  [direction {:keys [id invoice-id lang value]}]
  (input {:full-width? true
          :placeholder (translate lang :field-value)
          :class "field-label"
          :value value
          :hx {:name "label"
               :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-" direction "-field-label")
               :hx-trigger "input changed delay:250ms"}}))

(defn item 
  "An item in the invoice"
  [{:keys [id name qty price]} invoice-id currency lang]
  (table/row
    {:class "item"}
    (table/column
      {}
      (input {:full-width? true
              :type "text"
              :value name
              :hx {:name "name"
                   :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-item-name")
                   :hx-trigger "input changed delay:250ms"}}))
    (table/column
      {}
      (input {:full-width? true
              :type "number"
              :value qty
              :hx {:name "qty"
                   :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-item-qty")
                   :hx-swap "innerHTML"
                   :hx-trigger "input changed delay:250ms"
                   :hx-target "next .total-price"}}))
    (table/column
      {}
      (input {:full-width? true
              :type "number"
              :value price
              :hx {:name "price"
                   :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-item-price")
                   :hx-swap "innerHTML"
                   :hx-trigger "input changed delay:250ms"
                   :hx-target "next .total-price"}}))
    (table/column
      {:class "total-price"
       :align "right"
       :highlight? true}
      (format-currency (* (parse-float qty) (parse-float price)) currency))
    (line-options/main 
      {}
      (option 
        {:hx {:hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/delete-item")
              :hx-swap "delete"
              :hx-target "closest .item"}}
        (translate lang :remove-item)))))

(defn discount [{:keys [invoice-id discount-name discount-percentage]}]
  (table/main
    {}
    (table/row
      {}
      (table/column
        {}
        "asdasd"))))

