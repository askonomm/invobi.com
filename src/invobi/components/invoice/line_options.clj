(ns invobi.components.invoice.line-options
  (:require
    [invobi.components :refer [options]]))

(defn main [{:keys [icon style]} & children]
  (let [icon (or icon "gear")]
    [:div.invoice-line-options
     {:style style}
     [:button.toggle-button
      [:i {:class (str "fa fa-" icon)}]]
     (options children)]))
