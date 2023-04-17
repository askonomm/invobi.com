(defproject invobi "1.0"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojars.askonomm/ruuter "1.3.3"]
                 [com.github.seancorfield/next.jdbc "1.3.847"]
                 [org.xerial/sqlite-jdbc "3.41.0.0"]
                 [com.layerware/hugsql "0.5.3"]
                 [com.layerware/hugsql-core "0.5.3"]
                 [com.layerware/hugsql-adapter-next-jdbc "0.5.3"]
                 [hugsql-adapter-case "0.1.0"]
                 [dev.weavejester/ragtime "0.9.3"]
                 [org.clojure/data.json "2.4.0"]
                 [buddy/buddy-core "1.10.413"]
                 [ring "1.9.6"]
                 [hiccup "1.0.5"]
                 [metosin/malli "0.11.0"]]
  :plugins []
  :aliases {"migrate" ["run" "-m" "api.db/migrate"]
            "rollback" ["run" "-m" "api.db/rollback"]}
  :profiles {:uberjar {:aot :all}}
  :source-paths ["src" "test"]
  :min-lein-version "2.9.0"
  :uberjar-name "invo-api.jar"
  :main invobi.core)
