(ns clj-trader.api.yahoo
  (:require [clj-http.client :as client]
            [cheshire.core :as cc]
            [clojure.string :as str]
            [clj-trader.util :as util]))

;https://rapidapi.com/apidojo/api/yahoo-finance1
(def rapid-yahoo
  {
   :summary "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-summary?region=US&lang=en"
   :charts "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-charts?comparisons=%255EGDAXI%252C%255EFCHI&region=US&lang=en&symbol=HYDR.ME&interval=5m&range=1d"
   })

(defn rapid-get [url]
  (client/get url
              {:headers {:x-rapidapi-host "apidojo-yahoo-finance-v1.p.rapidapi.com"
                         :x-rapidapi-key "fb21ecfe76mshe0c4f5005696ca8p12eef0jsn3d02701c2a14"}
               }))

(def exampleResponse {:cached nil,
                      :request-time 1636,
                      :repeatable? false,
                      :protocol-version {:name "HTTP", :major 1, :minor 1},
                      :streaming? true,
                      :chunked? true,
                      :reason-phrase "OK",
                      :headers {"Access-Control-Allow-Headers" "ver",
                                "Server" "RapidAPI-1.1.24",
                                "X-RapidAPI-Region" "AWS - us-east-1",
                                "X-RateLimit-requests-Remaining" "498",
                                "Content-Type" "application/json",
                                "Access-Control-Allow-Origin" "*",
                                "Connection" "Close",
                                "transfer-encoding" "chunked",
                                "Access-Control-Allow-Methods" "GET, POST",
                                "Date" "Tue, 18 Aug 2020 01:57:00 GMT",
                                "X-RateLimit-requests-Limit" "500",
                                "Access-Control-Allow-Credentials" "true",
                                "X-RapidAPI-Version" "1.1.24"},
                      :orig-content-encoding nil,
                      :status 200,
                      :length -1,
                      :body "{\"marketSummaryResponse\":{\"result\":[{\"fullExchangeName\":\"CME\",\"symbol\":\"ES=F\",\"gmtOffSetMilliseconds\":-14400000,\"headSymbolAsString\":\"ES=F\",\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715218,\"fmt\":\"9:46PM EDT\"},\"regularMarketChangePercent\":{\"raw\":0.05177898,\"fmt\":\"0.05%\"},\"quoteType\":\"FUTURE\",\"headSymbol\":true,\"contractSymbol\":false,\"tradeable\":false,\"regularMarketPreviousClose\":{\"raw\":3379.75,\"fmt\":\"3,379.75\"},\"exchangeTimezoneName\":\"America/New_York\",\"regularMarketChange\":{\"raw\":1.75,\"fmt\":\"1.75\"},\"exchangeDataDelayedBy\":10,\"firstTradeDateMilliseconds\":969249600000,\"exchangeTimezoneShortName\":\"EDT\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":3381.5,\"fmt\":\"3,381.50\"},\"market\":\"us24_market\",\"sourceInterval\":10,\"exchange\":\"CME\",\"shortName\":\"S&P Futures\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"CBOT\",\"symbol\":\"YM=F\",\"gmtOffSetMilliseconds\":-14400000,\"headSymbolAsString\":\"YM=F\",\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715210,\"fmt\":\"9:46PM EDT\"},\"regularMarketChangePercent\":{\"raw\":0.01080108,\"fmt\":\"0.01%\"},\"quoteType\":\"FUTURE\",\"headSymbol\":true,\"contractSymbol\":false,\"tradeable\":false,\"regularMarketPreviousClose\":{\"raw\":27775.0,\"fmt\":\"27,775.00\"},\"exchangeTimezoneName\":\"America/New_York\",\"regularMarketChange\":{\"raw\":3.0,\"fmt\":\"3.00\"},\"exchangeDataDelayedBy\":10,\"firstTradeDateMilliseconds\":1017982800000,\"exchangeTimezoneShortName\":\"EDT\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":27778.0,\"fmt\":\"27,778.00\"},\"market\":\"us24_market\",\"sourceInterval\":10,\"exchange\":\"CBT\",\"shortName\":\"Dow Futures\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"CME\",\"symbol\":\"NQ=F\",\"gmtOffSetMilliseconds\":-14400000,\"headSymbolAsString\":\"NQ=F\",\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715199,\"fmt\":\"9:46PM EDT\"},\"regularMarketChangePercent\":{\"raw\":0.022149866,\"fmt\":\"0.02%\"},\"quoteType\":\"FUTURE\",\"headSymbol\":true,\"contractSymbol\":false,\"tradeable\":false,\"regularMarketPreviousClose\":{\"raw\":11286.75,\"fmt\":\"11,286.75\"},\"exchangeTimezoneName\":\"America/New_York\",\"regularMarketChange\":{\"raw\":2.5,\"fmt\":\"2.50\"},\"exchangeDataDelayedBy\":10,\"firstTradeDateMilliseconds\":969249600000,\"exchangeTimezoneShortName\":\"EDT\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":11289.25,\"fmt\":\"11,289.25\"},\"market\":\"us24_market\",\"sourceInterval\":10,\"exchange\":\"CME\",\"shortName\":\"Nasdaq Futures\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"CME\",\"symbol\":\"RTY=F\",\"gmtOffSetMilliseconds\":-14400000,\"headSymbolAsString\":\"RTY=F\",\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715215,\"fmt\":\"9:46PM EDT\"},\"regularMarketChangePercent\":{\"raw\":0.08839096,\"fmt\":\"0.09%\"},\"quoteType\":\"FUTURE\",\"headSymbol\":true,\"contractSymbol\":false,\"tradeable\":false,\"regularMarketPreviousClose\":{\"raw\":1583.9,\"fmt\":\"1,583.90\"},\"exchangeTimezoneName\":\"America/New_York\",\"regularMarketChange\":{\"raw\":1.4000244,\"fmt\":\"1.40\"},\"exchangeDataDelayedBy\":10,\"firstTradeDateMilliseconds\":1499659200000,\"exchangeTimezoneShortName\":\"EDT\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":1585.3,\"fmt\":\"1,585.30\"},\"market\":\"us24_market\",\"sourceInterval\":10,\"exchange\":\"CME\",\"shortName\":\"Russell 2000 Futures\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"NY Mercantile\",\"symbol\":\"CL=F\",\"gmtOffSetMilliseconds\":-14400000,\"headSymbolAsString\":\"CL=F\",\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715124,\"fmt\":\"9:45PM EDT\"},\"regularMarketChangePercent\":{\"raw\":-0.326415,\"fmt\":\"-0.33%\"},\"quoteType\":\"FUTURE\",\"headSymbol\":true,\"contractSymbol\":false,\"tradeable\":false,\"regularMarketPreviousClose\":{\"raw\":42.89,\"fmt\":\"42.89\"},\"exchangeTimezoneName\":\"America/New_York\",\"regularMarketChange\":{\"raw\":-0.13999939,\"fmt\":\"-0.14\"},\"exchangeDataDelayedBy\":30,\"firstTradeDateMilliseconds\":967003200000,\"exchangeTimezoneShortName\":\"EDT\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":42.75,\"fmt\":\"42.75\"},\"market\":\"us24_market\",\"sourceInterval\":30,\"exchange\":\"NYM\",\"shortName\":\"Crude Oil\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"COMEX\",\"symbol\":\"GC=F\",\"gmtOffSetMilliseconds\":-14400000,\"headSymbolAsString\":\"GC=F\",\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715214,\"fmt\":\"9:46PM EDT\"},\"regularMarketChangePercent\":{\"raw\":-0.0050020306,\"fmt\":\"-0.01%\"},\"quoteType\":\"FUTURE\",\"headSymbol\":true,\"contractSymbol\":false,\"tradeable\":false,\"regularMarketPreviousClose\":{\"raw\":1998.7,\"fmt\":\"1,998.70\"},\"exchangeTimezoneName\":\"America/New_York\",\"regularMarketChange\":{\"raw\":-0.099975586,\"fmt\":\"-0.10\"},\"exchangeDataDelayedBy\":30,\"firstTradeDateMilliseconds\":967608000000,\"exchangeTimezoneShortName\":\"EDT\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":1998.6,\"fmt\":\"1,998.60\"},\"market\":\"us24_market\",\"sourceInterval\":15,\"exchange\":\"CMX\",\"shortName\":\"Gold\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"COMEX\",\"symbol\":\"SI=F\",\"gmtOffSetMilliseconds\":-14400000,\"headSymbolAsString\":\"SI=F\",\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715213,\"fmt\":\"9:46PM EDT\"},\"regularMarketChangePercent\":{\"raw\":0.8602338,\"fmt\":\"0.86%\"},\"quoteType\":\"FUTURE\",\"headSymbol\":true,\"contractSymbol\":false,\"tradeable\":false,\"regularMarketPreviousClose\":{\"raw\":27.667,\"fmt\":\"27.67\"},\"exchangeTimezoneName\":\"America/New_York\",\"regularMarketChange\":{\"raw\":0.23800087,\"fmt\":\"0.24\"},\"exchangeDataDelayedBy\":30,\"firstTradeDateMilliseconds\":967608000000,\"exchangeTimezoneShortName\":\"EDT\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":27.905,\"fmt\":\"27.91\"},\"market\":\"us24_market\",\"sourceInterval\":15,\"exchange\":\"CMX\",\"shortName\":\"Silver\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"CCY\",\"symbol\":\"EURUSD=X\",\"gmtOffSetMilliseconds\":3600000,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715805,\"fmt\":\"2:56AM BST\"},\"regularMarketChangePercent\":{\"raw\":0.11893281,\"fmt\":\"0.12%\"},\"quoteType\":\"CURRENCY\",\"tradeable\":false,\"currency\":\"USD\",\"regularMarketPreviousClose\":{\"raw\":1.1879307,\"fmt\":\"1.1879\"},\"exchangeTimezoneName\":\"Europe/London\",\"regularMarketChange\":{\"raw\":0.0014127493,\"fmt\":\"0.0014\"},\"exchangeDataDelayedBy\":0,\"firstTradeDateMilliseconds\":1070236800000,\"exchangeTimezoneShortName\":\"BST\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":1.1893435,\"fmt\":\"1.1893\"},\"market\":\"ccy_market\",\"quoteSourceName\":\"Delayed Quote\",\"priceHint\":4,\"sourceInterval\":15,\"exchange\":\"CCY\",\"shortName\":\"EUR/USD\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"NYBOT\",\"exchangeTimezoneName\":\"America/New_York\",\"symbol\":\"^TNX\",\"regularMarketChange\":{\"raw\":-0.026000023,\"fmt\":\"-0.0260\"},\"gmtOffSetMilliseconds\":-14400000,\"firstTradeDateMilliseconds\":-252356400000,\"exchangeDataDelayedBy\":30,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597690790,\"fmt\":\"2:59PM EDT\"},\"exchangeTimezoneShortName\":\"EDT\",\"regularMarketChangePercent\":{\"raw\":-3.66714,\"fmt\":\"-3.67%\"},\"quoteType\":\"INDEX\",\"regularMarketPrice\":{\"raw\":0.68299997,\"fmt\":\"0.6830\"},\"marketState\":\"REGULAR\",\"market\":\"us24_market\",\"priceHint\":4,\"tradeable\":false,\"sourceInterval\":30,\"exchange\":\"NYB\",\"shortName\":\"10-Yr Bond\",\"region\":\"US\",\"triggerable\":false,\"regularMarketPreviousClose\":{\"raw\":0.709,\"fmt\":\"0.7090\"},\"longName\":\"Treasury Yield 10 Years\"},{\"fullExchangeName\":\"Chicago Options\",\"exchangeTimezoneName\":\"America/New_York\",\"symbol\":\"^VIX\",\"regularMarketChange\":{\"raw\":-0.69999886,\"fmt\":\"-0.70\"},\"gmtOffSetMilliseconds\":-14400000,\"firstTradeDateMilliseconds\":631290600000,\"exchangeDataDelayedBy\":20,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597695290,\"fmt\":\"4:14PM EDT\"},\"exchangeTimezoneShortName\":\"EDT\",\"regularMarketChangePercent\":{\"raw\":-3.1745982,\"fmt\":\"-3.17%\"},\"quoteType\":\"INDEX\",\"regularMarketPrice\":{\"raw\":21.35,\"fmt\":\"21.35\"},\"marketState\":\"POSTPOST\",\"market\":\"us_market\",\"priceHint\":2,\"tradeable\":false,\"sourceInterval\":15,\"exchange\":\"WCB\",\"shortName\":\"Vix\",\"region\":\"US\",\"triggerable\":false,\"regularMarketPreviousClose\":{\"raw\":22.05,\"fmt\":\"22.05\"}},{\"fullExchangeName\":\"CCY\",\"symbol\":\"GBPUSD=X\",\"gmtOffSetMilliseconds\":3600000,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715805,\"fmt\":\"2:56AM BST\"},\"regularMarketChangePercent\":{\"raw\":0.19301349,\"fmt\":\"0.19%\"},\"quoteType\":\"CURRENCY\",\"tradeable\":false,\"currency\":\"USD\",\"regularMarketPreviousClose\":{\"raw\":1.3104614,\"fmt\":\"1.3105\"},\"exchangeTimezoneName\":\"Europe/London\",\"regularMarketChange\":{\"raw\":0.0025293827,\"fmt\":\"0.0025\"},\"exchangeDataDelayedBy\":0,\"firstTradeDateMilliseconds\":1070236800000,\"exchangeTimezoneShortName\":\"BST\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":1.3129908,\"fmt\":\"1.3130\"},\"market\":\"ccy_market\",\"quoteSourceName\":\"Delayed Quote\",\"priceHint\":4,\"sourceInterval\":15,\"exchange\":\"CCY\",\"shortName\":\"GBP/USD\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"CCY\",\"symbol\":\"JPY=X\",\"gmtOffSetMilliseconds\":3600000,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715819,\"fmt\":\"2:56AM BST\"},\"regularMarketChangePercent\":{\"raw\":-0.29332867,\"fmt\":\"-0.29%\"},\"quoteType\":\"CURRENCY\",\"tradeable\":false,\"currency\":\"JPY\",\"regularMarketPreviousClose\":{\"raw\":106.026,\"fmt\":\"106.0260\"},\"exchangeTimezoneName\":\"Europe/London\",\"regularMarketChange\":{\"raw\":-0.31100464,\"fmt\":\"-0.3110\"},\"exchangeDataDelayedBy\":0,\"firstTradeDateMilliseconds\":846633600000,\"exchangeTimezoneShortName\":\"BST\",\"marketState\":\"REGULAR\",\"regularMarketPrice\":{\"raw\":105.715,\"fmt\":\"105.7150\"},\"market\":\"ccy_market\",\"quoteSourceName\":\"Delayed Quote\",\"priceHint\":4,\"sourceInterval\":15,\"exchange\":\"CCY\",\"shortName\":\"USD/JPY\",\"region\":\"US\",\"triggerable\":false},{\"fullExchangeName\":\"CCC\",\"exchangeTimezoneName\":\"Europe/London\",\"symbol\":\"BTC-USD\",\"regularMarketChange\":{\"raw\":19.735352,\"fmt\":\"19.74\"},\"gmtOffSetMilliseconds\":3600000,\"firstTradeDateMilliseconds\":1410908400000,\"exchangeDataDelayedBy\":0,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597715746,\"fmt\":\"2:55AM BST\"},\"exchangeTimezoneShortName\":\"BST\",\"regularMarketChangePercent\":{\"raw\":0.16084039,\"fmt\":\"0.16%\"},\"quoteType\":\"CRYPTOCURRENCY\",\"regularMarketPrice\":{\"raw\":12289.881,\"fmt\":\"12,289.88\"},\"marketState\":\"REGULAR\",\"market\":\"ccc_market\",\"quoteSourceName\":\"CryptoCompare\",\"tradeable\":true,\"sourceInterval\":15,\"exchange\":\"CCC\",\"region\":\"US\",\"triggerable\":false,\"regularMarketPreviousClose\":{\"raw\":12270.1455,\"fmt\":\"12,270.15\"}},{\"fullExchangeName\":\"Nasdaq GIDS\",\"exchangeTimezoneName\":\"America/New_York\",\"symbol\":\"^CMC200\",\"regularMarketChange\":{\"raw\":0.0,\"fmt\":\"0.00\"},\"gmtOffSetMilliseconds\":-14400000,\"firstTradeDateMilliseconds\":1546266600000,\"exchangeDataDelayedBy\":0,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1594647950,\"fmt\":\"9:45AM EDT\"},\"exchangeTimezoneShortName\":\"EDT\",\"regularMarketChangePercent\":{\"raw\":0.0,\"fmt\":\"0.00%\"},\"quoteType\":\"INDEX\",\"regularMarketPrice\":{\"raw\":156.7543,\"fmt\":\"156.75\"},\"marketState\":\"POSTPOST\",\"market\":\"us_market\",\"quoteSourceName\":\"Delayed Quote\",\"priceHint\":2,\"tradeable\":false,\"sourceInterval\":15,\"exchange\":\"NIM\",\"shortName\":\"CMC Crypto 200\",\"region\":\"US\",\"triggerable\":false,\"regularMarketPreviousClose\":{\"raw\":156.754,\"fmt\":\"156.75\"}},{\"fullExchangeName\":\"FTSE Index\",\"exchangeTimezoneName\":\"Europe/London\",\"symbol\":\"^FTSE\",\"regularMarketChange\":{\"raw\":37.399902,\"fmt\":\"37.40\"},\"gmtOffSetMilliseconds\":3600000,\"firstTradeDateMilliseconds\":441964800000,\"exchangeDataDelayedBy\":15,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597678530,\"fmt\":\"4:35PM BST\"},\"exchangeTimezoneShortName\":\"BST\",\"regularMarketChangePercent\":{\"raw\":0.6141159,\"fmt\":\"0.61%\"},\"quoteType\":\"INDEX\",\"regularMarketPrice\":{\"raw\":6127.44,\"fmt\":\"6,127.44\"},\"marketState\":\"PREPRE\",\"market\":\"gb_market\",\"priceHint\":2,\"tradeable\":false,\"sourceInterval\":15,\"exchange\":\"FGI\",\"shortName\":\"FTSE 100\",\"region\":\"US\",\"triggerable\":false,\"regularMarketPreviousClose\":{\"raw\":6090.04,\"fmt\":\"6,090.04\"}},{\"fullExchangeName\":\"Osaka\",\"exchangeTimezoneName\":\"Asia/Tokyo\",\"symbol\":\"^N225\",\"regularMarketChange\":{\"raw\":-123.21094,\"fmt\":\"-123.21\"},\"gmtOffSetMilliseconds\":32400000,\"firstTradeDateMilliseconds\":-157420800000,\"exchangeDataDelayedBy\":0,\"language\":\"en-US\",\"regularMarketTime\":{\"raw\":1597714615,\"fmt\":\"10:36AM JST\"},\"exchangeTimezoneShortName\":\"JST\",\"regularMarketChangePercent\":{\"raw\":-0.5334557,\"fmt\":\"-0.53%\"},\"quoteType\":\"INDEX\",\"regularMarketPrice\":{\"raw\":22973.54,\"fmt\":\"22,973.54\"},\"marketState\":\"REGULAR\",\"market\":\"jp_market\",\"priceHint\":2,\"tradeable\":false,\"sourceInterval\":20,\"exchange\":\"OSA\",\"shortName\":\"Nikkei 225\",\"region\":\"US\",\"triggerable\":false,\"regularMarketPreviousClose\":{\"raw\":23096.75,\"fmt\":\"23,096.75\"}}],\"error\":null}}",
                      :trace-redirects []})

