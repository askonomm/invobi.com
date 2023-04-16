(ns invobi.routes
  (:require
    [invobi.routes.api.hello :as routes.api.hello]
    [invobi.routes.api.invoice :as routes.api.invoice]
    [invobi.routes.site :as routes.site]))

(def routes
  (concat
    routes.api.hello/routes
    routes.api.invoice/routes
    routes.site/routes))
