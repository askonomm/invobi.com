(ns invobi.utils.encryption
  (:require
    [buddy.core.bytes :as bytes-util]
    [buddy.core.codecs :as codecs]
    [buddy.core.crypto :as crypto]
    [buddy.core.hash :as hash-util]
    [buddy.core.nonce :as nonce]))

(defn encrypt 
  "Encrypts the given value with the given key."
  [v k]
  (let [v (or v "")
        iv (nonce/random-bytes 16)
        encryption-key (hash-util/sha256 k)
        text-bytes (codecs/to-bytes v)
        encrypted-text (crypto/encrypt text-bytes encryption-key iv
                                       {:alg :aes128-cbc-hmac-sha256})]
    (-> (bytes-util/concat iv encrypted-text)
        codecs/bytes->hex)))

(defn decrypt 
  "Decrypts the given value with the given key."
  [v k]
  (when-not (nil? v)
    (let [[iv-part text-part] (split-at 16 (codecs/hex->bytes v))
          iv (byte-array iv-part)
          encrypted-bytes (byte-array text-part)
          encryption-key (hash-util/sha256 k)
          text-bytes (crypto/decrypt encrypted-bytes encryption-key iv
                                     {:alg :aes128-cbc-hmac-sha256})]
      (codecs/bytes->str text-bytes))))
