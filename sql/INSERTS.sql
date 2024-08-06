INSERT INTO ecommerce.role (role_id, name) VALUES(1, 'ADMIN');
INSERT INTO ecommerce.role (role_id, name) VALUES(2, 'USER');

INSERT INTO ecommerce.account (account_no_expired, account_no_locked, credential_no_expired, is_enable, account_id, password, username) VALUES(1, 1, 1, 1, 1, 'password1', 'admin');
INSERT INTO ecommerce.account (account_no_expired, account_no_locked, credential_no_expired, is_enable, account_id, password, username) VALUES(1, 1, 1, 1, 2, 'password2', 'user');

INSERT INTO ecommerce.permission (permission_id, name) VALUES(1, 'VIEW_DASHBOARD');
INSERT INTO ecommerce.permission (permission_id, name) VALUES(2, 'EDIT_PRODUCTS');

INSERT INTO ecommerce.role_permission (permission_id, role_id) VALUES(1, 1);
INSERT INTO ecommerce.role_permission (permission_id, role_id) VALUES(2, 1);
INSERT INTO ecommerce.role_permission (permission_id, role_id) VALUES(1, 2);

INSERT INTO ecommerce.account_role (account_id, role_id) VALUES(1, 1);
INSERT INTO ecommerce.account_role (account_id, role_id) VALUES(2, 2);

INSERT INTO ecommerce.country (country_id, name) VALUES(1, 'USA');
INSERT INTO ecommerce.country (country_id, name) VALUES(2, 'Canada');

INSERT INTO ecommerce.region (country_country_id, region_id, name) VALUES(1, 1, 'California');
INSERT INTO ecommerce.region (country_country_id, region_id, name) VALUES(2, 2, 'Ontario');

INSERT INTO ecommerce.city (city_id, region_region_id, name) VALUES(1, 1, 'Los Angeles');
INSERT INTO ecommerce.city (city_id, region_region_id, name) VALUES(2, 2, 'Toronto');

INSERT INTO ecommerce.customer (account_account_id, created_at, customer_id, updated_at, document_number, employee_id, first_name, first_surname, last_name, last_surname, document_type) VALUES(1, NOW(), 1, NOW(), '123456789', 'E1', 'John', 'Doe', 'Smith', 'Johnson', 'CEDULA_CIUDADANIA');
INSERT INTO ecommerce.customer (account_account_id, created_at, customer_id, updated_at, document_number, employee_id, first_name, first_surname, last_name, last_surname, document_type) VALUES(2, NOW(), 2, NOW(), '987654321', 'E2', 'Jane', 'Doe', 'Smith', 'Johnson', 'PASAPORTE');

INSERT INTO ecommerce.customer_address (city_city_id, customer_address_id, customer_customer_id, address_line1, address_line2) VALUES(1, 1, 1, '123 Main St', 'Apt 4B');
INSERT INTO ecommerce.customer_address (city_city_id, customer_address_id, customer_customer_id, address_line1, address_line2) VALUES(2, 2, 2, '456 Maple Rd', 'Suite 500');

INSERT INTO ecommerce.customer_phone (customer_customer_id, customer_phone_id, phone_number, telephone_type) VALUES(1, 1, '123-456-7890', 'CELULAR');
INSERT INTO ecommerce.customer_phone (customer_customer_id, customer_phone_id, phone_number, telephone_type) VALUES(2, 2, '987-654-3210', 'FIJO');

INSERT INTO ecommerce.order_status (order_status_id, description, is_active, name) VALUES(1, 'Order placed', 'YES', 'PLACED');
INSERT INTO ecommerce.order_status (order_status_id, description, is_active, name) VALUES(2, 'Order shipped', 'YES', 'SHIPPED');

INSERT INTO ecommerce.orders (deliver_date, expected_date, order_date, customer_customer_id, order_id, status_order_status_id, commentary, order_type) VALUES('2024-01-01', '2024-01-05', '2024-01-01', 1, 1, 1, 'First order', 'COMPRA');
INSERT INTO ecommerce.orders (deliver_date, expected_date, order_date, customer_customer_id, order_id, status_order_status_id, commentary, order_type) VALUES('2024-01-10', '2024-01-15', '2024-01-10', 2, 2, 2, 'Second order', 'VENTA');

INSERT INTO ecommerce.supplier (created_at, supplier_id, updated_at, contact_name, email, name) VALUES(NOW(), 1, NOW(), 'Alice', 'alice@example.com', 'Supplier A');
INSERT INTO ecommerce.supplier (created_at, supplier_id, updated_at, contact_name, email, name) VALUES(NOW(), 2, NOW(), 'Bob', 'bob@example.com', 'Supplier B');

