(ns invobi.components
  (:require
    [invobi.utils :refer [lang-code->lang-name translate current-year]]))

(defn- header-language-switcher [data]
  [:div.language-switcher
   [:button.button.small.blank {:onclick "toggleLanguageSwitcherOptions()"}
    (lang-code->lang-name (-> data :lang))]
   [:div.options
    (when-not (= (-> data :lang) "en")
      [:a.button.medium.blank.no-border {:href (str "/en" (-> data :current-pure-path))} (lang-code->lang-name "en")])
    (when-not (= (-> data :lang) "et")
      [:a.button.medium.blank.no-border {:href (str "/et" (-> data :current-pure-path))} (lang-code->lang-name "et")])
    (when-not (= (-> data :lang) "es")
      [:a.button.medium.blank.no-border {:href (str "/es" (-> data :current-pure-path))} (lang-code->lang-name "es")])]])

(defn header [data]
  [:div.site-header
   [:div.left-side
    [:div.logo
     [:a {:href "/en"}
      [:img {:src "/img/invo.png" :alt "Invo"}]]]
    (header-language-switcher data)]
   [:div.nav
    [:a.button
     {:href (str "/" (-> data :lang) "/create-invoice")}
     [:i.fa.fa-plus]
     [:span (translate (-> data :lang) :create-invoice)]]]])

(defn footer [_]
  [:div.site-footer
   "© " (current-year) " " [:a {:href "https://repl.ee"} "REPL OÜ."]
   [:a.logo {:href "https://repl.ee" :target "_blank"}
    [:img {:src "/img/logo.png" :alt "REPL logo"}]]])

(defn button [props & children]
  (let [class (cond-> "button"
                      (-> props :class) (str " " (-> props :class))
                      (-> props :size) (str " " (-> props :size))
                      (-> props :type) (str " " (-> props :type))
                      (-> props :disabled?) (str " disabled")
                      (-> props :no-border?) (str " no-border")
                      (-> props :color) (str " color-" (-> props :color)))]
    (if (-> props :href)
      [:a {:href (-> props :href)
           :class class
           :target (-> props :target)
           :style (-> props :style)}
       children]
      [:button (merge {:onclick (-> props :on-click)
                       :class class
                       :style (-> props :style)}
                      (-> props :hx))
       children])))

(defn input [{:keys [class placeholder value style full-width? type hx]}]
  (let [class (cond-> "input"
                      class (str " " class)
                      full-width? (str " full-width"))]
    [:input (merge {:class class
                    :placeholder placeholder
                    :value value
                    :type type
                    :style style}
                   hx)]))

(defn textarea [{:keys [class placeholder value style full-width? hx]}]
  (let [class (cond-> "textarea"
                      class (str " " class)
                      full-width? (str " full-width"))]
    [:textarea (merge {:class class
                       :placeholder placeholder
                       :style style}
                      hx)
     value]))