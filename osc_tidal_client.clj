(ns osc-tidal-client
  (:require
   [babashka.process :as p]
   [clojure.java.io :as io]))

(def ghci-opts
  {
   ;; :out :append
   ;; :out-file (io/file "/tmp/out.txt")
   :err :inherit
   :shutdown p/destroy-tree
   })

(def ghci (p/process ghci-opts "ghci"))

(def stdin (io/writer (:in ghci)))


(def rdr (io/reader (:out ghci)))

;; Send string
(binding [*out* stdin]
  (println "1 + 3")
  (println "100 + 388")
  )

(binding [*out* stdin]
  (with-open [rdr (io/reader (:out ghci))]
    (binding [*in* rdr]
      (read-line))))

(defn ghci-send [str]
  (binding [*out* stdin]
    (println str)
    (with-open [rdr (io/reader (:out ghci))]
      (binding [*in* rdr]
        (loop [max 10]
          (when-let [line (read-line)]
            (println :line line)
            (when (pos? max)
              (recur (dec max)))))))))



(.close stdin)

(slurp (:out ghci)) ;; "hello\n"

(:exit @ghci) ;; 0

(p/alive? ghci) ;; false
