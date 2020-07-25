(ns clj-trader.exchange
  (:import (user Exchange)
           (clojure.lang PersistentQueue)))

(defrecord MockExchange [symbolInformation]
  Exchange
  (defn getLatestCandle [symbol timeInterval]
    (let [q (agent (deque ()))]
      (produceCandles q symbol timeInterval)
      q)))


(defn produceCandles [que])


(defn queue
  ([] (PersistentQueue/EMPTY))
  ([coll]
   (reduce conj PersistentQueue/EMPTY coll)))

(def que
   (atom (queue)))

(print (swap-vals! que pop))

(Thread/sleep 500)

(swap-vals! conj que "hello")



