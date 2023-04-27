(ns invobi.components.invoice.head
  (:require
    [invobi.components :refer [button input textarea]]
    [invobi.components.invoice.common :refer [delete-field field-label field-value]]
    [invobi.components.table :as table]
    [invobi.utils :refer [translate format-date]]))
   
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

(defn- from-fields [invoice lang pdf?]
  (if pdf?
    [:div "???"]
    (list
      (for [field (:from-fields invoice)]
        [:div.field
         (list
           (delete-field "from" {:id (-> field :id)
                                 :invoice-id (-> invoice :id)
                                 :lang lang})
           (field-label "from" {:id (-> field :id)
                                :invoice-id (-> invoice :id)
                                :lang lang
                                :value (-> field :label)})
           (field-value "from" {:id (-> field :id)
                                :invoice-id (-> invoice :id)
                                :lang lang
                                :value (-> field :value)}))])
      (button
        {:style "margin-top: 15px;"
         :size "small"
         :type "blank"
         :hx {:hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/add-from-field")
              :hx-swap "beforebegin"}}
        (translate lang :add-field)))))

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

(defn- to-fields [invoice lang pdf?]
  (if pdf?
    [:div "???"]
    (list
      (for [field (:to-fields invoice)]
        [:div.field
         (list
           (delete-field "to" {:id (-> field :id)
                               :invoice-id (-> invoice :id)
                               :lang lang})
           (field-label "to" {:id (-> field :id)
                              :invoice-id (-> invoice :id)
                              :lang lang
                              :value (-> field :label)})
           (field-value "to" {:id (-> field :id)
                              :invoice-id (-> invoice :id)
                              :lang lang
                              :value (-> field :value)}))])
      (button
        {:style "margin-top: 15px;"
         :size "small"
         :type "blank"
         :hx {:hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/add-to-field")
              :hx-swap "beforebegin"}}
        (translate lang :add-field)))))

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
    (table/main 
      {:cols "heading"
       :row-gap "large"}
      (table/row 
        {}
        (table/column
          {}
          [:h3.invoice-heading (translate lang :from)]
          (from-name invoice lang pdf?)
          (from-fields invoice lang pdf?))
        (table/column
          {}
          [:h3.invoice-heading (translate lang :to)]
          (to-name invoice lang pdf?)
          (to-fields invoice lang pdf?))
        (table/column
          {}
          [:h3.invoice-heading (translate lang :date-issued)]
          (date-issued invoice lang pdf?))
        (table/column
          {}
          [:h3.invoice-heading (translate lang :due-date)]
          (due-date invoice lang pdf?))))))

