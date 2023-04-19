(ns invobi.routes.api.invoice
  (:require
    [invobi.components.invoice.common :as common]
    [invobi.db.invoice :as db]
    [invobi.utils.response :refer [->html ->json]]
    [invobi.utils :refer [route-middleware parse-float format-currency]]
    [invobi.schema.routes.api.invoice :as schema]))

(defn- update-nr [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [nr]} (-> request :form-params)]
    (db/update-nr id nr)
    (->json {:status "ok"})))

(defn- update-from-name [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [from-name]} (-> request :form-params)]
    (db/update-from-name id from-name)
    (->json {:status "ok"})))

(defn- add-from-field [request]
  (let [{:keys [lang id]} (-> request :params)
        fields (db/get-from-fields id)
        field-id (str (random-uuid))]
    (db/update-from-fields id (conj fields {:id field-id
                                            :label ""
                                            :value ""}))
    (->html
      (list
        (common/field-label "from" {:id field-id
                                    :invoice-id id
                                    :lang lang
                                    :value ""})
        (common/field-value "from" {:id field-id
                                    :invoice-id id
                                    :lang lang
                                    :value ""})))))

(defn- update-to-name [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [to-name]} (-> request :form-params)]
    (db/update-to-name id to-name)
    (->json {:status "ok"})))

(defn- add-to-field [request]
  (let [{:keys [lang id]} (-> request :params)
        fields (db/get-to-fields id)
        field-id (str (random-uuid))]
    (db/update-to-fields id (conj fields {:id field-id
                                          :label ""
                                          :value ""}))
    (->html
      (list
        (common/field-label "to" {:id field-id
                                  :invoice-id id
                                  :lang lang
                                  :value ""})
        (common/field-value "to" {:id field-id
                                  :invoice-id id
                                  :lang lang
                                  :value ""})))))

(defn- update-date-issued [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [date-issued]} (-> request :form-params)]
    (db/update-date-issued id date-issued)
    (->json {:status "ok"})))

(defn- update-due-date [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [due-date]} (-> request :form-params)]
    (db/update-due-date id due-date)
    (->json {:status "ok"})))

(defn- add-item [request]
  (let [{:keys [id lang]} (-> request :params)
        currency (db/get-currency id)
        items (db/get-items id)
        new-item {:id (str (random-uuid))
                  :name ""
                  :qty 1
                  :price 0}]
    (db/update-items id (conj items new-item))
    (->html (common/item new-item id currency lang))))

(defn- update-item-name [request]
  (let [{:keys [id item-id]} (-> request :params)
        {:strs [name]} (-> request :form-params)
        items (db/get-items id)
        new-items (map #(if (= (:id %) item-id)
                          (assoc % :name name)
                          %)
                       items)]
    (db/update-items id new-items)
    (->json {:status "ok"})))

(defn- update-item-qty [request]
  (let [{:keys [id item-id]} (-> request :params)
        {:strs [qty]} (-> request :form-params)
        currency (db/get-currency id)
        items (db/get-items id)
        item (->> items
                  (filter #(= (:id %) item-id))
                  first)
        qty (parse-float qty)
        new-items (map #(if (= (:id %) item-id)
                          (assoc % :qty qty)
                          %)
                       items)]
    (db/update-items id new-items)
    (->html [:span (format-currency (* qty (:price item)) currency)])))

(defn- update-item-price [request]
  (let [{:keys [id item-id]} (-> request :params)
        {:strs [price]} (-> request :form-params)
        currency (db/get-currency id)
        items (db/get-items id)
        item (->> items
                  (filter #(= (:id %) item-id))
                  first)
        price (parse-float price)
        new-items (map #(if (= (:id %) item-id)
                          (assoc % :price price)
                          %)
                       items)]
    (db/update-items id new-items)
    (->html [:span (format-currency (* price (:qty item)) currency)])))

(def routes
  [{:path "/api/:lang/invoice/:id/update-nr"
    :method :post
    :response #(route-middleware % update-nr schema/UpdateNr)}
   {:path "/api/:lang/invoice/:id/update-from-name"
    :method :post
    :response #(route-middleware % update-from-name schema/UpdateFromName)}
   {:path "/api/:lang/invoice/:id/update-to-name"
    :method :post
    :response #(route-middleware % update-to-name schema/UpdateToName)}
   {:path "/api/:lang/invoice/:id/update-date-issued"
    :method :post
    :response #(route-middleware % update-date-issued schema/UpdateDateIssued)}
   {:path "/api/:lang/invoice/:id/update-due-date"
    :method :post
    :response #(route-middleware % update-due-date schema/UpdateDueDate)}
   {:path "/api/:lang/invoice/:id/add-from-field"
    :method :post
    :response #(route-middleware % add-from-field schema/AddFromField)}
   {:path "/api/:lang/invoice/:id/add-to-field"
    :method :post
    :response #(route-middleware % add-to-field schema/AddToField)}
   {:path "/api/:lang/invoice/:id/add-item"
    :method :post
    :response #(route-middleware % add-item schema/AddItem)}
   {:path "/api/:lang/invoice/:id/:item-id/update-item-name"
    :method :post
    :response #(route-middleware % update-item-name schema/UpdateItemName)}
   {:path "/api/:lang/invoice/:id/:item-id/update-item-qty"
    :method :post
    :response #(route-middleware % update-item-qty schema/UpdateItemQty)}
   {:path "/api/:lang/invoice/:id/:item-id/update-item-price"
    :method :post
    :response #(route-middleware % update-item-price schema/UpdateItemPrice)}])

