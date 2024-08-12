![](https://raw.githubusercontent.com/David-Albarracin/README_MATERIALS/main/spring.png)

# Spring Boot eCommerce API

Este es el proyecto final con temática de comercio electrónico, en el cual se implementan tecnologías de Spring como JPA, Spring Security y controladores para MySQL. La arquitectura utilizada es orientada al dominio.

## Integrantes

- **Liliana Paola Castellanos Pinzón**
- **Sofia Marcela Medina Díaz**
- **Edgar Benjamin David Albarracín Sanabria**

## Requerimientos

1. **Comandos DDL para construir la estructura de la base de datos**

   - ```sql
     /*!40000 DROP DATABASE IF EXISTS `ecommerce`*/;
     
     CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
     
     USE `ecommerce`;
     
     --
     -- Table structure for table `account`
     --
     
     DROP TABLE IF EXISTS `account`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `account` (
       `account_no_expired` bit(1) DEFAULT NULL,
       `account_no_locked` bit(1) DEFAULT NULL,
       `credential_no_expired` bit(1) DEFAULT NULL,
       `is_active` bit(1) DEFAULT NULL,
       `is_enable` bit(1) DEFAULT NULL,
       `account_id` bigint NOT NULL AUTO_INCREMENT,
       `created_at` datetime(6) DEFAULT NULL,
       `updated_at` datetime(6) DEFAULT NULL,
       `password` varchar(255) DEFAULT NULL,
       `username` varchar(255) DEFAULT NULL,
       PRIMARY KEY (`account_id`),
       UNIQUE KEY `UKgex1lmaqpg0ir5g1f5eftyaa1` (`username`)
     ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `account_role`
     --
     
     DROP TABLE IF EXISTS `account_role`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `account_role` (
       `account_id` bigint NOT NULL,
       `role_id` bigint NOT NULL,
       PRIMARY KEY (`account_id`,`role_id`),
       KEY `FKrs2s3m3039h0xt8d5yhwbuyam` (`role_id`),
       CONSTRAINT `FK1f8y4iy71kb1arff79s71j0dh` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
       CONSTRAINT `FKrs2s3m3039h0xt8d5yhwbuyam` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `charge`
     --
     
     DROP TABLE IF EXISTS `charge`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `charge` (
       `charge_id` bigint NOT NULL AUTO_INCREMENT,
       `charge_name` varchar(255) DEFAULT NULL,
       PRIMARY KEY (`charge_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `city`
     --
     
     DROP TABLE IF EXISTS `city`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `city` (
       `city_id` bigint NOT NULL AUTO_INCREMENT,
       `region_id` bigint NOT NULL,
       `name` varchar(255) NOT NULL,
       PRIMARY KEY (`city_id`),
       KEY `FKsi0dkm9kk6dyuedmc0j18t770` (`region_id`),
       CONSTRAINT `FKsi0dkm9kk6dyuedmc0j18t770` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `country`
     --
     
     DROP TABLE IF EXISTS `country`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `country` (
       `country_id` bigint NOT NULL AUTO_INCREMENT,
       `name` varchar(255) NOT NULL,
       PRIMARY KEY (`country_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `customer`
     --
     
     DROP TABLE IF EXISTS `customer`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `customer` (
       `is_active` bit(1) DEFAULT NULL,
       `created_at` datetime(6) DEFAULT NULL,
       `customer_id` bigint NOT NULL AUTO_INCREMENT,
       `employee_id` bigint DEFAULT NULL,
       `updated_at` datetime(6) DEFAULT NULL,
       `document_number` varchar(255) NOT NULL,
       `first_name` varchar(255) NOT NULL,
       `first_surname` varchar(255) NOT NULL,
       `last_name` varchar(255) NOT NULL,
       `last_surname` varchar(255) NOT NULL,
       `document_type` enum('CEDULA_CIUDADANIA','CEDULA_EXTRANJERIA','NIT','PASAPORTE') NOT NULL,
       PRIMARY KEY (`customer_id`),
       KEY `FKiv4yye896nrvevqc0vpmmskmn` (`employee_id`),
       CONSTRAINT `FKiv4yye896nrvevqc0vpmmskmn` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `customer_address`
     --
     
     DROP TABLE IF EXISTS `customer_address`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `customer_address` (
       `city_id` bigint NOT NULL,
       `customer_address_id` bigint NOT NULL AUTO_INCREMENT,
       `customer_id` bigint NOT NULL,
       `address_line1` varchar(50) NOT NULL,
       `address_line2` varchar(50) NOT NULL,
       PRIMARY KEY (`customer_address_id`),
       KEY `FKc031eoihoyqy4w9l6nmukocc9` (`city_id`),
       KEY `FKr9ofa0ydsgbaqmt9leb3v5eii` (`customer_id`),
       CONSTRAINT `FKc031eoihoyqy4w9l6nmukocc9` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`),
       CONSTRAINT `FKr9ofa0ydsgbaqmt9leb3v5eii` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `customer_phone`
     --
     
     DROP TABLE IF EXISTS `customer_phone`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `customer_phone` (
       `customer_id` bigint NOT NULL,
       `customer_phone_id` bigint NOT NULL AUTO_INCREMENT,
       `phone_number` varchar(50) NOT NULL,
       `telephone_type` enum('CELULAR','FIJO') NOT NULL,
       PRIMARY KEY (`customer_phone_id`),
       KEY `FKni7c19gxj6a4t4eu60khpbi9u` (`customer_id`),
       CONSTRAINT `FKni7c19gxj6a4t4eu60khpbi9u` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `employee`
     --
     
     DROP TABLE IF EXISTS `employee`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `employee` (
       `extension` int NOT NULL,
       `is_active` bit(1) DEFAULT NULL,
       `boss_id` bigint DEFAULT NULL,
       `charge_id` bigint NOT NULL,
       `created_at` datetime(6) DEFAULT NULL,
       `employee_id` bigint NOT NULL AUTO_INCREMENT,
       `office_id` bigint NOT NULL,
       `updated_at` datetime(6) DEFAULT NULL,
       `document_number` varchar(255) NOT NULL,
       `first_name` varchar(255) NOT NULL,
       `first_surname` varchar(255) NOT NULL,
       `phone_number` varchar(255) NOT NULL,
       `second_name` varchar(255) NOT NULL,
       `second_surname` varchar(255) NOT NULL,
       `document_type` enum('CEDULA_CIUDADANIA','CEDULA_EXTRANJERIA','NIT','PASAPORTE') NOT NULL,
       PRIMARY KEY (`employee_id`),
       KEY `FKbt4ib0926k7136ncs464vwav2` (`boss_id`),
       KEY `FKlj93fb4hom228bxwb1im1rw56` (`charge_id`),
       KEY `FKjurhambl7fs34cp8i36xpd5yp` (`office_id`),
       CONSTRAINT `FKbt4ib0926k7136ncs464vwav2` FOREIGN KEY (`boss_id`) REFERENCES `employee` (`employee_id`),
       CONSTRAINT `FKjurhambl7fs34cp8i36xpd5yp` FOREIGN KEY (`office_id`) REFERENCES `office` (`office_id`),
       CONSTRAINT `FKlj93fb4hom228bxwb1im1rw56` FOREIGN KEY (`charge_id`) REFERENCES `charge` (`charge_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `office`
     --
     
     DROP TABLE IF EXISTS `office`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `office` (
       `city_id` bigint NOT NULL,
       `office_id` bigint NOT NULL AUTO_INCREMENT,
       `address_line1` varchar(50) NOT NULL,
       `address_line2` varchar(50) NOT NULL,
       PRIMARY KEY (`office_id`),
       KEY `FKkhfcx7e6edowjigcfh69kcqwl` (`city_id`),
       CONSTRAINT `FKkhfcx7e6edowjigcfh69kcqwl` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `office_phone`
     --
     
     DROP TABLE IF EXISTS `office_phone`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `office_phone` (
       `office_id` bigint NOT NULL,
       `office_phone_id` bigint NOT NULL AUTO_INCREMENT,
       `phone_number` varchar(50) NOT NULL,
       `telephone_type` enum('CELULAR','FIJO') NOT NULL,
       PRIMARY KEY (`office_phone_id`),
       KEY `FKkgj6axi2ja33vakmk2eqvto6b` (`office_id`),
       CONSTRAINT `FKkgj6axi2ja33vakmk2eqvto6b` FOREIGN KEY (`office_id`) REFERENCES `office` (`office_id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `order_detail`
     --
     
     DROP TABLE IF EXISTS `order_detail`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `order_detail` (
       `amount` tinyint NOT NULL,
       `order_line` smallint DEFAULT NULL,
       `total_price` int NOT NULL,
       `unit_price` int NOT NULL,
       `order_detail_id` bigint NOT NULL AUTO_INCREMENT,
       `order_id` bigint NOT NULL,
       `product_id` bigint NOT NULL,
       PRIMARY KEY (`order_detail_id`),
       KEY `FKrws2q0si6oyd6il8gqe2aennc` (`order_id`),
       KEY `FKb8bg2bkty0oksa3wiq5mp5qnc` (`product_id`),
       CONSTRAINT `FKb8bg2bkty0oksa3wiq5mp5qnc` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE,
       CONSTRAINT `FKrws2q0si6oyd6il8gqe2aennc` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `order_status`
     --
     
     DROP TABLE IF EXISTS `order_status`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `order_status` (
       `order_status_id` bigint NOT NULL AUTO_INCREMENT,
       `description` varchar(45) DEFAULT NULL,
       `is_active` varchar(45) DEFAULT NULL,
       `name` varchar(45) NOT NULL,
       PRIMARY KEY (`order_status_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `orders`
     --
     
     DROP TABLE IF EXISTS `orders`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `orders` (
       `deliver_date` date DEFAULT NULL,
       `expected_date` date DEFAULT NULL,
       `order_date` date NOT NULL,
       `customer_id` bigint NOT NULL,
       `order_id` bigint NOT NULL AUTO_INCREMENT,
       `order_status_id` bigint NOT NULL,
       `commentary` varchar(45) DEFAULT NULL,
       `order_type` enum('COMPRA','VENTA') NOT NULL,
       PRIMARY KEY (`order_id`),
       KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`),
       KEY `FK2n7p8t83wo7x0lep1q06a6cvy` (`order_status_id`),
       CONSTRAINT `FK2n7p8t83wo7x0lep1q06a6cvy` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`order_status_id`),
       CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `pay_method`
     --
     
     DROP TABLE IF EXISTS `pay_method`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `pay_method` (
       `pay_method_id` bigint NOT NULL AUTO_INCREMENT,
       `name` varchar(50) NOT NULL,
       `description` varchar(255) DEFAULT NULL,
       PRIMARY KEY (`pay_method_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `permission`
     --
     
     DROP TABLE IF EXISTS `permission`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `permission` (
       `permission_id` bigint NOT NULL AUTO_INCREMENT,
       `name` varchar(255) DEFAULT NULL,
       PRIMARY KEY (`permission_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `product`
     --
     
     DROP TABLE IF EXISTS `product`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `product` (
       `price_buy` int DEFAULT NULL,
       `price_sale` int NOT NULL,
       `stock` tinyint NOT NULL,
       `product_gama_id` bigint NOT NULL,
       `product_id` bigint NOT NULL AUTO_INCREMENT,
       `supplier_id` bigint NOT NULL,
       `code` varchar(45) NOT NULL,
       `name` varchar(45) NOT NULL,
       `description` tinytext,
       PRIMARY KEY (`product_id`),
       UNIQUE KEY `UKh3w5r1mx6d0e5c6um32dgyjej` (`code`),
       KEY `FK2kxvbr72tmtscjvyp9yqb12by` (`supplier_id`),
       KEY `FKcixe27j5bm5mc7ofk9xj5fqtd` (`product_gama_id`),
       CONSTRAINT `FK2kxvbr72tmtscjvyp9yqb12by` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`),
       CONSTRAINT `FKcixe27j5bm5mc7ofk9xj5fqtd` FOREIGN KEY (`product_gama_id`) REFERENCES `product_gama` (`product_gama_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `product_gama`
     --
     
     DROP TABLE IF EXISTS `product_gama`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `product_gama` (
       `product_gama_id` bigint NOT NULL AUTO_INCREMENT,
       `image` varchar(450) DEFAULT NULL,
       `name` varchar(255) NOT NULL,
       `description_html` tinytext,
       `description_text` tinytext,
       PRIMARY KEY (`product_gama_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `region`
     --
     
     DROP TABLE IF EXISTS `region`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `region` (
       `country_id` bigint NOT NULL,
       `region_id` bigint NOT NULL AUTO_INCREMENT,
       `name` varchar(50) NOT NULL,
       PRIMARY KEY (`region_id`),
       KEY `FK7vb2cqcnkr9391hfn72louxkq` (`country_id`),
       CONSTRAINT `FK7vb2cqcnkr9391hfn72louxkq` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `role`
     --
     
     DROP TABLE IF EXISTS `role`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `role` (
       `role_id` bigint NOT NULL AUTO_INCREMENT,
       `name` enum('ADMIN','USER') NOT NULL,
       PRIMARY KEY (`role_id`),
       UNIQUE KEY `UK8sewwnpamngi6b1dwaa88askk` (`name`)
     ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `role_permission`
     --
     
     DROP TABLE IF EXISTS `role_permission`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `role_permission` (
       `permission_id` bigint NOT NULL,
       `role_id` bigint NOT NULL,
       PRIMARY KEY (`permission_id`,`role_id`),
       KEY `FKa6jx8n8xkesmjmv6jqug6bg68` (`role_id`),
       CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
       CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `supplier`
     --
     
     DROP TABLE IF EXISTS `supplier`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `supplier` (
       `is_active` bit(1) DEFAULT NULL,
       `created_at` datetime(6) DEFAULT NULL,
       `supplier_id` bigint NOT NULL AUTO_INCREMENT,
       `updated_at` datetime(6) DEFAULT NULL,
       `contact_name` varchar(50) NOT NULL,
       `email` varchar(100) NOT NULL,
       `name` varchar(100) NOT NULL,
       PRIMARY KEY (`supplier_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `supplier_address`
     --
     
     DROP TABLE IF EXISTS `supplier_address`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `supplier_address` (
       `city_id` bigint NOT NULL,
       `supplier_address_id` bigint NOT NULL AUTO_INCREMENT,
       `supplier_id` bigint NOT NULL,
       `address_line1` varchar(50) NOT NULL,
       `address_line2` varchar(50) NOT NULL,
       PRIMARY KEY (`supplier_address_id`),
       KEY `FKak3m439kd87k3x7ot2yb9p3b8` (`city_id`),
       KEY `FKr9dtkjlmnsmxxnd1hl8r8lpi6` (`supplier_id`),
       CONSTRAINT `FKak3m439kd87k3x7ot2yb9p3b8` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`),
       CONSTRAINT `FKr9dtkjlmnsmxxnd1hl8r8lpi6` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `supplier_phone`
     --
     
     DROP TABLE IF EXISTS `supplier_phone`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `supplier_phone` (
       `supplier_id` bigint NOT NULL,
       `supplier_phone_id` bigint NOT NULL AUTO_INCREMENT,
       `phone_number` varchar(50) NOT NULL,
       `telephone_type` enum('Celular','Fijo') NOT NULL,
       PRIMARY KEY (`supplier_phone_id`),
       KEY `FK1f0x81egyt4uenf0nsu1ux078` (`supplier_id`),
       CONSTRAINT `FK1f0x81egyt4uenf0nsu1ux078` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     /*!40101 SET character_set_client = @saved_cs_client */;
     
     --
     -- Table structure for table `transactions`
     --
     
     DROP TABLE IF EXISTS `transactions`;
     /*!40101 SET @saved_cs_client     = @@character_set_client */;
     /*!50503 SET character_set_client = utf8mb4 */;
     CREATE TABLE `transactions` (
       `amount` decimal(10,2) NOT NULL,
       `order_id` bigint NOT NULL,
       `pay_method_id` bigint NOT NULL,
       `transaction_date` datetime(6) NOT NULL,
       `transaction_id` bigint NOT NULL AUTO_INCREMENT,
       PRIMARY KEY (`transaction_id`),
       KEY `FKfyxndk58yiq2vpn0yd4m09kbt` (`order_id`),
       KEY `FK68gvy3fsoercfqmebvvg84osb` (`pay_method_id`),
       CONSTRAINT `FK68gvy3fsoercfqmebvvg84osb` FOREIGN KEY (`pay_method_id`) REFERENCES `pay_method` (`pay_method_id`),
       CONSTRAINT `FKfyxndk58yiq2vpn0yd4m09kbt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     ```
     
     

2. **Diagrama entidad-relación**

   - ![](https://raw.githubusercontent.com/David-Albarracin/backend_dashboard_ecommerce/main/sql/ecommerce-DER.png)

