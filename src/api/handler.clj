(ns api.handler
  (:require [compojure.api.sweet :refer :all]
            [mongerr.core :as db]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(defn store-data
  [request]
  (let [params (:params request)]
    (db/db-insert (:collection (:params request)) (dissoc params :collection))))
  
(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Api"
                    :description "Api data store"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
      :tags ["api"]
      (GET "/store" request (ok (store-data request))))))
