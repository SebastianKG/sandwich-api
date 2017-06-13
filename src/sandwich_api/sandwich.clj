(ns sandwich-api.sandwich)

(defrecord Sandwich 
  [name
   chicken-count
   ham-count
   lettuce-count 
   mozzarella-count
   bacon-count
   avocado-count])

(defn valid-sandwich? [sandwich-map]
  (let [record-keys (keys (map->Sandwich {}))]
    (every? identity sandwich-map)))
