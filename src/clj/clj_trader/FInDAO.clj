(ns clj-trader.FInDAO
  (:require [cheshire.core :as cc]))

(def files
  {:charts "C:\\Users\\Mike-AMD\\IdeaProjects\\clj-trader\\resources\\examples\\Chart.txt"
   :market-sum "C:\\Users\\Mike-AMD\\IdeaProjects\\clj-trader\\resources\\examples\\MarketSum.txt"})



(defn format-chart [json-list & get-ins]
  (let [ json (first json-list)
        row-count (count (get-in json (first get-ins)))]
    (println row-count)
    (loop [collection (list (conj (map last get-ins)))
          index 0]
      (println collection)
      (if (>= index row-count)
        (reverse collection)
        (recur (conj collection
                 (conj
                    (map #(nth (get-in json %) index) get-ins))
                      )
                  (inc index))))))

(defn read-chart-data []
  (format-chart
    (cc/parse-string (slurp (:charts files)))
    ["timestamp"] ["comparisons" "high"] ["comparisons" "low"]))

(read-chart-data)

(def simple-data 
  {:h [1,2,3]
   :l [4,5,6]})

(format-chart simple-data [:h] [:l])

(conj (map last [[:h] [:l]]))