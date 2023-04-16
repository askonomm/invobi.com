(ns invobi.components.invoice.head
  (:require
    [invobi.components :refer [button input textarea]]
    [invobi.components.invoice.common :as invoice.common]
    [invobi.components.table :as table]
    [invobi.utils :refer [translate format-date]]
    [clojure.string :as string]))

(defn- from-name [invoice lang pdf?]
  (if pdf?
    [:div.text-bold (-> invoice :from-name)]
    (textarea {:full-width? true
               :class "text-bold"
               :placeholder (translate lang :name)
               :value (-> invoice :from-name)
               :hx {:name "from-name"
                    :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-from-name")
                    :hx-trigger "keyup changed delay:250ms"}})))

(defn- from-company-name [invoice lang pdf?]
  (if pdf?
    [:div.text-bold (-> invoice :from-company-name)]
    (textarea {:full-width? true
               :class "text-bold"
               :placeholder (translate lang :company-name)
               :value (-> invoice :from-company-name)
               :hx {:name "from-company-name"
                    :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-from-company-name")
                    :hx-trigger "keyup changed delay:250ms"}})))

(defn- from-company-address [invoice lang pdf?]
  (if pdf?
    [:div (-> invoice :from-company-address)]
    (textarea {:full-width? true
               :placeholder (translate lang :company-address)
               :value (-> invoice :from-company-address)
               :hx {:name "from-company-address"
                    :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-from-company-address")
                    :hx-trigger "keyup changed delay:250ms"}})))

(defn- from-company-extra [invoice lang pdf?]
  (if pdf?
    [:div (-> invoice :from-company-extra)]
    (if (string/blank? (-> invoice :from-company-extra))
      (button
        {:size "small"
         :type "blank"
         :hx {:hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/add-from-company-extra")
              :hx-swap "outerHTML"}}
        (translate lang :add-company-extra))
      (list
        (invoice.common/from-company-extra-label
          {:id (-> invoice :id)
           :lang lang
           :value (-> invoice :from-company-extra-label)})
        (invoice.common/from-company-extra-textarea
          {:id (-> invoice :id)
           :lang lang
           :value (-> invoice :from-company-extra)})))))

(defn- to-name [invoice lang pdf?]
  (if pdf?
    [:div.text-bold (-> invoice :to-name)]
    (textarea {:full-width? true
               :class "text-bold"
               :placeholder (translate lang :name)
               :value (-> invoice :to-name)
               :hx {:name "to-name"
                    :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-to-name")
                    :hx-trigger "keyup changed delay:250ms"}})))

(defn- to-company-name [invoice lang pdf?]
  (if pdf?
    [:div.text-bold (-> invoice :to-company-name)]
    (textarea {:full-width? true
               :class "text-bold"
               :placeholder (translate lang :company-name)
               :value (-> invoice :to-company-name)
               :hx {:name "to-company-name"
                    :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-to-company-name")
                    :hx-trigger "keyup changed delay:250ms"}})))

(defn- to-company-address [invoice lang pdf?]
  (if pdf?
    [:div (-> invoice :to-company-address)]
    (textarea {:full-width? true
               :placeholder (translate lang :company-address)
               :value (-> invoice :to-company-address)
               :hx {:name "to-company-address"
                    :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-to-company-address")
                    :hx-trigger "keyup changed delay:250ms"}})))

(defn- to-company-extra [invoice lang pdf?]
  (if pdf?
    [:div (-> invoice :to-company-extra)]
    (if (string/blank? (-> invoice :to-company-extra))
      (button
        {:size "small"
         :type "blank"
         :hx {:hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/add-to-company-extra")
              :hx-swap "outerHTML"}}
        (translate lang :add-company-extra))
      (list
        (invoice.common/to-company-extra-label
          {:id (-> invoice :id)
           :lang lang
           :value (-> invoice :to-company-extra-label)})
        (invoice.common/to-company-extra-textarea
          {:id (-> invoice :id)
           :lang lang
           :value (-> invoice :to-company-extra)})))))

(defn- date-issued [invoice lang pdf?]
  (if pdf?
    [:div (format-date (-> invoice :date-issued) lang)]
    (input {:type "date"
            :value (-> invoice :date-issued)
            :hx {:name "date-issued"
                 :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-date-issued")}})))

(defn- due-date [invoice lang pdf?]
  (if pdf?
    [:div (format-date (-> invoice :due-date) lang)]
    (input {:type "date"
            :value (-> invoice :due-date)
            :hx {:name "due-date"
                 :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-due-date")}})))

(defn main [data]
  (let [lang (-> data :lang)
        pdf? (-> data :pdf?)
        invoice (-> data :invoice)]
    (table/main {:cols "heading"
                 :row-gap "large"}
                (table/row
                  {}
                  (table/column
                    {}
                    [:h3.invoice-heading (translate lang :from)]
                    (from-name invoice lang pdf?)
                    (from-company-name invoice lang pdf?)
                    (from-company-address invoice lang pdf?)
                    (from-company-extra invoice lang pdf?))
                  (table/column
                    {}
                    [:h3.invoice-heading (translate lang :to)]
                    (to-name invoice lang pdf?)
                    (to-company-name invoice lang pdf?)
                    (to-company-address invoice lang pdf?)
                    (to-company-extra invoice lang pdf?))
                  (table/column
                    {}
                    [:h3.invoice-heading (translate lang :date-issued)]
                    (date-issued invoice lang pdf?))
                  (table/column
                    {}
                    [:h3.invoice-heading (translate lang :due-date)]
                    (due-date invoice lang pdf?))))))


