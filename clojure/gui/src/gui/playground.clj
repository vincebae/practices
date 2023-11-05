(ns gui.playground
  (:gen-class)
  (:require [cljfx.api :as fx])
  (:import (javafx.application Platform)))


(def *state (atom {:title "Clojure FX Playground", :slider-value 50}))


(defn title-input
  [{:keys [title]}]
  {:fx/type :text-field,
   :text title,
   :on-text-changed {:event/type ::title-input-changed}})

(defn slider
  [{:keys [value]}]
  {:fx/type :slider,
   :min 0,
   :max 100,
   :value value,
   :on-value-changed {:event/type ::slider-value-changed}})

(defn slider-label [{:keys [text]}] {:fx/type :label, :text text})


(defn root
  [{:keys [title slider-value]}]
  {:fx/type :stage,
   :showing true,
   :title title,
   :x 2400,
   :y 400,
   :width 400,
   :height 400,
   :on-close-request (fn [_] (Platform/exit)),
   :scene {:fx/type :scene,
           :root {:fx/type :v-box,
                  :alignment :center,
                  :children [{:fx/type :label, :text "Window title input"}
                             {:fx/type title-input, :title title}
                             {:fx/type slider, :value slider-value}
                             {:fx/type slider-label, :text (str slider-value)}
                             ;
                            ]}}})


(defn map-event-handler
  [event]
  (case (:event/type event)
    ::title-input-changed (swap! *state assoc :title (:fx/event event))
    ::slider-value-changed (swap! *state assoc :slider-value (:fx/event event))
    ;
  ))


(def renderer
  (fx/create-renderer :middleware (fx/wrap-map-desc assoc :fx/type root)
                      :opts {:fx.opt/map-event-handler map-event-handler}))


(defn open-window [] (fx/mount-renderer *state renderer))
