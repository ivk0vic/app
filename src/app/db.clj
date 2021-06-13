(ns app.db
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec {:dbtype "h2" :dbname "./app"})

(defn add-student-to-db
  [x y]
  (let [results (jdbc/insert! db-spec :students {:x x :y y})]
    (assert (= (count results) 1))
    (first (vals (first results)))))

(defn get-xy
  [std-id]
  (let [results (jdbc/query db-spec
                            ["select x, y from students where Rbr = ?" std-id])]
    (assert (= (count results) 1))
    (first results)))

(defn get-all-students
  []
  (jdbc/query db-spec "select id, x, y from students"))
