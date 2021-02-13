(ns clj-trader.exchange
  (:import
           (clojure.lang PersistentQueue)
           (java.util Date)
           (java.math BigInteger)))

(defrecord MockExchange [symbolInformation]
  Exchange
  (defn getLatestCandle [symbol timeInterval]
    (let [q []]
      (produceCandles q symbol timeInterval)
      q)))


(defn makeCandle [symbol]
  ({
    "symbol" symbol
    "open"     (BigInteger. 10),
    "close"    (BigInteger. 15),
    "openTime" (Date. 2020 05 12 10 30),
    "closeTime" (Date. 2020 05 12 10 35)
    }))

(makeCandle "TLS")



