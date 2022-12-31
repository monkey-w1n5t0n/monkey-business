(ns bb.main
  (:require
   [babashka.process :as p]
   [clojure.java.io :as io]
   [osc-clj.osc :as osc]))

(def PORT 4242)

(println "Starting server...")
(def server (osc/osc-server PORT))
(println "Starting client...")
(def client (osc/osc-client "localhost" PORT))

(println "DONE")

; Register a handler function for the /test OSC address
; The handler takes a message map with the following keys:
;   [:src-host, :src-port, :path, :type-tag, :args]
(osc/osc-handle server "/test" (fn [msg] (println "MSG: " msg)))

(println "DONE2")
; send it some messages
(doseq [val (range 10)]
 (osc/osc-send client "/test" "i" val))

(println "DONE3")


(defn -main [& _args]
  (println "hello world"))


;; (Thread/sleep 1000)

;; ;remove handler
;; (osc/osc-rm-handler server "/test")

;; ; stop listening and deallocate resources
;; (osc/osc-close client)
;; (osc/osc-close server)




















(def ghc;; i-opts
;;   {:err :inherit :shutdown p/destroy-tree})

;; (def ghci (p/process ghci-opts "ghci"))
;; (def ghci-stdin (io/writer (:in ghci)))
;; (def ghci-rdr (io/reader (:out ghci)))

;; ;; Send string
;; (defn send-string [s writer]
;;   (binding [*out* writer]
;;     (print s)))

;; ;; read output line
;; (defn read-line [reader]
;;   (binding [*in* reader]
;;     (read-line)))

;; (defn send-and-receive [s writer reader]
;;   (send-string s writer)
;;   (read-line reader))




;; (send-and-receive "1 + 4" ghci-stdin ghci-rdr)


;; (defn ghci-send [str]
;;   (binding [*out* stdin]
;;     (println str)
;;     (with-open [rdr (io/reader (:out ghci))]
;;       (binding [*in* rdr]
;;         (loop [max 10]
;;           (when-let [line (read-line)]
;;             (println :line line)
;;             (when (pos? max)
;;               (recur (dec max)))))))))



;; (.close stdin)

;; (slurp (:out ghci)) ;; "hello\n"

;; (:exit @ghci) ;; 0

;; (p/alive? ghci) ;; false
