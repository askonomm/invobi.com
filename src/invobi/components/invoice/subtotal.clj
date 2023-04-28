(ns invobi.components.invoice.subtotal 
  (:require
     [invobi.components.table :as table]
     [invobi.utils :refer [format-currency parse-float translate]]))

(defn main [{:keys [lang invoice]}]
  (let [subtotal (->> invoice :items
                      (reduce 
                        (fn [acc i]
                          (+ acc (* (parse-float (:price i)) (parse-float (:qty i)))))
                        0))]  
    (table/main
      {:cols "two"}
      (table/row
        {}
        (table/column
          {:highlight? true}
          (translate lang :sub-total))
        (table/column
          {:align "right"
           :highlight? true}
          [:div 
           {:hx-get (str "/api/" lang "/invoice/" (:id invoice) "/subtotal")
            :hx-trigger "every 1s"
            :hx-swap "innerHTML"}
           (format-currency subtotal (:currency invoice))])))))
