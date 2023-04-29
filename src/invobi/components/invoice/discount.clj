(ns invobi.components.invoice.discount
  (:require 
   [invobi.components :refer [button]] 
   [invobi.utils :refer [translate]]))

(defn main [{:keys [lang invoice]}]
  [:div.discount
   (if (nil? (:discount-percentage invoice))
     (button
      {:size "small"
       :type "blank"
       :hx {:name "add-discount"
            :hx-post (str "/api/" lang "/invoice/" (:id invoice) "/add-discount")
            :hx-swap "innerHTML"
            :hx-target ".discount"}}
      (translate lang :add-discount))
     [:div "discount"])])
