DROP DATABASE IF EXISTS ecommerce_t;
CREATE DATABASE ecommerce_t;
USE ecommerce_t;

CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('ADMIN','USER') NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account` (
  `account_no_expired` bit(1) DEFAULT NULL,
  `account_no_locked` bit(1) DEFAULT NULL,
  `credential_no_expired` bit(1) DEFAULT NULL,
  `is_enable` bit(1) DEFAULT NULL,
  `account_id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UKgex1lmaqpg0ir5g1f5eftyaa1` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permission` (
  `permission_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role_permission` (
  `permission_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `FKa6jx8n8xkesmjmv6jqug6bg68` (`role_id`),
  CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_role` (
  `account_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FKrs2s3m3039h0xt8d5yhwbuyam` (`role_id`),
  CONSTRAINT `FK1f8y4iy71kb1arff79s71j0dh` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `FKrs2s3m3039h0xt8d5yhwbuyam` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `country` (
  `country_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `region` (
  `country_country_id` bigint DEFAULT NULL,
  `region_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`region_id`),
  KEY `FK8nqbmbsyhi42a35pr27mkrd3u` (`country_country_id`),
  CONSTRAINT `FK8nqbmbsyhi42a35pr27mkrd3u` FOREIGN KEY (`country_country_id`) REFERENCES `country` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `city` (
  `city_id` bigint NOT NULL AUTO_INCREMENT,
  `region_region_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`city_id`),
  KEY `FK8s50mk780hveqqhyuwnv362sk` (`region_region_id`),
  CONSTRAINT `FK8s50mk780hveqqhyuwnv362sk` FOREIGN KEY (`region_region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer` (
  `account_account_id` bigint DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) NOT NULL,
  `document_number` varchar(255) NOT NULL,
  `employee_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `first_surname` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `last_surname` varchar(255) NOT NULL,
  `document_type` enum('CEDULA_CIUDADANIA','CEDULA_EXTRANJERIA','NIT','PASAPORTE') NOT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `FK9x7okmi8dvaakw32wqqh208kv` (`account_account_id`),
  CONSTRAINT `FK9x7okmi8dvaakw32wqqh208kv` FOREIGN KEY (`account_account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer_address` (
  `city_city_id` bigint DEFAULT NULL,
  `customer_address_id` bigint NOT NULL AUTO_INCREMENT,
  `customer_customer_id` bigint DEFAULT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  PRIMARY KEY (`customer_address_id`),
  KEY `FKmdyjbt8ma21lwk31gk03tsafr` (`city_city_id`),
  KEY `FKigw0i0l1u44ub00t5xa5va3jo` (`customer_customer_id`),
  CONSTRAINT `FKigw0i0l1u44ub00t5xa5va3jo` FOREIGN KEY (`customer_customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FKmdyjbt8ma21lwk31gk03tsafr` FOREIGN KEY (`city_city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer_phone` (
  `customer_customer_id` bigint DEFAULT NULL,
  `customer_phone_id` bigint NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(50) NOT NULL,
  `telephone_type` enum('CELULAR','FIJO') NOT NULL,
  PRIMARY KEY (`customer_phone_id`),
  KEY `FK6jppim2k2aymglvo05orhcaao` (`customer_customer_id`),
  CONSTRAINT `FK6jppim2k2aymglvo05orhcaao` FOREIGN KEY (`customer_customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_status` (
  `order_status_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `is_active` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
  `deliver_date` date DEFAULT NULL,
  `expected_date` date DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `customer_customer_id` bigint DEFAULT NULL,
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `status_order_status_id` bigint DEFAULT NULL,
  `commentary` varchar(45) DEFAULT NULL,
  `order_type` enum('COMPRA','VENTA') DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKgk0a4775o5scnc1sjrg2w8afa` (`customer_customer_id`),
  KEY `FK3drv0c2497162jv03pcmmasrq` (`status_order_status_id`),
  CONSTRAINT `FK3drv0c2497162jv03pcmmasrq` FOREIGN KEY (`status_order_status_id`) REFERENCES `order_status` (`order_status_id`),
  CONSTRAINT `FKgk0a4775o5scnc1sjrg2w8afa` FOREIGN KEY (`customer_customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `supplier` (
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `supplier_id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `contact_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `supplier_phone` (
  `supplier_phone_id` bigint NOT NULL AUTO_INCREMENT,
  `supplier_supplier_id` bigint DEFAULT NULL,
  `phone_number` varchar(50) NOT NULL,
  `telephone_type` enum('Celular','Fijo') NOT NULL,
  PRIMARY KEY (`supplier_phone_id`),
  KEY `FKrrrtlhyamh8j956sh1ec9lt0b` (`supplier_supplier_id`),
  CONSTRAINT `FKrrrtlhyamh8j956sh1ec9lt0b` FOREIGN KEY (`supplier_supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `supplier_address` (
  `city_city_id` bigint DEFAULT NULL,
  `supplier_address_id` bigint NOT NULL AUTO_INCREMENT,
  `supplier_supplier_id` bigint DEFAULT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  PRIMARY KEY (`supplier_address_id`),
  KEY `FKhea5pw2jiaqhhoojj0ufm0oam` (`city_city_id`),
  KEY `FKhr9r3l231uj7bvhhkcs881kna` (`supplier_supplier_id`),
  CONSTRAINT `FKhea5pw2jiaqhhoojj0ufm0oam` FOREIGN KEY (`city_city_id`) REFERENCES `city` (`city_id`),
  CONSTRAINT `FKhr9r3l231uj7bvhhkcs881kna` FOREIGN KEY (`supplier_supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `office` (
  `city_city_id` bigint DEFAULT NULL,
  `office_id` bigint NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(20) NOT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  PRIMARY KEY (`office_id`),
  KEY `FKrlc012kl5drkrsmv21k8b3gfk` (`city_city_id`),
  CONSTRAINT `FKrlc012kl5drkrsmv21k8b3gfk` FOREIGN KEY (`city_city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `office_phone` (
  `office_office_id` bigint DEFAULT NULL,
  `office_phone_id` bigint NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(50) NOT NULL,
  `telephone_type` enum('CELULAR','FIJO') NOT NULL,
  PRIMARY KEY (`office_phone_id`),
  KEY `FK7ahklbea42or4nlr45ucjjm51` (`office_office_id`),
  CONSTRAINT `FK7ahklbea42or4nlr45ucjjm51` FOREIGN KEY (`office_office_id`) REFERENCES `office` (`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `office_address` (
  `city_city_id` bigint DEFAULT NULL,
  `office_address_id` bigint NOT NULL AUTO_INCREMENT,
  `office_office_id` bigint DEFAULT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  PRIMARY KEY (`office_address_id`),
  KEY `FKip54qn21jw37w3qr53aa0lgjy` (`city_city_id`),
  KEY `FK77ii6ykyqvo0knxmxn9rq0niq` (`office_office_id`),
  CONSTRAINT `FK77ii6ykyqvo0knxmxn9rq0niq` FOREIGN KEY (`office_office_id`) REFERENCES `office` (`office_id`),
  CONSTRAINT `FKip54qn21jw37w3qr53aa0lgjy` FOREIGN KEY (`city_city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `charge` (
  `charge_id` bigint NOT NULL AUTO_INCREMENT,
  `charge_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`charge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employee` (
  `extension` int NOT NULL,
  `boss_employee_id` bigint DEFAULT NULL,
  `charge_charge_id` bigint DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `document_number` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `first_surname` varchar(255) NOT NULL,
  `office_id` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `second_name` varchar(255) NOT NULL,
  `second_surname` varchar(255) NOT NULL,
  `document_type` enum('CEDULA_CIUDADANIA','CEDULA_EXTRANJERIA','NIT','PASAPORTE') NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `UKlsnx7na4u8ohrhoeag7un4wh3` (`account_id`),
  KEY `FKci6ohd0x8v06tp3jc778lfrf6` (`boss_employee_id`),
  KEY `FK7m27b8tuw4nl6nyr81f410f52` (`charge_charge_id`),
  CONSTRAINT `FK7m27b8tuw4nl6nyr81f410f52` FOREIGN KEY (`charge_charge_id`) REFERENCES `charge` (`charge_id`),
  CONSTRAINT `FKci6ohd0x8v06tp3jc778lfrf6` FOREIGN KEY (`boss_employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product_gama` (
  `product_gama_id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(450) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description_html` tinytext,
  `description_text` tinytext,
  PRIMARY KEY (`product_gama_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product` (
  `price_buy` int DEFAULT NULL,
  `price_sale` int DEFAULT NULL,
  `stock` tinyint DEFAULT NULL,
  `product_gama_product_gama_id` bigint DEFAULT NULL,
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` tinytext,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `UKh3w5r1mx6d0e5c6um32dgyjej` (`code`),
  KEY `FKh0ijrxv9rpabm9irajyp3i7ax` (`product_gama_product_gama_id`),
  CONSTRAINT `FKh0ijrxv9rpabm9irajyp3i7ax` FOREIGN KEY (`product_gama_product_gama_id`) REFERENCES `product_gama` (`product_gama_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_detail` (
  `amount` tinyint DEFAULT NULL,
  `order_line` smallint DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `unit_price` int DEFAULT NULL,
  `customer_order_order_id` bigint DEFAULT NULL,
  `order_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `product_product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `FK6j04jrky289y92qgbo0to3yep` (`customer_order_order_id`),
  KEY `FKm7asbwck993r4yi0olqeqxcda` (`product_product_id`),
  CONSTRAINT `FK6j04jrky289y92qgbo0to3yep` FOREIGN KEY (`customer_order_order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKm7asbwck993r4yi0olqeqxcda` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pay_method` (
  `pay_method_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pay_method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transaction` (
  `amount` decimal(10,2) NOT NULL,
  `orders_order_id` bigint DEFAULT NULL,
  `pay_method_pay_method_id` bigint DEFAULT NULL,
  `transaction_date` datetime(6) NOT NULL,
  `transaction_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`transaction_id`),
  KEY `FKah9u0uj81li9mn7ydkuw6w3ka` (`orders_order_id`),
  KEY `FKboxpfb77dkma9ve0v98fhahm8` (`pay_method_pay_method_id`),
  CONSTRAINT `FKah9u0uj81li9mn7ydkuw6w3ka` FOREIGN KEY (`orders_order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKboxpfb77dkma9ve0v98fhahm8` FOREIGN KEY (`pay_method_pay_method_id`) REFERENCES `pay_method` (`pay_method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;