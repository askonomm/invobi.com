(ns invobi.components.invoice.common
  (:require
    [invobi.components :refer [textarea input]]
    [invobi.components.table :as table]
    [invobi.utils :refer [translate format-currency format-float]]))

(defn from-company-extra-textarea [{:keys [id lang value]}]
  (textarea {:full-width? true
             :placeholder (translate lang :company-extra)
             :value value
             :hx {:name "from-company-extra"
                  :hx-post (str "/api/" lang "/invoice/" id "/update-from-company-extra")
                  :hx-trigger "keyup changed delay:250ms"}}))

(defn from-company-extra-label [{:keys [id lang value]}]
  (input {:full-width? true
          :placeholder (translate lang :company-extra-label)
          :class "extra-field-label"
          :value value
          :hx {:name "from-company-extra-label"
               :hx-post (str "/api/" lang "/invoice/" id "/update-from-company-extra-label")
               :hx-trigger "keyup changed delay:250ms"}}))

(defn to-company-extra-textarea [{:keys [id lang value]}]
  (textarea {:full-width? true
             :placeholder (translate lang :company-extra)
             :value value
             :hx {:name "to-company-extra"
                  :hx-post (str "/api/" lang "/invoice/" id "/update-to-company-extra")
                  :hx-trigger "keyup changed delay:250ms"}}))

(defn to-company-extra-label [{:keys [id lang value]}]
  (input {:full-width? true
          :placeholder (translate lang :company-extra-label)
          :class "extra-field-label"
          :value value
          :hx {:name "to-company-extra-label"
               :hx-post (str "/api/" lang "/invoice/" id "/update-to-company-extra-label")
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