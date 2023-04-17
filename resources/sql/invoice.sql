-- :name update-nr-sql :! :n
UPDATE invoices
SET nr = :value
WHERE uuid = :id

-- :name update-from-name-sql :! :n
UPDATE invoices
SET from_name = :value
WHERE uuid = :id

-- :name update-from-company-name-sql :! :n
UPDATE invoices
SET from_company_name = :value
WHERE uuid = :id

-- :name update-from-company-address-sql :! :n
UPDATE invoices
SET from_company_address = :value
WHERE uuid = :id

-- :name update-from-company-extra-sql :! :n
UPDATE invoices
SET from_company_extra = :value
WHERE uuid = :id

-- :name update-from-company-extra-label-sql :! :n
UPDATE invoices
SET from_company_extra_label = :value
WHERE uuid = :id

-- :name update-to-name-sql :! :n
UPDATE invoices
SET to_name = :value
WHERE uuid = :id

-- :name update-to-company-name-sql :! :n
UPDATE invoices
SET to_company_name = :value
WHERE uuid = :id

-- :name update-to-company-address-sql :! :n
UPDATE invoices
SET to_company_address = :value
WHERE uuid = :id

-- :name update-to-company-extra-sql :! :n
UPDATE invoices
SET to_company_extra = :value
WHERE uuid = :id

-- :name update-to-company-extra-label-sql :! :n
UPDATE invoices
SET to_company_extra_label = :value
WHERE uuid = :id

-- :name update-date-issued-sql :! :n
UPDATE invoices
SET date_issued = :value
WHERE uuid = :id

-- :name update-due-date-sql :! :n
UPDATE invoices
SET due_date = :value
WHERE uuid = :id

-- :name update-currency-sql :! :n
UPDATE invoices
SET currency = :value
WHERE uuid = :id