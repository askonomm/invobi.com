(ns invobi.routes.api.invoice
  (:require
    [invobi.components.invoice.common :as common]
    [invobi.db.invoice :as db]
    [invobi.utils.response :refer [->html ->json]]
    [invobi.utils :refer [route-middleware]]
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

(defn- update-from-company-name [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [from-company-name]} (-> request :form-params)]
    (db/update-from-company-name id from-company-name)
    (->json {:status "ok"})))

(defn- update-from-company-address [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [from-company-address]} (-> request :form-params)]
    (db/update-from-company-address id from-company-address)
    (->json {:status "ok"})))

(defn- add-from-company-extra [request]
  (let [{:keys [lang id]} (-> request :params)]
    (db/update-from-company-extra id "")
    (->html (list
              (common/from-company-extra-label
                {:id id
                 :lang lang
                 :value ""})
              (common/from-company-extra-textarea
                {:id id
                 :lang lang
                 :value ""})))))

(defn- update-from-company-extra [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [from-company-extra]} (-> request :form-params)]
    (db/update-from-company-extra id from-company-extra)
    (->json {:status "ok"})))

(defn- update-from-company-extra-label [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [from-company-extra-label]} (-> request :form-params)]
    (db/update-from-company-extra-label id from-company-extra-label)
    (->json {:status "ok"})))

(defn- update-to-name [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [to-name]} (-> request :form-params)]
    (db/update-to-name id to-name)
    (->json {:status "ok"})))

(defn- update-to-company-name [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [to-company-name]} (-> request :form-params)]
    (db/update-to-company-name id to-company-name)
    (->json {:status "ok"})))

(defn- update-to-company-address [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [to-company-address]} (-> request :form-params)]
    (db/update-to-company-address id to-company-address)
    (->json {:status "ok"})))

(defn- add-to-company-extra [request]
  (let [{:keys [lang id]} (-> request :params)]
    (db/update-to-company-extra id "")
    (->html (list
              (common/to-company-extra-label
                {:id id
                 :lang lang
                 :value ""})
              (common/to-company-extra-textarea
                {:id id
                 :lang lang
                 :value ""})))))

(defn- update-to-company-extra [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [to-company-extra]} (-> request :form-params)]
    (db/update-to-company-extra id to-company-extra)
    (->json {:status "ok"})))

(defn- update-to-company-extra-label [request]
  (let [{:keys [id]} (-> request :params)
        {:strs [to-company-extra-label]} (-> request :form-params)]
    (db/update-to-company-extra-label id to-company-extra-label)
    (->json {:status "ok"})))

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
        items (db/get-items id)
        new-item {:id (str (random-uuid))
                  :name ""
                  :qty 1
                  :price 0}]
    (db/update-items id (conj items new-item))
    (->html (common/item new-item id lang))))

(def routes
  [{:path "/api/:lang/invoice/:id/update-nr"
    :method :post
    :response #(route-middleware % update-nr schema/UpdateNr)}
   {:path "/api/:lang/invoice/:id/update-from-name"
    :method :post
    :response #(route-middleware % update-from-name schema/UpdateFromName)}
   {:path "/api/:lang/invoice/:id/update-from-company-name"
    :method :post
    :response #(route-middleware % update-from-company-name schema/UpdateFromCompanyName)}
   {:path "/api/:lang/invoice/:id/update-from-company-address"
    :method :post
    :response #(route-middleware % update-from-company-address schema/UpdateFromCompanyAddress)}
   {:path "/api/:lang/invoice/:id/add-from-company-extra"
    :method :post
    :response #(route-middleware % add-from-company-extra)}
   {:path "/api/:lang/invoice/:id/update-from-company-extra"
    :method :post
    :response #(route-middleware % update-from-company-extra schema/UpdateFromCompanyExtra)}
   {:path "/api/:lang/invoice/:id/update-from-company-extra-label"
    :method :post
    :response #(route-middleware % update-from-company-extra-label schema/UpdateFromCompanyExtraLabel)}
   {:path "/api/:lang/invoice/:id/update-to-name"
    :method :post
    :response #(route-middleware % update-to-name schema/UpdateToName)}
   {:path "/api/:lang/invoice/:id/update-to-company-name"
    :method :post
    :response #(route-middleware % update-to-company-name schema/UpdateToCompanyName)}
   {:path "/api/:lang/invoice/:id/update-to-company-address"
    :method :post
    :response #(route-middleware % update-to-company-address schema/UpdateToCompanyAddress)}
   {:path "/api/:lang/invoice/:id/add-to-company-extra"
    :method :post
    :response #(route-middleware % add-to-company-extra)}
   {:path "/api/:lang/invoice/:id/update-to-company-extra"
    :method :post
    :response #(route-middleware % update-to-company-extra schema/UpdateToCompanyExtra)}
   {:path "/api/:lang/invoice/:id/update-to-company-extra-label"
    :method :post
    :response #(route-middleware % update-to-company-extra-label schema/UpdateToCompanyExtraLabel)}
   {:path "/api/:lang/invoice/:id/update-date-issued"
    :method :post
    :response #(route-middleware % update-date-issued schema/UpdateDateIssued)}
   {:path "/api/:lang/invoice/:id/update-due-date"
    :method :post
    :response #(route-middleware % update-due-date schema/UpdateDueDate)}
   {:path "/api/:lang/invoice/:id/add-item"
    :method :post
    :response #(route-middleware % add-item schema/AddItem)}])

