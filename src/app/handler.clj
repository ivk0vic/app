(ns app.handler
  (:require [app.views :as views] ; add this require
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes ; replace the generated app-routes with this
  (GET "/"
       []
       (views/home-page))
  (GET "/add-student"
       []
       (views/add-student-page))
  (POST "/add-student"
        {params :params}
        (views/add-student-results-page params))
  (GET "/student/:std-id"
       [std-id]
       (views/student-page std-id))
  (GET "/all-students"
       []
       (views/all-students-page))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
