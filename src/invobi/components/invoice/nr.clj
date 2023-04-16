(ns invobi.components.invoice.nr
  (:require
    [invobi.utils :refer [translate]]
    [invobi.components :refer [input]]))

(defn main [data]
  (let [lang (-> data :lang)
        invoice (-> data :invoice)]
    [:h1.invoice-nr
     [:span (str (translate lang :invoice) " #")]
     (input {:placeholder "0001"
             :type "text"
             :value (-> invoice :nr)
             :hx {:name "nr"
                  :hx-post (str "/api/" lang "/invoice/" (-> invoice :id) "/update-nr")
                  :hx-trigger "keyup changed delay:250ms"}})]))