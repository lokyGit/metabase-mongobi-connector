(defproject metabase/mongobi-driver "0.0.1-mongobi"
  :min-lein-version "2.5.0"

  :dependencies
  [[mysql/mysql-connector-java "5.1.41"]]

  :profiles
  {:provided
   {:dependencies
    [[org.clojure/clojure "1.10.0"]
     [metabase-core "1.0.0-SNAPSHOT"]]}

   :uberjar
   {:auto-clean    true
    :aot           :all
    :omit-source   true
    :javac-options ["-target" "1.8", "-source" "1.8"]
    :target-path   "target/%s"
    :uberjar-name  "mongobi.metabase-driver.jar"}})