(ns invobi.utils
  (:require
   [clojure.data.json :as json]
   [invobi.translations :refer [en es et]]
   [invobi.utils.response :refer [->html]]
   [io.randomseed.bankster.money :as money]
   [io.randomseed.bankster.scale :refer [ROUND_HALF_UP]]
   [malli.core :as m]
   [malli.error :as me])
  (:import
   (java.text DecimalFormat DecimalFormatSymbols NumberFormat)
   (java.time Year)
   (java.util Locale)))

(defn <-json [data]
  (-> data :body
      slurp
      (json/read-str :key-fn keyword)))

(defn lang-code->lang-name [code]
  (case code
    "en" "English"
    "et" "Eesti"
    "es" "Espanol"))

(defn translate [lang k]
  (case lang
    "en" (k en)
    "et" (k et)
    "es" (k es)))

(defn data-from-request
  ([request]
   (data-from-request request {}))
  ([request merge-with]
   (merge {:lang (-> request :params :lang)
           :current-path (-> request :uri)
           :current-pure-path (subs (-> request :uri) 3)
           :preferred-lang nil}
          merge-with)))

(defn current-year []
  (.getValue (Year/now)))

(defn format-date [input lang])

(defn validate [input schema]
  (if (m/validate (m/schema schema) input)
    true
    (do (println "------------------------------------------------------")
        (println "# VALIDATION FAILED:")
        (println (-> (m/schema schema)
                     (m/explain input)
                     me/humanize))
        (println "------------------------------------------------------")
        false)))

(defn route-middleware
  ([request response]
   (response request))
  ([request response schema]
   (if (validate request schema)
     (response request)
     (->html "Something went wrong."))))

(defn parse-float [input]
  (try
    (Float/parseFloat input)
    (catch Exception _
      0)))

(defn format-currency [input currency]
  (try
   (money/format (money/of (keyword currency) input ROUND_HALF_UP))
   (catch Exception _
     0))) 
