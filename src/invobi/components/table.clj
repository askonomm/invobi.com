(ns invobi.components.table)

(defn main
  ([children]
   (main {} children))
  ([{:keys [id cols row-gap]} & children]
   (let [class (cond-> "invoice-table" 
                 cols (str " " cols "-cols") 
                 row-gap (str " " row-gap "-rowgap"))]
     [:div {:id id
            :class class}
      children])))

(defn row
  ([content]
   (row {} content))
  ([{:keys [heading? class]} & children]
   [:div {:class (cond-> "row" 
                   heading? (str " heading")
                   class (str " " class))}
    children]))

(defn column
  ([children]
   (column {} children))
  ([{:keys [highlight? nowrap? align class]} & children]3
   (let [class (cond-> "column"
                 class (str " " class) 
                 align (str " align-" align) 
                 highlight? (str " highlight") 
                 nowrap? (str " nowrap"))]
     [:div {:class class}
      children])))

