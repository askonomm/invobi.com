(ns invobi.components.invoice.common
  (:require
    [invobi.components :refer [textarea input button]]
    [invobi.components.table :as table]
    [invobi.utils :refer [translate format-currency format-float]]))

(defn delete-field [direction {:keys [id invoice-id lang]}]
  (button
    {:class "delete-field color-warning"
     :size "small"
     :type "transparent"
     :hx {:hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/delete-" direction "-field")
          :hx-swap "delete"
          :hx-target "closest .field"}}
    (translate lang :delete-field)))

(defn field-value [direction {:keys [id invoice-id lang value]}]
  (textarea {:full-width? true
             :placeholder (translate lang :field-label)
             :value value
             :hx {:name "value"
                  :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-" direction "-field-value")
                  :hx-trigger "keyup changed delay:250ms"}}))

(defn field-label [direction {:keys [id invoice-id lang value]}]
  (input {:full-width? true
          :placeholder (translate lang :field-value)
          :class "field-label"
          :value value
          :hx {:name "label"
               :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-" direction "-field-label")
               :hx-trigger "keyup changed delay:250ms"}}))

(defn item [{:keys [id name qty price]} invoice-id currency lang]
  (table/row
    {}
    (table/column
      {}
      (input {:full-width? true
              :type "text"
              :value name
              :hx {:name "name"
                   :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-item-name")
                   :hx-trigger "keyup changed delay:250ms"}}))
    (table/column
      {}
      (input {:full-width? true
              :type "number"
              :value (format-float qty)
              :hx {:name "qty"
                   :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-item-qty")
                   :hx-swap "innerHTML"
                   :hx-trigger "keyup"
                   :hx-target "next .total-price"}}))
    (table/column
      {}
      (input {:full-width? true
              :type "number"
              :value (format-float price)
              :hx {:name "price"
                   :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-item-price")
                   :hx-swap "innerHTML"
                   :hx-trigger "keyup"
                   :hx-target "next .total-price"}}))
    (table/column
      {:class "total-price"
       :align "right"}
      (format-currency (* qty price) currency))))