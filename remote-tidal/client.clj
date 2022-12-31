(ns client
  (:require [org.httpkit.client :as client]
            [clojure.edn :as edn]))


(defn send-msg [url msg]
  (let [response (client/request
                  {:method :post
                   :url url
                   :body "hello 123 baba"})]
    (println (:status response))
    (println (:body response))))

(send-msg "http://127.0.0.1:8081" "hello WORLD")

;; ;; start concurrent requests, get promise, half the waiting time
;; (let [response1 (http-kit/get "http://http-kit.org/")
;;       response2 (http-kit/get "http://clojure.org/")]
;;   ;; Handle responses one-by-one, blocking as necessary
;;   ;; Other keys :headers :body :error :opts
;;   (println "response1's status: " (:status @response1))
;;   (println "response2's status: " (:status @response2)))
