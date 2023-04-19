(ns invobi.components.invoice.common
  (:require
    [invobi.components :refer [textarea input]]
    [invobi.components.table :as table]
    [invobi.utils :refer [translate format-currency format-float]]))

(defn field-value [direction {:keys [id invoice-id lang value]}]
  (textarea {:full-width? true
             :placeholder (translate lang :company-extra)
             :value value
             :hx {:name "field-value"
                  :hx-post (str "/api/" lang "/invoice/" invoice-id "/" id "/update-" direction "-field-value")
                  :hx-trigger "keyup changed delay:250ms"}}))

(defn field-label [direction {:keys [id invoice-id lang value]}]
  (input {:full-width? true
          :placeholder (translate lang :company-extra-label)
          :class "field-label"
          :value value
          :hx {:name "field-label"
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