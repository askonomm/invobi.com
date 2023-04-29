-- :name update-nr-sql :! :n
UPDATE invoices
SET nr = :value
WHERE uuid = :id;

-- :name update-from-name-sql :! :n
UPDATE invoices
SET from_name = :value
WHERE uuid = :id;

-- :name get-from-fields-sql :! :one
SELECT from_fields
FROM invoices
WHERE uuid = :id;

-- :name update-from-fields-sql :! :n
UPDATE invoices
SET from_fields = :value
WHERE uuid = :id;

-- :name update-to-name-sql :! :n
UPDATE invoices
SET to_name = :value
WHERE uuid = :id;

-- :name get-to-fields-sql :! :one
SELECT to_fields
FROM invoices
WHERE uuid = :id;

-- :name update-to-fields-sql :! :n
UPDATE invoices
SET to_fields = :value
WHERE uuid = :id;

-- :name update-date-issued-sql :! :n
UPDATE invoices
SET date_issued = :value
WHERE uuid = :id;

-- :name update-due-date-sql :! :n
UPDATE invoices
SET due_date = :value
WHERE uuid = :id;

-- :name get-currency-sql :! :one
SELECT currency
FROM invoices
WHERE uuid = :id;

-- :name update-currency-sql :! :n
UPDATE invoices
SET currency = :value
WHERE uuid = :id;

-- :name get-items-sql :! :one
SELECT items
FROM invoices
WHERE uuid = :id;

-- :name update-items-sql :! :n
UPDATE invoices
SET items = :value
WHERE uuid = :id;

-- :name update-qty-type-sql :! :n
UPDATE invoices
SET qty_type = :value
WHERE uuid = :id;
