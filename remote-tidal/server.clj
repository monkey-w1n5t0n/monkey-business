(ns server
  (:require [org.httpkit.server :as server]
            [clojure.edn :as edn]
            [clojure.pprint :refer [pprint]]))

(def port (edn/read-string
           (nth *command-line-args* 0)))

(defn handler [request]
  (let [message (slurp (:body request))]
    (println message)))

(defn start-server [port]
  (server/run-server handler {:port port}))

(start-server port)

(read-line)
