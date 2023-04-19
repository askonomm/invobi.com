-- :name get-invoice-sql :? :one
-- :doc Get invoice by ID
SELECT *
FROM invoices
WHERE uuid = :id

-- :name update-invoice-sql :! :n
-- :doc Update invoice by ID
UPDATE invoices
SET nr = :nr,
    from_name = :from-name,
    to_name = :to-name,
    items = :items,
    due_date = :due-date,
    date_issued = :date-issued,
    qty_type = :qty-type,
    currency = :currency,
    payment_details = :payment-details,
    discount_name = :discount-name,
    discount_percentage = :discount-percentage,
    tax_type = :tax-type,
    tax_name = :tax-name,
    tax_percentage = :tax-percentage
WHERE uuid = :id

-- :name create-invoice-sql :! :n
-- :doc blah
INSERT INTO invoices (uuid, nr, from_name, from_fields,
		      to_name, to_fields, items, due_date,
		      date_issued, qty_type, currency, payment_details, discount_name, discount_percentage,
		      tax_type, tax_name, tax_percentage)
VALUES (:id, :nr, :from-name, :from-fields, :to-name,
	:to-fields, :items, :due-date, :date-issued,
	:qty-type, :currency, :payment-details, :discount-name, :discount-percentage, :tax-type, :tax-name, 
	:tax-percentage)

-- :name delete-invoice-sql :! :n
DELETE FROM invoices WHERE uuid = :id
