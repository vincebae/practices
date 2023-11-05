(defproject gui "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0",
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"] [cljfx "1.7.24"]]
  :main ^:skip-aot gui.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all,
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
