(ns playground.core
  (:gen-class)
  (:require
    [clj-sockets.core :refer [create-socket]]
    [criterium.core :refer [bench]])
  (:import [java.net Socket]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Start")
  (println "Bench localhost")
  (bench (try (create-socket "localhost" 0) (catch Exception e)))
  (println "Bench 127.0.0.1")
  (bench (try (create-socket "127.0.0.1" 0) (catch Exception e)))
  ; (Socket. "localhost" 23)
  ; (Socket. "127.0.0.1" 23)
  (println "End"))
