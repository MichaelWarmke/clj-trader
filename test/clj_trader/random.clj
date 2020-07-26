(ns clj-trader.random
  (:require [clojure.test :refer :all])
  (:import (java.util.concurrent LinkedBlockingQueue)
           (clojure.lang PersistentQueue)))

(defn callCandleApi []
  (Thread/sleep 1000)
  "New Candle")

(defn produceCandles []
    (take 10
         (repeat callCandleApi)))

(def que
  (seque (produceCandles)))

(seq que)

(Thread/sleep 5000)

(seq que)
