(ns: clj-trader.exchange
     (:gen-class))

(defprotocol Exchange
     (defn getLatestCandle [symbol timeInterval])
     (defn getHistoricalCandle [symbol timeInterval])
     (defn getSymbolInformation []))

