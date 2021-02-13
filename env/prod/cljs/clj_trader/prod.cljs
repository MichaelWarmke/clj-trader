(ns clj-trader.prod
  (:require [clj-trader.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
