(ns metabase.driver.mongobi
    "MongoDB BI Connector driver. Builds off of the MySQL/MariaDB driver."
    (:require [metabase.driver.mysql]
              [metabase
                [driver :as driver]
                [util :as u]]
              [metabase.driver.sql-jdbc
                [common :as sql-jdbc.common]
                [connection :as sql-jdbc.conn]])
    )

(driver/register! :mongobi, :parent :mysql)

;;; +----------------------------------------------------------------------------------------------------------------+
;;; |                                         metabase.driver.sql-jdbc impls                                         |
;;; +----------------------------------------------------------------------------------------------------------------+

(defmethod sql-jdbc.conn/connection-details->spec :mongobi
    [_ {:keys [host port db], :as opts}]
    ( -> (merge
     {:classname                     "com.mysql.jdbc.Driver"
      :subprotocol                   "mysql"
      :subname                       (str "//" host ":" port "/" db)
      :useLocalTransactionState      true}
     (dissoc opts :host :port :db))
     (sql-jdbc.common/handle-additional-options opts)))