(def exampleChartsResponse {:cached nil,
                       :request-time 2404,
                       :repeatable? false,
                       :protocol-version {:name "HTTP", :major 1, :minor 1},
                       :streaming? true,
                       :chunked? false,
                       :reason-phrase "OK",
                       :headers {"Access-Control-Allow-Headers" "ver",
                                 "Server" "RapidAPI-1.1.24",
                                 "X-RapidAPI-Region" "AWS - us-east-1",
                                 "X-RateLimit-requests-Remaining" "496",
                                 "Content-Type" "application/json",
                                 "Access-Control-Allow-Origin" "*",
                                 "Content-Length" "18399",
                                 "Connection" "Close",
                                 "Access-Control-Allow-Methods" "GET, POST",
                                 "Date" "Tue, 18 Aug 2020 03:27:21 GMT",
                                 "X-RateLimit-requests-Limit" "500",
                                 "Access-Control-Allow-Credentials" "true",
                                 "X-RapidAPI-Version" "1.1.24"},
                       :orig-content-encoding nil,
                       :status 200,
                       :length 18399,
                       :body "{\"chart\":{\"result\":[{\"meta\":{\"currency\":\"RUB\",\"symbol\":\"HYDR.ME\",\"exchangeName\":\"MCX\",\"instrumentType\":\"EQUITY\",\"firstTradeDate\":1267597800,\"regularMarketTime\":1597698613,\"gmtoffset\":10800,\"timezone\":\"MSK\",\"exchangeTimezoneName\":\"Europe/Moscow\",\"regularMarketPrice\":0.7471,\"chartPreviousClose\":0.7532,\"previousClose\":0.7532,\"scale\":4,\"priceHint\":4,\"currentTradingPeriod\":{\"pre\":{\"timezone\":\"MSK\",\"start\":1597732200,\"end\":1597732200,\"gmtoffset\":10800},\"regular\":{\"timezone\":\"MSK\",\"start\":1597732200,\"end\":1597766400,\"gmtoffset\":10800},\"post\":{\"timezone\":\"MSK\",\"start\":1597766400,\"end\":1597766400,\"gmtoffset\":10800}},\"tradingPeriods\":[[{\"timezone\":\"MSK\",\"start\":1597645800,\"end\":1597680000,\"gmtoffset\":10800}]],\"dataGranularity\":\"5m\",\"range\":\"1d\",\"validRanges\":[\"6mo\",\"2y\",\"1y\",\"ytd\",\"5d\",\"5y\",\"10y\",\"1d\",\"1mo\",\"3mo\",\"max\"]},\"timestamp\":[1597647300,1597647600,1597647900,1597648200,1597648500,1597648800,1597649100,1597649400,1597649700,1597650000,1597650300,1597650600,1597650900,1597651200,1597651500,1597651800,1597652100,1597652400,1597652700,1597653000,1597653300,1597653600,1597653900,1597654200,1597654500,1597654800,1597655100,1597655400,1597655700,1597656000,1597656300,1597656600,1597656900,1597657200,1597657500,1597657800,1597658100,1597658400,1597658700,1597659000,1597659300,1597659600,1597659900,1597660200,1597660500,1597660800,1597661100,1597661400,1597661700,1597662000,1597662300,1597662600,1597662900,1597663200,1597663500,1597663800,1597664100,1597664400,1597664700,1597665000,1597665300,1597665600,1597665900,1597666200,1597666500,1597666800,1597667100,1597667400,1597667700,1597668000,1597668300,1597668600,1597668900,1597669200,1597669500,1597669800,1597670100,1597670400,1597670700,1597671000,1597671300,1597671600,1597671900,1597672200,1597672500,1597672800,1597673100,1597673400,1597673700,1597674000,1597674300,1597674600,1597674900,1597675200,1597675500,1597675800,1597676100,1597676400,1597676700,1597677000,1597677300,1597677600,1597677900,1597678200,1597678500,1597678800,1597679100,1597679400,1597679700],\"comparisons\":[{\"symbol\":\"^FCHI\",\"previousClose\":4962.93,\"gmtoffset\":7200,\"high\":[4986.96,4995.55,4979.26,4960.85,4950.82,4967.19,4985.33,4977.73,4968.71,4973.33,4975.5,4982.42,4979.12,4965.93,4966.3,4964.94,4961.88,4961.62,4965.99,4965.87,4971.92,4972.07,4970.91,4968.77,4970.05,4974.08,4975.37,4975.17,4979.84,4978.33,4974.85,4972.84,4969.23,4967.94,4963.61,4964.16,4961.93,4961.97,4962.15,4962.39,4962.54,4965.74,4967.75,4968.72,4968.94,4966.89,4966.07,4969.56,4971.21,4966.99,4967.67,4967.28,4967.06,4970.02,4970.04,4967.89,4971.45,4970.69,4970.4,4968.54,4969.42,4970.67,4971.32,4971.56,4972.86,4972.31,4970.41,4968.93,4972.22,4972.07,4976.92,4976.23,4971.62,4974.43,4977.9,4975.68,4977.28,4976.2,4974.95,4975.95,4970.78,4969.71,4971.55,4971.56,4971.03,4970.16,4966.04,4967.79,4970.86,4972.5,4974.22,4970.59,4974.65,4974.37,4976.61,4977.43,4975.84,4972.09,4973.55,4975.58,4978.64,4980.58],\"low\":[4972.59,4977.07,4959.54,4948.63,4937.72,4952.56,4968.5,4964.26,4959.94,4965.78,4971.73,4974.05,4962.02,4961.61,4962.65,4959.36,4957.59,4954.23,4957.84,4963.66,4956.8,4967.79,4964.1,4961.25,4966.13,4970.21,4971.24,4972.55,4976.23,4973.79,4972.03,4967.44,4964.64,4962.8,4961.05,4959.91,4959.61,4958.89,4961.27,4960.66,4959.91,4960.83,4965.06,4967.16,4966.91,4965.76,4962.26,4964.59,4965.82,4963.37,4965.01,4964.26,4964.26,4967.06,4966.56,4966.88,4967.9,4969.43,4967.94,4967.58,4967.73,4968.92,4970.23,4968.73,4970.46,4970.82,4969.12,4967.29,4968.67,4970.8,4971.68,4967.16,4966.69,4971.35,4974.22,4974.36,4975.19,4974.33,4971.07,4966.64,4968.3,4966.07,4969.17,4968.28,4968.66,4965.4,4964.11,4965.01,4966.6,4970.47,4969.17,4968.67,4967.51,4973.24,4973.8,4975.49,4970.92,4971.36,4971.92,4972.58,4974.72,4977.71],\"chartPreviousClose\":4962.93,\"close\":[4986.96,4977.07,4961.56,4951.25,4950.82,4967.19,4977.65,4964.26,4966.72,4973.33,4974.15,4978.62,4962.34,4963.91,4964.88,4961.09,4961.72,4957.42,4965.49,4965.19,4971.92,4970.86,4964.5,4964.26,4970.05,4973.58,4974.51,4974.87,4978.23,4974.45,4973.21,4967.88,4964.64,4962.8,4961.85,4959.91,4959.77,4961.33,4962.02,4962.39,4961.04,4965.74,4967.65,4967.25,4967.29,4966.19,4964.53,4969.0,4966.65,4966.99,4965.27,4964.42,4967.06,4968.0,4967.12,4967.7,4971.01,4970.36,4968.81,4967.66,4969.42,4970.4,4970.59,4971.54,4970.92,4971.27,4969.12,4968.88,4971.71,4972.07,4976.05,4967.16,4971.34,4974.43,4975.77,4975.17,4975.28,4975.13,4974.13,4969.62,4968.41,4969.71,4970.89,4968.73,4970.26,4966.01,4964.6,4967.07,4970.86,4971.36,4969.79,4968.67,4974.33,4973.87,4976.3,4975.87,4971.68,4972.08,4972.79,4975.08,4977.89,4977.84],\"open\":[4972.59,4986.39,4977.76,4960.85,4950.01,4952.56,4968.5,4977.73,4964.78,4965.92,4974.29,4974.05,4977.98,4963.13,4963.41,4964.46,4961.2,4961.62,4957.84,4965.49,4965.54,4972.07,4970.91,4963.31,4967.12,4970.21,4973.67,4973.47,4976.51,4978.11,4974.7,4972.84,4968.07,4964.46,4962.69,4961.94,4960.15,4959.73,4961.36,4962.13,4962.3,4960.83,4965.71,4967.62,4967.38,4966.44,4966.07,4964.59,4971.21,4965.93,4967.46,4965.36,4964.28,4967.06,4967.98,4967.01,4967.9,4970.52,4970.4,4968.54,4967.73,4969.46,4970.26,4970.38,4971.69,4971.17,4970.15,4968.06,4968.67,4971.77,4972.04,4975.87,4966.69,4971.35,4974.22,4975.68,4975.34,4975.4,4974.95,4974.66,4969.79,4968.55,4969.17,4971.01,4969.79,4970.16,4965.64,4965.36,4966.9,4971.21,4971.53,4969.71,4968.63,4974.07,4973.8,4976.66,4975.41,4971.63,4971.92,4972.58,4975.26,4977.71]},{\"symbol\":\"^GDAXI\",\"previousClose\":12901.34,\"gmtoffset\":7200,\"high\":[null,12942.37,12945.44,12923.39,12894.36,12877.88,12915.16,12960.84,12942.88,12929.54,12932.62,12933.07,12943.17,12937.08,12906.22,12916.85,12914.7,12912.51,12910.3,12918.2,12917.55,12929.84,12928.41,12925.87,12922.14,12928.05,12936.92,12937.79,12937.2,12945.83,12943.03,12935.33,12929.28,12923.94,12922.7,12911.14,12914.97,12914.29,12913.3,12915.53,12912.53,12912.82,12920.43,12925.62,12925.44,12926.9,12927.58,12926.33,12934.84,12938.16,12933.82,12933.89,12932.25,12930.93,12936.47,12935.34,12932.13,12939.95,12937.86,12933.87,12931.1,12933.19,12936.17,12934.58,12938.11,12939.27,12940.04,12933.14,12930.01,12936.72,12941.95,12953.95,12951.4,12945.31,12948.71,12958.84,12952.62,12954.9,12951.17,12946.9,12951.1,12941.01,12932.18,12934.81,12934.46,12929.87,12924.83,12915.66,12915.03,12921.89,12925.57,12929.16,12924.7,12926.72,12926.92,12933.61,12934.05,12928.27,12921.42,12924.03,12930.56,12940.37,12943.47,12942.25,12941.58,12920.66,12920.66,null,null],\"low\":[null,12922.53,12912.94,12877.16,12862.89,12847.85,12877.03,12913.69,12915.08,12906.48,12916.72,12924.96,12928.38,12891.95,12894.15,12901.22,12897.4,12897.03,12887.54,12899.64,12908.56,12891.17,12919.45,12907.47,12903.25,12918.49,12924.72,12930.83,12928.21,12939.85,12933.21,12926.46,12917.91,12915.3,12909.8,12906.92,12905.09,12907.67,12905.78,12911.85,12909.86,12903.73,12906.97,12918.51,12923.14,12922.71,12923.46,12918.63,12924.13,12926.19,12922.59,12926.83,12923.38,12923.1,12930.91,12925.05,12928.24,12931.13,12933.06,12928.9,12928.22,12928.42,12930.59,12931.52,12929.86,12932.9,12931.51,12928.61,12924.64,12928.65,12934.34,12940.68,12934.81,12934.56,12945.25,12947.85,12948.25,12948.21,12944.52,12936.84,12930.03,12930.48,12922.07,12928.81,12924.19,12922.62,12913.98,12904.77,12904.57,12911.69,12920.35,12918.66,12915.86,12909.27,12922.7,12924.75,12927.43,12918.83,12915.14,12918.86,12918.07,12929.41,12936.98,12941.58,12920.66,12920.66,12920.66,null,null],\"chartPreviousClose\":12901.34,\"close\":[null,12929.73,12917.61,12886.78,12870.65,12877.88,12914.62,12942.88,12915.19,12918.0,12930.16,12929.24,12935.51,12896.46,12903.89,12913.39,12908.96,12910.35,12899.41,12916.58,12912.99,12927.84,12923.31,12907.47,12921.86,12925.89,12934.35,12930.83,12937.2,12941.65,12935.27,12929.5,12919.33,12915.3,12909.8,12910.62,12911.27,12908.95,12912.83,12911.94,12911.65,12906.97,12919.68,12925.13,12923.46,12926.06,12926.16,12924.34,12933.08,12930.15,12933.62,12928.06,12923.52,12930.93,12932.72,12930.73,12931.24,12937.86,12933.87,12931.12,12928.66,12932.99,12932.68,12933.5,12937.74,12934.7,12931.86,12928.61,12928.79,12936.1,12941.43,12950.8,12934.81,12945.25,12948.17,12952.42,12948.25,12949.04,12947.09,12945.98,12938.32,12930.85,12929.67,12932.06,12927.77,12924.78,12915.09,12905.89,12912.37,12920.98,12924.31,12920.51,12915.86,12925.53,12924.75,12933.61,12927.71,12919.04,12920.95,12918.86,12930.07,12937.15,12942.64,12941.58,12920.66,12920.66,12920.66,null,null],\"open\":[null,12924.88,12929.73,12916.62,12887.02,12870.45,12877.65,12914.62,12942.88,12915.14,12916.81,12929.95,12929.24,12935.38,12896.93,12903.89,12913.39,12908.96,12910.3,12899.91,12916.71,12913.04,12927.81,12923.28,12907.35,12921.86,12925.94,12934.35,12930.83,12942.16,12941.68,12935.27,12928.66,12919.33,12915.28,12909.8,12910.68,12911.27,12908.92,12912.77,12911.94,12911.65,12906.97,12919.68,12925.13,12923.6,12926.06,12926.16,12924.34,12936.79,12930.12,12933.49,12928.06,12923.52,12930.93,12932.72,12930.73,12931.24,12937.86,12933.87,12931.03,12928.66,12932.99,12932.67,12933.5,12937.67,12934.7,12932.19,12928.61,12928.79,12936.18,12941.43,12950.8,12934.81,12945.25,12948.17,12952.62,12948.25,12949.04,12946.9,12945.98,12938.6,12930.96,12929.48,12932.06,12928.21,12924.7,12915.09,12906.22,12912.37,12921.77,12924.31,12920.6,12915.81,12925.44,12924.75,12933.61,12927.3,12915.76,12920.95,12918.86,12930.07,12937.19,12942.25,12941.58,12920.66,12920.66,null,null]}],\"indicators\":{\"quote\":[{\"open\":[0.754800021648407,0.7545999884605408,0.7548999786376953,0.7531999945640564,0.753600001335144,0.7542999982833862,0.7538999915122986,0.7531999945640564,0.7527999877929688,0.7534000277519226,0.753600001335144,0.7534999847412109,0.7534999847412109,0.7544999718666077,0.7537000179290771,0.7540000081062317,0.7544999718666077,0.754800021648407,0.7537999749183655,0.753600001335144,0.7540000081062317,0.7544000148773193,0.7544999718666077,0.7540000081062317,0.7537000179290771,0.7552000284194946,0.7552000284194946,0.7534999847412109,0.7544000148773193,0.7540000081062317,0.7541000247001648,0.7544999718666077,0.754800021648407,0.7547000050544739,0.7542999982833862,0.753600001335144,0.7534000277519226,0.7513999938964844,0.7519000172615051,0.7516999840736389,0.7512000203132629,0.7523999810218811,0.7516999840736389,0.7520999908447266,0.7512000203132629,0.7522000074386597,0.7526999711990356,0.751800000667572,0.7511000037193298,0.751800000667572,0.751800000667572,0.7519000172615051,0.7522000074386597,0.7516000270843506,0.7508000135421753,0.7509999871253967,0.7495999932289124,0.7493000030517578,0.7493000030517578,0.7493000030517578,0.7495999932289124,0.7493000030517578,0.7487999796867371,0.7491999864578247,0.7473000288009644,0.7476999759674072,0.7476000189781189,0.7470999956130981,0.7455000281333923,0.7465999722480774,0.7473000288009644,0.7470999956130981,0.7445999979972839,0.7439000010490417,0.7451000213623047,0.7440000176429749,0.7434999942779541,0.7437999844551086,0.7448999881744385,0.7457000017166138,0.7457000017166138,0.7462999820709229,0.7452999949455261,0.7459999918937683,0.7468000054359436,0.7459999918937683,0.7444000244140625,0.7480000257492065,0.7473000288009644,0.7468000054359436,0.7477999925613403,0.7468000054359436,0.7459999918937683,0.746999979019165,0.7469000220298767,0.7469000220298767,0.7465999722480774,0.7469000220298767,0.7466999888420105,0.7462999820709229,0.7462999820709229,0.7468000054359436,0.7462999820709229,0.7462999820709229,0.7455000281333923,null,0.7470999956130981,null,null],\"high\":[0.754800021648407,0.7570000290870667,0.755299985408783,0.7541000247001648,0.7551000118255615,0.7548999786376953,0.7544999718666077,0.7540000081062317,0.7537999749183655,0.753600001335144,0.7537000179290771,0.7537000179290771,0.7548999786376953,0.7547000050544739,0.7540000081062317,0.754800021648407,0.7548999786376953,0.755299985408783,0.7548999786376953,0.7547000050544739,0.7544999718666077,0.7547000050544739,0.7545999884605408,0.755299985408783,0.755299985408783,0.7555000185966492,0.7554000020027161,0.7548999786376953,0.7549999952316284,0.7544999718666077,0.754800021648407,0.7549999952316284,0.7552000284194946,0.754800021648407,0.7544999718666077,0.7538999915122986,0.7534999847412109,0.7523999810218811,0.7520999908447266,0.7522000074386597,0.7524999976158142,0.7523999810218811,0.7519999742507935,0.7522000074386597,0.7527999877929688,0.7533000111579895,0.7526999711990356,0.7523000240325928,0.7519999742507935,0.7519999742507935,0.7523000240325928,0.7524999976158142,0.7524999976158142,0.7519999742507935,0.7512999773025513,0.7515000104904175,0.7495999932289124,0.7494999766349792,0.7495999932289124,0.7495999932289124,0.7495999932289124,0.7495999932289124,0.7495999932289124,0.7495999932289124,0.7480000257492065,0.7480000257492065,0.7480000257492065,0.7472000122070312,0.746999979019165,0.7473000288009644,0.7476000189781189,0.7473999857902527,0.7450000047683716,0.7465999722480774,0.7451000213623047,0.7441999912261963,0.7445999979972839,0.7448999881744385,0.7457000017166138,0.7457000017166138,0.746399998664856,0.7468000054359436,0.7459999918937683,0.746999979019165,0.7468000054359436,0.7472000122070312,0.748199999332428,0.748199999332428,0.7473000288009644,0.7477999925613403,0.7477999925613403,0.7468000054359436,0.7477999925613403,0.7477999925613403,0.7469000220298767,0.7473999857902527,0.7473999857902527,0.746999979019165,0.7466999888420105,0.7469000220298767,0.7465999722480774,0.7470999956130981,0.7469000220298767,0.7472000122070312,0.7473999857902527,null,0.7470999956130981,null,null],\"low\":[0.754800021648407,0.7534000277519226,0.7523999810218811,0.7524999976158142,0.7534999847412109,0.7531999945640564,0.7531999945640564,0.7522000074386597,0.7527999877929688,0.7527999877929688,0.7527999877929688,0.7520999908447266,0.7530999779701233,0.7531999945640564,0.753000020980835,0.753600001335144,0.7537000179290771,0.7537000179290771,0.7533000111579895,0.753600001335144,0.7537000179290771,0.7537999749183655,0.7538999915122986,0.7537000179290771,0.753600001335144,0.7545999884605408,0.7529000043869019,0.7530999779701233,0.7540000081062317,0.7540000081062317,0.7541000247001648,0.7541000247001648,0.7538999915122986,0.7537000179290771,0.753000020980835,0.7530999779701233,0.7509999871253967,0.7513999938964844,0.7494999766349792,0.7512000203132629,0.7511000037193298,0.7508000135421753,0.7509999871253967,0.7513999938964844,0.7511000037193298,0.7519999742507935,0.7515000104904175,0.7508000135421753,0.7509999871253967,0.7511000037193298,0.7512000203132629,0.7519000172615051,0.7516999840736389,0.7505999803543091,0.7495999932289124,0.7490000128746033,0.7491999864578247,0.7484999895095825,0.7487999796867371,0.7484999895095825,0.7488999962806702,0.7486000061035156,0.7487999796867371,0.746399998664856,0.7466999888420105,0.7470999956130981,0.7470999956130981,0.7450000047683716,0.745199978351593,0.7465000152587891,0.7470999956130981,0.7444000244140625,0.7433000206947327,0.7434999942779541,0.7432000041007996,0.7426999807357788,0.7434999942779541,0.743399977684021,0.7433000206947327,0.7440999746322632,0.7443000078201294,0.7452999949455261,0.7450000047683716,0.7457000017166138,0.7457000017166138,0.7441999912261963,0.7440000176429749,0.7462999820709229,0.7459999918937683,0.7468000054359436,0.7461000084877014,0.7455000281333923,0.7457000017166138,0.7462999820709229,0.7459999918937683,0.7462999820709229,0.7465999722480774,0.7462999820709229,0.7455999851226807,0.7458000183105469,0.7458000183105469,0.7457000017166138,0.7458999752998352,0.745199978351593,0.7452999949455261,null,0.7470999956130981,null,null],\"close\":[0.754800021648407,0.7547000050544739,0.7534999847412109,0.7534999847412109,0.7541999816894531,0.7534999847412109,0.7533000111579895,0.7527999877929688,0.753600001335144,0.7531999945640564,0.7534999847412109,0.7534999847412109,0.7538999915122986,0.7534999847412109,0.7540000081062317,0.754800021648407,0.7548999786376953,0.7537999749183655,0.753600001335144,0.7540000081062317,0.7540000081062317,0.7538999915122986,0.7540000081062317,0.7537000179290771,0.7552000284194946,0.7551000118255615,0.7534999847412109,0.7544000148773193,0.7541000247001648,0.7540000081062317,0.7541000247001648,0.7548999786376953,0.7544999718666077,0.7542999982833862,0.753000020980835,0.7534999847412109,0.7513999938964844,0.7519000172615051,0.7516999840736389,0.7512000203132629,0.7519999742507935,0.7516999840736389,0.751800000667572,0.7513999938964844,0.7520999908447266,0.7527999877929688,0.751800000667572,0.7511000037193298,0.7516999840736389,0.751800000667572,0.7519000172615051,0.7524999976158142,0.751800000667572,0.7509999871253967,0.7509999871253967,0.7493000030517578,0.7494999766349792,0.7494000196456909,0.7490000128746033,0.7495999932289124,0.7490000128746033,0.7486000061035156,0.7495999932289124,0.7475000023841858,0.7477999925613403,0.7476000189781189,0.7470999956130981,0.7455000281333923,0.7465999722480774,0.7472000122070312,0.7470999956130981,0.7444000244140625,0.7437000274658203,0.745199978351593,0.7440000176429749,0.7439000010490417,0.7437999844551086,0.7448999881744385,0.7457000017166138,0.7457000017166138,0.7462000250816345,0.7452999949455261,0.7459999918937683,0.7468000054359436,0.7462999820709229,0.7448999881744385,0.7480999827384949,0.746999979019165,0.7468000054359436,0.7473000288009644,0.7468000054359436,0.7459999918937683,0.7470999956130981,0.7469000220298767,0.7466999888420105,0.7468000054359436,0.7469000220298767,0.7465000152587891,0.7462999820709229,0.7459999918937683,0.7459999918937683,0.7462999820709229,0.746399998664856,0.7458999752998352,0.7470999956130981,null,0.7470999956130981,null,null],\"volume\":[0,12702000,26059000,15490000,5260000,6243000,3540000,11330000,1924000,4954000,3369000,6981000,5905000,3838000,1315000,2928000,2450000,9857000,9544000,1432000,1965000,1989000,548000,12873000,5997000,2910000,7500000,3131000,3720000,2074000,1037000,1211000,8126000,570000,1439000,1699000,30972000,2263000,23940000,775000,2310000,6068000,3291000,1022000,2981000,3425000,920000,5479000,496000,1150000,1199000,2171000,1208000,3872000,24469000,23310000,5023000,15378000,5830000,9944000,1753000,10050000,627000,48425000,8882000,6261000,4563000,29659000,5287000,2772000,2084000,24370000,9431000,7579000,9036000,15203000,4138000,2328000,17026000,7857000,17347000,1625000,2027000,2796000,1942000,10400000,33665000,3440000,4071000,8148000,13454000,2528000,3629000,1821000,7669000,1007000,567000,1002000,8916000,3184000,1632000,4057000,1234000,3516000,6777000,null,270000,null,null]}]}}],\"error\":null}}",
                       :trace-redirects []})

(defn getAllKeys [key result]
  (map #(get % key) result))

(def marketSumResult
  (get-in (cc/parse-string (:body exampleResponse)) ["marketSummaryResponse" "result"]))

(def charts
  (get-in (cc/parse-string (:body exampleChartsResponse)) ["chart" "result" "meta"]))

(get-in exampleChartsResponse ["chart" "result"])

(getAllKeys "shortName" marketSumResult)


(filter
  (fn [map] (= "Nasdaq Futures" (get map "shortName")))
        marketSumResult)


;(rapid-get (:charts rapid-yahoo))
;(rapid-get (:summary rapid-yahoo))

(spit (str/join (list util/project-dir "\\resources\\examples\\MarketSum.txt"))
      (cc/generate-string marketSumResult {:pretty true}))

(spit (str/join (list util/project-dir "\\resources\\examples\\Chart.txt"))
      (cc/generate-string charts {:pretty true}))