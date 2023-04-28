(ns invobi.components
  (:require
    [invobi.utils :refer [lang-code->lang-name translate current-year]]))

(defn button 
  "Renders a button or a link depending on the props.
  It's important to note that it also takes in an additional map `:hx`, 
  which is used for HTMx attributes."
  [props & children]
  (let [classname (cond->
                    "button"
                    (-> props :class) (str " " (-> props :class))
                    (-> props :size) (str " " (-> props :size))
                    (-> props :type) (str " " (-> props :type))
                    (-> props :disabled?) (str " disabled")
                    (-> props :no-border?) (str " no-border")
                    (-> props :color) (str " color-" (-> props :color)))]
    (if (-> props :href)
      [:a {:href (-> props :href)
           :class classname
           :target (-> props :target)
           :style (-> props :style)}
       children]
      [:button (merge {:onclick (-> props :on-click)
                       :class classname
                       :style (-> props :style)}
                      (-> props :hx))
       children])))

(defn option 
  "Renders a button or a link depending on the props"
  [opts & children]
  (button
    opts   
    children))

(defn options 
  "Renders drop-down options"
  [& options]
  [:div.options
   options])

(defn- header-language-switcher 
  "Renders a language switcher in the header"
  [data]
  (let [languages ["en" "et" "es"]]
    [:div.language-switcher
     [:button.button.small.blank {:onclick "toggleLanguageSwitcherOptions()"}
      (lang-code->lang-name (-> data :lang))]
     (options
       (for [lang languages]
        (when-not (= (-> data :lang) lang)
          (option
            {:href (str "/" lang (-> data :current-pure-path))
             :size "small"
             :type "blank"
             :no-border? true}
            (lang-code->lang-name lang)))))]))

(defn header 
  "Renders the header"
  [data]
  [:div.site-header
   [:div.left-side
    [:div.logo
     [:a {:href (str "/" (-> data :lang))}
      [:img {:src "/img/invo.png" :alt "Invo"}]]]
    (header-language-switcher data)]
   [:div.nav
    [:a.button
     {:href (str "/" (-> data :lang) "/create-invoice")}
     [:i.fa.fa-plus]
     [:span (translate (-> data :lang) :create-invoice)]]]])

(defn footer 
  "Renders the footer"
  [_]
  [:div.site-footer
   "© " (current-year) " " [:a {:href "https://repl.ee"} "REPL OÜ."]
   [:a.logo {:href "https://repl.ee" :target "_blank"}
    [:img {:src "/img/logo.png" :alt "REPL logo"}]]])

(defn input 
  "Renders an input"
  [{:keys [class placeholder value style full-width? type hx]}]
  (let [class (cond->
                "input"
                class (str " " class)
                full-width? (str " full-width"))]
    [:input (merge {:class class
                    :placeholder placeholder
                    :value value
                    :type type
                    :style style}
                   hx)]))

(defn textarea 
  "Renders a textarea"
  [{:keys [class placeholder value style full-width? hx]}]
  (let [class (cond->
                "textarea"
                class (str " " class)
                full-width? (str " full-width"))]
    [:textarea (merge {:class class
                       :placeholder placeholder
                       :style style}
                      hx)
     value]))

