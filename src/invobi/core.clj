(ns invobi.core
  (:require
    [invobi.db :as db]
    [invobi.routes :refer [routes]]
    [invobi.utils.response :refer [->json]]
    [ring.adapter.jetty :as jetty]
    [ring.middleware.params :refer [wrap-params]]
    [ring.middleware.keyword-params :refer [wrap-keyword-params]]
    [ring.middleware.multipart-params :refer [wrap-multipart-params]]
    [ring.middleware.resource :refer [wrap-resource]]
    [ruuter.core :as ruuter])
  (:gen-class))

(defn- handler [request]
  (if (= (-> request :request-method) :options)
    (->json {:status "ok"})
    (try
      (ruuter/route routes request)
      (catch Exception e
        (println e)
        (->json {:status "not-ok"})))))

(def app
  (-> handler
      wrap-params
      wrap-keyword-params
      wrap-multipart-params
      (wrap-resource "public")))

(defn -main [& _]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "4004"))]
    (println "Listening on" port "...")
    (db/migrate)
    (jetty/run-jetty app {:port port})))
