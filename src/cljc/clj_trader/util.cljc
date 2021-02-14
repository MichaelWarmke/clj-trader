(ns clj-trader.util)

(def project-dir (System/getProperty "user.dir"))

(defn foo-cljc [x]
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
