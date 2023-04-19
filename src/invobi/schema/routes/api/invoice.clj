(ns invobi.schema.routes.api.invoice)

(def UpdateNr
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["nr" string?]]]])

(def UpdateFromName
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["from-name" string?]]]])

(def UpdateFromCompanyName
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["from-company-name" string?]]]])

(def UpdateFromCompanyAddress
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["from-company-address" string?]]]])

(def UpdateFromCompanyExtra
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["from-company-extra" string?]]]])

(def UpdateFromCompanyExtraLabel
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["from-company-extra-label" string?]]]])

(def UpdateToName
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["to-name" string?]]]])

(def UpdateToCompanyName
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["to-company-name" string?]]]])

(def UpdateToCompanyAddress
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["to-company-address" string?]]]])

(def UpdateToCompanyExtra
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["to-company-extra" string?]]]])

(def UpdateToCompanyExtraLabel
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["to-company-extra-label" string?]]]])

(def UpdateDateIssued
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["date-issued" string?]]]])

(def UpdateDueDate
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["due-date" string?]]]])

(def AddItem
  [:map
   [:params [:map [:id string?]]]])

(def UpdateItemName
  [:map
   [:params [:map
             [:id string?]
             [:item-id string?]]]
   [:form-params [:map ["name" string?]]]])

(def UpdateItemQty
  [:map
   [:params [:map
             [:id string?]
             [:item-id string?]]]
   [:form-params [:map ["qty" string?]]]])

(def UpdateItemPrice
  [:map
   [:params [:map
             [:id string?]
             [:item-id string?]]]
   [:form-params [:map ["price" string?]]]])