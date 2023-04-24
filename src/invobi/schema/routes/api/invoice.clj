(ns invobi.schema.routes.api.invoice)

(def UpdateNr
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["nr" string?]]]])

(def UpdateFromName
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["from-name" string?]]]])

(def UpdateToName
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["to-name" string?]]]])

(def UpdateDateIssued
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["date-issued" string?]]]])

(def UpdateDueDate
  [:map
   [:params [:map [:id string?]]]
   [:form-params [:map ["due-date" string?]]]])

(def AddFromField
  [:map
   [:params [:map [:id string?]]]])

(def UpdateFromFieldLabel
  [:map
   [:params [:map
             [:id string?]
             [:field-id string?]]]
   [:form-params [:map ["label" string?]]]])

(def UpdateFromFieldValue
  [:map
   [:params [:map
             [:id string?]
             [:field-id string?]]]
   [:form-params [:map ["value" string?]]]])

(def DeleteFromField
  [:map
   [:params [:map
             [:id string?]
             [:field-id string?]]]])

(def AddToField
  [:map
   [:params [:map [:id string?]]]])

(def UpdateToFieldLabel
  [:map
   [:params [:map
             [:id string?]
             [:field-id string?]]]
   [:form-params [:map ["label" string?]]]])

(def UpdateToFieldValue
  [:map
   [:params [:map
             [:id string?]
             [:field-id string?]]]
   [:form-params [:map ["value" string?]]]])

(def DeleteToField
  [:map
   [:params [:map
             [:id string?]
             [:field-id string?]]]])

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