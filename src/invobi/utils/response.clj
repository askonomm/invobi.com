(ns invobi.utils.response
  (:require
    [clojure.data.json :as json]
    [hiccup.core :refer [html]]
    [invobi.utils.layout :refer [wrap-page]]))

(defn ->json
  ([data]
   (->json 200 data))
  ([status data]
   {:status status
    :headers {"Content-Type" "application/json"
              "Access-Control-Allow-Methods" "*"
              "Access-Control-Allow-Headers" "*"
              "Access-Control-Allow-Origin" "*"}
    :body (json/write-str data)}))

(defn ->html
  [contents]
  {:status 200
   :headers {"Content-Type" "text/html"
             "Access-Control-Allow-Methods" "*"
             "Access-Control-Allow-Headers" "*"
             "Access-Control-Allow-Origin" "*"}
   :body (html contents)})

(defn ->html-page
  ([contents]
   (->html-page {} contents))
  ([opts contents]
   {:status 200
    :headers {"Content-Type" "text/html"
              "Access-Control-Allow-Methods" "*"
              "Access-Control-Allow-Headers" "*"
              "Access-Control-Allow-Origin" "*"}
    :body (wrap-page opts (html contents))}))

(defn ->pdf
  [name data]
  {:status 200
   :headers {"Content-Type" "application/pdf"
             "Content-Disposition" (str "attachment; filename=\"" name ".pdf"\")
             "Access-Control-Allow-Headers" "*"
             "Access-Control-Allow-Origin" "*"}
   :body data})

(defn ->redirect
  ([path]
   (->redirect 302 path))
  ([status path]
   {:status status
    :headers {"Location" path}
    :body ""}))