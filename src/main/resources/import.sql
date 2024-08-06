INSERT INTO ecommerce.permission (permission_id, name) VALUES(1, 'VIEW_DASHBOARD');
INSERT INTO ecommerce.permission (permission_id, name) VALUES(2, 'EDIT_PRODUCTS');

INSERT INTO ecommerce.country (country_id, name) VALUES(1, 'USA');
INSERT INTO ecommerce.country (country_id, name) VALUES(2, 'Canada');

INSERT INTO ecommerce.region (country_id, region_id, name) VALUES(1, 1, 'California');
INSERT INTO ecommerce.region (country_id, region_id, name) VALUES(2, 2, 'Ontario');

INSERT INTO ecommerce.city (city_id, region_id, name) VALUES(1, 1, 'Los Angeles');
INSERT INTO ecommerce.city (city_id, region_id, name) VALUES(2, 2, 'Toronto');

INSERT INTO ecommerce.product_gama (product_gama_id, name) VALUES(1, 'Frutas');
INSERT INTO ecommerce.product_gama (product_gama_id, name) VALUES(2, 'Plasticos');

INSERT INTO ecommerce.product (price_buy, price_sale, stock, product_gama_id, product_id, code, name, description) VALUES(1500, 1600, 20, 1, 1, '152', 'Perro', 'R');
INSERT INTO ecommerce.product (price_buy, price_sale, stock, product_gama_id, product_id, code, name, description) VALUES(1500, 1600, 16, 2, 2, '452', 'Pato', 'R');
INSERT INTO ecommerce.product (price_buy, price_sale, stock, product_gama_id, product_id, code, name, description) VALUES(1500, 1600, 15, 2, 3, '445', 'Gato', 'R');