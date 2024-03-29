
(ns time.loop
    (:require [time.schedule :as schedule]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn reduce-interval
  ; @description
  ; Iterates over the given collection and applies the given function on each item while taking a pause between each of them.
  ;
  ; @param (function) f
  ; @param (collection) coll
  ; @param (integer) interval
  ;
  ; @usage
  ; (defn my-function [%] ...)
  ; (reduce-interval my-function [:a :b :c] 500)
  ; =>
  ; (set-timeout! #(my-function :a)    0)
  ; (set-timeout! #(my-function :b)  500)
  ; (set-timeout! #(my-function :c) 1000)
  ;
  ; @return (*)
  [f coll interval]
  (letfn [(reduce-interval-f [lap item]
                             (schedule/set-timeout! #(f item)
                                                     (* lap interval))
                             (inc lap))]
         (reduce reduce-interval-f 0 coll)))
