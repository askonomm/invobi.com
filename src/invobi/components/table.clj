(ns invobi.components.table)

(defn main
  ([children]
   (main {} children))
  ([{:keys [cols row-gap]} & children]
   (let [class (cond-> "invoice-table"
                       cols (str " " cols "-cols")
                       row-gap (str " " row-gap "-rowgap"))]
     [:div {:class class}
      children])))

(defn row
  ([content]
   (row {} content))
  ([{:keys [heading?]} & children]
   [:div {:class (cond-> "row"
                         heading? (str " heading"))}
    children]))

(defn column
  ([children]
   (column {} children))
  ([{:keys [highlight? nowrap? align]} & children]
   (let [class (cond-> "column"
                       align (str " align-" align)
                       highlight? (str " highlight")
                       nowrap? (str " nowrap"))]
     [:div {:class class}
      children])))

