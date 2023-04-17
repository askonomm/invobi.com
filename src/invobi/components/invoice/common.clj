(ns invobi.components.invoice.common
  (:require
    [invobi.components :refer [textarea input]]
    [invobi.components.table :as table]
    [invobi.utils :refer [translate]]))

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

(defn item [{:keys [id name qty price]} invoice-id lang]
  (table/row
    {}
    (table/column
      {}
      (input {:full-width? true
              :type "text"
              :value name
              :hx {:name "name"
                   :hx-post (str "/api/" lang "/invoice/" invoice-id "/update-item-name/" id)}}))
    (table/column
      {}
      qty)
    (table/column
      {}
      price)
    (table/column
      {:align "right"}
      "0")))