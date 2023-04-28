(ns invobi.utils.response
  (:require
    [clojure.data.json :as json]
    [hiccup.core :refer [html]]
    [invobi.utils.layout :refer [wrap-page]]))

(defn ->json
  "Returns a JSON response with the given data."
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
  "Returns an HTML response with the given contents."
  [contents]
  {:status 200
   :headers {"Content-Type" "text/html"
             "Access-Control-Allow-Methods" "*"
             "Access-Control-Allow-Headers" "*"
             "Access-Control-Allow-Origin" "*"}
   :body (html contents)})

(defn ->html-page
  "Returns an HTML response with the given contents wrapped in a page."
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
  "Returns a PDF response with the given data."
  [name data]
  {:status 200
   :headers {"Content-Type" "application/pdf"
             "Content-Disposition" (str "attachment; filename=\"" name ".pdf"\")
             "Access-Control-Allow-Headers" "*"
             "Access-Control-Allow-Origin" "*"}
   :body data})

(defn ->redirect
  "Returns a redirect response with the given path."
  ([path]
   (->redirect 302 path))
  ([status path]
   {:status status
    :headers {"Location" path}
    :body ""}))
