(ns invobi.routes.api.hello
  (:require
    [invobi.utils.response :refer [->json]]))

(defn- hello [_]
  (->json {:status "ok"}))

(def routes
  [{:path "/api"
    :method :get
    :response hello}])