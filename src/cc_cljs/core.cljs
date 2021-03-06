(ns cc-cljs.core
  (:require [reagent.core :as  r]
            [re-frame.core :as rf]
            [kee-frame.core :as k]
            [cc-cljs.events] 
            [cc-cljs.subs]
            [cc-cljs.db :refer [default-db]]
            [cc-cljs.routes :refer [routes]]
            [cc-cljs.views :refer [register-form]]))

(k/reg-event-fx
 :navigate-to
 (fn [{db :db} [d]]
   {:navigate-to [d]}))

(defn nearby []
  [:h1 "nearby shops"])

(defn preferred []
  [:h1 "preferred shops"])

(defn login []
  [:h1 "login page"])

(defn register []
  [register-form])

(defn not-found []
  [:h1 "Page Not Found :("])

(defn content []
  [k/switch-route (fn [route] (:handler route))
   :nearby [nearby]
   :preferred [preferred]
   :login [login]
   :register [register]
   nil [not-found]])

(defn start! []
  (println "Starting ...")
  (rf/clear-subscription-cache!)
  (k/start! {:initial-db default-db
             :routes routes
             :root-component [content]
             :debug? true}))

(defn stop! []
  (println "Stopping ..."))

#_(start!)
