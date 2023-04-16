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
    from_company_name = :from-company-name,
    from_company_address = :from-company-address,
    from_company_extra = :from-company-extra,
    to_name = :to-name,
    to_company_name = :to-company-name,
    to_company_address = :to-company-address,
    to_company_extra = :to-company-extra,
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
INSERT INTO invoices (uuid, nr, from_name, from_company_name, from_company_address, from_company_extra,
		      to_name, to_company_name, to_company_address, to_company_extra, items, due_date, 
		      date_issued, qty_type, currency, payment_details, discount_name, discount_percentage,
		      tax_type, tax_name, tax_percentage)
VALUES (:id, :nr, :from-name, :from-company-name, :from-company-address, :from-company-extra, :to-name, 
	:to-company-name, :to-company-address, :to-company-extra, :items, :due-date, :date-issued, 
	:qty-type, :currency, :payment-details, :discount-name, :discount-percentage, :tax-type, :tax-name, 
	:tax-percentage)

-- :name delete-invoice-sql :! :n
DELETE FROM invoices WHERE uuid = :id
