(ns invobi.utils.layout
  (:require
    [hiccup.page :refer [html5]]))

(defn wrap-page [opts contents]
  (html5
    {:lang (:lang opts)}
    [:head
     [:title (:title opts)]
     [:meta {:charset "utf-8"}]
     [:link {:rel "stylesheet" :href "/css/app.css"}]
     [:script {:src "/js/language-switcher.js"}]
     [:script {:src "/js/line-options.js"}]
     [:script {:src "/js/textarea.js"}]
     [:script {:src "/js/qty-type.js"}]
     [:script {:src "https://unpkg.com/htmx.org@1.8.6"}]
     [:script {:src "/js/htmx.js"}]]
    [:body
     contents]))

