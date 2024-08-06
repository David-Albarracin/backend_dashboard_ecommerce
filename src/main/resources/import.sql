INSERT INTO ecommerce.permission (permission_id, name) VALUES(1, 'VIEW_DASHBOARD');
INSERT INTO ecommerce.permission (permission_id, name) VALUES(2, 'EDIT_PRODUCTS');

INSERT INTO ecommerce.country (country_id, name) VALUES(1, 'USA');
INSERT INTO ecommerce.country (country_id, name) VALUES(2, 'Canada');

INSERT INTO ecommerce.region (country_country_id, region_id, name) VALUES(1, 1, 'California');
INSERT INTO ecommerce.region (country_country_id, region_id, name) VALUES(2, 2, 'Ontario');

INSERT INTO ecommerce.city (city_id, region_region_id, name) VALUES(1, 1, 'Los Angeles');
INSERT INTO ecommerce.city (city_id, region_region_id, name) VALUES(2, 2, 'Toronto');