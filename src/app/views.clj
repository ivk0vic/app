(ns app.views
  (:require [app.db :as db]
            [clojure.string :as str]
            [hiccup.page :as page]
            [ring.util.anti-forgery :as util]))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Students: " title)]
   (page/include-css "/css/styles.css")])

(def header-links
  [:div#header-links
   [:a {:href "/"} "Pocetna"]
   [:a {:href "/add-student"} "Dodaj studenta"]
   [:a {:href "/all-students"} "Pregled svih studenata"]
   ])

(defn home-page
  []
  (page/html5
   (gen-page-head "Pocetna")
   header-links
   [:h1 "Dobrodosli na pocetnu stranu."]
   [:p "Dodajte studente i cuvajte njihove podatke u bazi."]))

(defn add-student-page
  []
  (page/html5
   (gen-page-head "Dodaj studenta")
   header-links
   [:h1 "Dodaj studenta"]
   [:form {:action "/add-student" :method "POST" :autocomplete "off"}
    (util/anti-forgery-field) ; prevents cross-site scripting attacks
    [:p "Unesi index[yyyyxxxx]:" [:input {:type "text" :name "x"}]]
    [:p "Unesi prosek[x.y]:" [:input {:type "text" :name "y"}]]
    [:p [:input {:type "submit" :class "button" :value "Dodaj"}]]]))

(defn add-student-results-page
  [{:keys [x y]}]
  (let [id (db/add-student-to-db x y)]
    (page/html5
     (gen-page-head "Dodat student")
     header-links
     [:h1 "Dodat student"]
     [:p "Dodat student [" x ", " y "] (id: " id ") u bazu. "

      ])))

(defn student-page
  [std-id]
  (let [{x :x y :y} (db/get-xy std-id)]
    (page/html5
     (gen-page-head (str "Student " std-id))
     header-links
     [:h1 "Student"]
     [:p "id: " std-id]
     [:p "x: " x]
     [:p "y: " y])))

(defn all-students-page
  []
  (let [all-stds (db/get-all-students)]
    (page/html5
     (gen-page-head "Svi studenti u bazi")
     header-links
     [:h1 "Svi studenti"]
     [:table
      [:tr [:th "Rbr"] [:th "Index"] [:th "Prosek"]]
      (for [std all-stds]
        [:tr [:td (:id std)] [:td (:x std)] [:td (:y std)]])])))
