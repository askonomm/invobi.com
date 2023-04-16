(ns invobi.pages.home
  (:require
    [invobi.utils :refer [translate]]
    [invobi.components :refer [button header footer]]))

(defn- about [data]
  [:div.about
   [:div.masthead
    [:h1 (translate (-> data :lang) :about-title)]
    [:p (translate (-> data :lang) :about-subtitle)]
    (button
      {:size "large"
       :href (str "/" (-> data :lang) "/create-invoice")}
      (translate (-> data :lang) :create-invoice))
    [:div.features
     [:h2 (translate (-> data :lang) :about-features)]
     [:div.feature
      [:div.icon "\uD83D\uDDBC️"]
      [:h3 (translate (-> data :lang) :about-intuitive-interface)]
      [:p (translate (-> data :lang) :about-intuitive-interface-full)]]
     [:div.feature
      [:div.icon "\uD83D\uDCB1"]
      [:h3 (translate (-> data :lang) :about-multilinguage)]
      [:p (translate (-> data :lang) :about-multilinguage-full)]]
     [:div.feature
      [:div.icon "\uD83D\uDD75"]
      [:h3 (translate (-> data :lang) :about-privatelink)]
      [:p (translate (-> data :lang) :about-privatelink-full)]]
     [:div.feature
      [:div.icon "\uD83D\uDE4C"]
      [:h3 (translate (-> data :lang) :about-foreverfree)]
      [:p (translate (-> data :lang) :about-foreverfree-full)]]]]])

(defn main [data]
  (list
    (header data)
    (about data)
    (footer data)))