INSERT INTO ecommerce.supplier_phone (supplier_phone_id, supplier_supplier_id, phone_number, telephone_type) VALUES(1, 1, '123-456-7890', 'Celular');
INSERT INTO ecommerce.supplier_phone (supplier_phone_id, supplier_supplier_id, phone_number, telephone_type) VALUES(2, 2, '987-654-3210', 'Fijo');

INSERT INTO ecommerce.supplier_address (city_city_id, supplier_address_id, supplier_supplier_id, address_line1, address_line2) VALUES(1, 1, 1, '123 Supplier St', 'Building A');
INSERT INTO ecommerce.supplier_address (city_city_id, supplier_address_id, supplier_supplier_id, address_line1, address_line2) VALUES(2, 2, 2, '456 Supplier Rd', 'Building B');

INSERT INTO ecommerce.office (city_city_id, office_id, phone_number, address_line1, address_line2) VALUES(1, 1, '111-222-3333', '789 Office Blvd', 'Floor 10');
INSERT INTO ecommerce.office (city_city_id, office_id, phone_number, address_line1, address_line2) VALUES(2, 2, '444-555-6666', '321 Office Ave', 'Floor 20');

INSERT INTO ecommerce.office_phone (office_office_id, office_phone_id, phone_number, telephone_type) VALUES(1, 1, '111-222-3333', 'CELULAR');
INSERT INTO ecommerce.office_phone (office_office_id, office_phone_id, phone_number, telephone_type) VALUES(2, 2, '444-555-6666', 'FIJO');

INSERT INTO ecommerce.office_address (city_city_id, office_address_id, office_office_id, address_line1, address_line2) VALUES(1, 1, 1, '789 Office Blvd', 'Floor 10');
INSERT INTO ecommerce.office_address (city_city_id, office_address_id, office_office_id, address_line1, address_line2) VALUES(2, 2, 2, '321 Office Ave', 'Floor 20');

INSERT INTO ecommerce.charge (charge_id, charge_name) VALUES(1, 'Manager');
INSERT INTO ecommerce.charge (charge_id, charge_name) VALUES(2, 'Staff');

INSERT INTO ecommerce.employee (extension, boss_employee_id, charge_charge_id, created_at, employee_id, updated_at, account_id, document_number, first_name, first_surname, office_id, phone_number, second_name, second_surname, document_type) VALUES(123, NULL, 1, NOW(), 1, NOW(), 'A1', '123456789', 'John', 'Doe', 'O1', '111-222-3333', 'Middle', 'Last', 'CEDULA_CIUDADANIA');
INSERT INTO ecommerce.employee (extension, boss_employee_id, charge_charge_id, created_at, employee_id, updated_at, account_id, document_number, first_name, first_surname, office_id, phone_number, second_name, second_surname, document_type) VALUES(456, 1, 2, NOW(), 2, NOW(), 'A2', '987654321', 'Jane', 'Doe', 'O2', '444-555-6666', 'Middle', 'Last', 'PASAPORTE');

INSERT INTO ecommerce.product_gama (product_gama_id, image, description_html, description_text, name) VALUES(1, NULL, NULL, NULL, 'frutas');
INSERT INTO ecommerce.product_gama (product_gama_id, image, description_html, description_text, name) VALUES(2, NULL, NULL, NULL, 'vegetales');

INSERT INTO ecommerce.product (price_buy, price_sale, stock, product_gama_product_gama_id, product_id, code, name, description) VALUES(100, 150, 50, 1, 1, 'P1', 'Apple', 'Red Apple');
INSERT INTO ecommerce.product (price_buy, price_sale, stock, product_gama_product_gama_id, product_id, code, name, description) VALUES(50, 75, 100, 2, 2, 'P2', 'Carrot', 'Orange Carrot');

INSERT INTO ecommerce.order_detail (amount, order_line, total_price, unit_price, customer_order_order_id, order_detail_id, product_product_id) VALUES(2, 1, 300, 150, 1, 1, 1);
INSERT INTO ecommerce.order_detail (amount, order_line, total_price, unit_price, customer_order_order_id, order_detail_id, product_product_id) VALUES(3, 1, 225, 75, 2, 2, 2);

INSERT INTO ecommerce.pay_method (pay_method_id, name, description) VALUES(1, 'Credit Card', 'Payment by credit card');
INSERT INTO ecommerce.pay_method (pay_method_id, name, description) VALUES(2, 'PayPal', 'Payment by PayPal');

INSERT INTO ecommerce.transaction (amount, orders_order_id, pay_method_pay_method_id, transaction_id, transaction_date) VALUES(300, 1, 1, 1, NOW());
INSERT INTO ecommerce.transaction (amount, orders_order_id, pay_method_pay_method_id, transaction_id, transaction_date) VALUES(225, 2, 2, 2, NOW());