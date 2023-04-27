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
          (format-currency subtotal (:currency invoice)))))))
