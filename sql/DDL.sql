-- -----------------------------------------------------
-- Crear Base de datos
-- -----------------------------------------------------

DROP DATABASE IF EXISTS ecommerce;

CREATE DATABASE ecommerce;

USE ecommerce;


-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  `isActive` TINYINT(1) NOT NULL,
  `rol_id` INT NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `account_id` (`account_id` ASC) ,
  UNIQUE INDEX `email` (`email` ASC),
  CONSTRAINT `fk_rol_id`
    FOREIGN KEY (`rol_id`)
    REFERENCES `role` (`role_id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` INT AUTO_INCREMENT NOT NULL,
  `name` ENUM('ADMIN', 'USER') NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `permission` (
  `permission_id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(50) UNIQUE NOT NULL,
  PRIMARY KEY (`permission_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `role_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_id` INT NOT NULL,
  `permission_id` INT NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`),
  CONSTRAINT `fk_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`role_id`),
  CONSTRAINT `fk_permission_id`
    FOREIGN KEY (`permission_id`)
    REFERENCES `permission` (`permission_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `account_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account_role` (
  `account_id` VARCHAR(255) NOT NULL,
  `role_id` INT AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (`account_id`, `role_id`),
  CONSTRAINT `fk_account_id`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`account_id`),
  CONSTRAINT `fk_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`role_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `city` (
  `city_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `state` VARCHAR(50) NOT NULL,
  `country` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`city_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` VARCHAR(255) NOT NULL,
  `account_id` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `second_name` VARCHAR(255) NOT NULL,
  `first_surname` VARCHAR(255) NOT NULL,
  `second_surname` VARCHAR(255) NOT NULL,
  `document_number` VARCHAR(255) NOT NULL,
  `document_type` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `employee_id` VARCHAR(255) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `customer_id` (`customer_id` ASC) ,
  UNIQUE INDEX `account_id` (`account_id` ASC) ,
  INDEX `fk_customer_city1_idx` (`city_id` ASC) ,
  CONSTRAINT `fk_customer_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`account_id`),
  CONSTRAINT `fk_customer_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `city` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_status` (
  `order_status_id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `isActive` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`order_status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suppliers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `suppliers` (
  `supplier_id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `contact_name` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `address_line1` VARCHAR(50) NOT NULL,
  `address_line2` VARCHAR(50) NOT NULL,
  `city_id` INT NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`supplier_id`),
  INDEX `fk_supplier_city` (`city_id` ASC) ,
  CONSTRAINT `fk_supplier_city`
    FOREIGN KEY (`city_id`)
    REFERENCES `city` (`city_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATE NULL DEFAULT NULL,
  `expected_date` DATE NULL DEFAULT NULL,
  `deliver_date` DATE NULL DEFAULT NULL,
  `commentary` VARCHAR(45) NULL DEFAULT NULL,
  `status_id` INT NOT NULL,
  `order_type` ENUM('compra', 'venta') NULL,
  `client_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_customer_order_order_status1_idx` (`status_id` ASC) ,
  INDEX `fk_client_id_idx` (`client_id` ASC) ,
  CONSTRAINT `fk_customer_order_order_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `order_status` (`order_status_id`),
  CONSTRAINT `fk_suppliers_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `suppliers` (`supplier_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `office`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `office` (
  `office_id` VARCHAR(10) NOT NULL,
  `address_line1` VARCHAR(50) NOT NULL,
  `address_line2` VARCHAR(50) NOT NULL,
  `city_id` INT NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`office_id`),
  INDEX `fk_office_city` (`city_id` ASC) ,
  CONSTRAINT `fk_office_city`
    FOREIGN KEY (`city_id`)
    REFERENCES `city` (`city_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` VARCHAR(255) NOT NULL,
  `account_id` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `second_name` VARCHAR(255) NOT NULL,
  `first_surname` VARCHAR(255) NOT NULL,
  `second_surname` VARCHAR(255) NOT NULL,
  `document_number` VARCHAR(255) NOT NULL,
  `document_type` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `office_id` VARCHAR(255) NOT NULL,
  `extension` VARCHAR(255) NOT NULL,
  `position` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` DATETIME NOT NULL,
  `boss_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `employee_id` (`employee_id` ASC) ,
  UNIQUE INDEX `account_id` (`account_id` ASC) ,
  INDEX `fk_employee_office` (`office_id` ASC) ,
  INDEX `fk_employee_employee1_idx` (`employee_id` ASC) ,
  CONSTRAINT `fk_employee_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`account_id`),
  CONSTRAINT `fk_employee_office`
    FOREIGN KEY (`office_id`)
    REFERENCES `office` (`office_id`),
  CONSTRAINT `fk_employee_boss`
    FOREIGN KEY (`boss_id`)
    REFERENCES `employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product_gama`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product_gama` (
  `product_gama_id` VARCHAR(50) NOT NULL,
  `description_text` TEXT NULL DEFAULT NULL,
  `description_html` TEXT NULL DEFAULT NULL,
  `image` VARCHAR(450) NULL DEFAULT NULL,
  PRIMARY KEY (`product_gama_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `gama_id` VARCHAR(50) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `stock` TINYINT NULL DEFAULT NULL,
  `price_sale` INT NULL DEFAULT NULL,
  `price_buy` INT NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) ,
  INDEX `fk_product_product_gama_idx` (`gama_id` ASC) ,
  CONSTRAINT `fk_product_product_gama`
    FOREIGN KEY (`gama_id`)
    REFERENCES `product_gama` (`product_gama_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_detail` (
  `amount` TINYINT NULL DEFAULT NULL,
  `unit_price` INT NULL DEFAULT NULL,
  `total_price` INT NULL DEFAULT NULL,
  `order_line` SMALLINT NULL DEFAULT NULL,
  `product_id` INT NOT NULL,
  `customer_order_id` INT NOT NULL,
  INDEX `fk_order_detail_product1_idx` (`product_id` ASC) ,
  INDEX `fk_order_detail_customer_order1_idx` (`customer_order_id` ASC) ,
  CONSTRAINT `fk_order_detail_customer_order1`
    FOREIGN KEY (`customer_order_id`)
    REFERENCES `orders` (`order_id`),
  CONSTRAINT `fk_order_detail_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pay_methods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pay_methods` (
  `pay_method_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`pay_method_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transactions` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(10,2) NOT NULL,
  `transaction_date` DATETIME NOT NULL,
  `pay_method_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `fk_transaction_pay_method` (`pay_method_id` ASC) ,
  INDEX `fk_transactions_orders1_idx` (`order_id` ASC) ,
  CONSTRAINT `fk_transaction_pay_method`
    FOREIGN KEY (`pay_method_id`)
    REFERENCES `pay_methods` (`pay_method_id`),
  CONSTRAINT `fk_transactions_orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

