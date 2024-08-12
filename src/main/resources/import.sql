-- CIUDADES
-- ************************************
INSERT INTO country (name) VALUES  ('Estados Unidos'), ('Canada'), ('Mexico'), ('Brasil');

-- REGIONES
-- ************************************
INSERT INTO region (name, country_id) VALUES ('California', 1), ('New York', 1), ('Ontario', 2), ('British Columbia', 2), ('Ciudad de Mexico', 3), ('Jalisco', 3), ('São Paulo', 4), ('Rio de Janeiro', 4);

-- CIUDADES
-- ************************************
INSERT INTO city (name, region_id) VALUES ('Los Angeles', 1), ('San Francisco', 1), ('New York City', 2), ('Buffalo', 2), ('Toronto', 3), ('Ottawa', 3), ('Vancouver', 4), ('Victoria', 4), ('Ciudad de Mexico', 5), ('Ecatepec', 5), ('Guadalajara', 6), ('Tlaquepaque', 6), ('São Paulo', 7), ('Campinas', 7), ('Rio de Janeiro', 8), ('Niteroi', 8);

-- OFICINAS
-- ************************************
INSERT INTO office (city_id, address_line1, address_line2) VALUES(1, '123 Main St', 'Suite 100'), (2, '456 Market St', 'Apt 202'), (3, '789 Broadway', 'Floor 3'), (4, '101 Elm St', 'Suite 405'), (5, '202 King St', 'Floor 2'), (6, '303 Queen St', 'Apt 106'), (7, '404 Granville St', 'Suite 210'), (8, '505 Government St', 'Floor 5'), (9, '606 Reforma Ave', 'Suite 303'), (10, '707 Independencia Ave', 'Apt 409'), (11, '808 Lopez Cotilla', 'Floor 1'), (12, '909 Juarez Ave', 'Suite 202'), (13, '1010 Avenida Paulista', 'Apt 501'), (14, '1111 Rua de Campinas', 'Suite 302'), (15, '1212 Copacabana Ave', 'Floor 6'), (16, '1313 Rua do Rio', 'Apt 205');

-- CARGOS
-- ************************************
INSERT INTO charge (charge_name) VALUES('CEO'), ('Director de Finanzas'), ('Director de Operaciones'), ('Gerente de Ventas'), ('Contador Senior'), ('Ingeniero de Software'), ('Gerente de Produccion'), ('Analista de Marketing'), ('Especialista en Recursos Humanos'), ('Asistente de Contabilidad');

-- EMPLEADOS
-- ************************************
INSERT INTO employee (extension, boss_id, charge_id, employee_id, office_id, document_number, first_name, first_surname, phone_number, second_name, second_surname, document_type) VALUES(1001, NULL, 1, 1, 1, 'CC1234567890', 'Juan', 'Perez', '555-0101', 'Alejandro', 'Gomez', 'CEDULA_CIUDADANIA'), (1002, 1, 2, 2, 2, 'CC0987654321', 'Maria', 'Garcia', '555-0102', 'Isabel', 'Moreno', 'CEDULA_CIUDADANIA'), (1003, 1, 3, 3, 3, 'CC1122334455', 'Luis', 'Rodriguez', '555-0103', 'Fernando', 'Vega', 'CEDULA_CIUDADANIA'), (1004, 2, 4, 4, 4, 'CC2233445566', 'Ana', 'Hernandez', '555-0104', 'Cristina', 'Paredes', 'CEDULA_CIUDADANIA'), (1005, 3, 5, 5, 5, 'CC3344556677', 'Carlos', 'Martinez', '555-0105', 'Luis', 'Sanchez', 'CEDULA_CIUDADANIA'), (1006, 3, 6, 6, 6, 'CC4455667788', 'Laura', 'Sanchez', '555-0106', 'Nerea', 'Gomez', 'CEDULA_CIUDADANIA'), (1007, 2, 7, 7, 7, 'CC5566778899', 'Javier', 'Ramirez', '555-0107', 'Marcelo', 'Reyes', 'CEDULA_CIUDADANIA'), (1008, 4, 8, 8, 8, 'CC6677889900', 'Isabel', 'Muñoz', '555-0108', 'Paola', 'Vega', 'CEDULA_CIUDADANIA'), (1009, 3, 9, 9, 9, 'CC7788990011', 'Andres', 'Valencia', '555-0109', 'Sergio', 'Torres', 'CEDULA_CIUDADANIA'), (1010, 2, 10, 10, 10, 'CC8899001122', 'Sofia', 'Martinez', '555-0110', 'Felipe', 'Montoya', 'CEDULA_CIUDADANIA'); 

-- GAMAS DE PRODUCTAS
-- ************************************	
INSERT INTO product_gama (image, name, description_html, description_text) VALUES('images/smartphones.jpg', 'Smartphones de ultima Generacion', '<p>Descubre nuestra <strong>gama de smartphones de ultima generacion</strong>, con la tecnologia mas avanzada y diseños innovadores. ¡Perfectos para cualquier usuario!</p>', 'Smartphones de ultima generacion con tecnologia avanzada y diseños innovadores. Ideal para cualquier usuario.'), ('images/flagship_phones.jpg', 'Telefonos Flagship', '<p>Explora los <strong>telefonos flagship</strong> con las mejores especificaciones y el rendimiento mas alto. La opcion premium para los entusiastas de la tecnologia.</p>', 'Telefonos flagship con las mejores especificaciones y rendimiento alto. La opcion premium para entusiastas de la tecnologia.'), ('images/mid_range_phones.jpg', 'Telefonos de Gama Media', '<p>Nuestros <strong>telefonos de gama media</strong> ofrecen una excelente relacion calidad-precio, combinando caracteristicas esenciales con un diseño atractivo.</p>', 'Telefonos de gama media con excelente relacion calidad-precio y diseño atractivo.'), ('images/budget_phones.jpg', 'Telefonos Economicos', '<p>Conoce nuestros <strong>telefonos economicos</strong>, ideales para quienes buscan un dispositivo funcional a un precio accesible.</p>', 'Telefonos economicos ideales para quienes buscan funcionalidad a un precio accesible.'), ('images/accessories.jpg', 'Accesorios para Smartphones', '<p>Completa tu experiencia con nuestra gama de <strong>accesorios para smartphones</strong>, incluyendo fundas, cargadores y mas.</p>', 'Accesorios para smartphones, incluyendo fundas, cargadores y otros complementos.'); 

-- PROOVEDORES
-- ************************************	
INSERT INTO supplier (contact_name, email, name) VALUES('John Doe', 'john.doe@apple.com', 'Apple Inc.'), ('Jane Smith', 'jane.smith@Samsung.com', 'Samsung Electronics'),  ('David Brown', 'david.brown@oneplus.com', 'OnePlus Technology'), ('Emily Davis', 'emily.davis@Sony.com', 'Sony Mobile'),    ('Michael Johnson', 'michael.johnson@Xiaomi.com', 'Xiaomi Corporation'), ('Linda Wilson', 'linda.wilson@Motorola.com', 'Motorola Solutions'),  ('Robert Lee', 'robert.lee@Nokia.com', 'Nokia Corporation'), ('Karen Martinez', 'karen.martinez@Alcatel.com', 'Alcatel-Lucent'),   ('Chris White', 'chris.white@Belkin.com', 'Belkin International'),('Patricia Clark', 'patricia.clark@OtterBox.com', 'OtterBox');

-- CONTACTO DE PROOVEDORES
-- ************************************	
INSERT INTO supplier_phone (supplier_id, phone_number, telephone_type) VALUES(1, '+1-408-555-1234', 'Fijo'), (1, '+1-408-555-5678', 'Celular'), (2, '+1-415-555-2345', 'Fijo'), (2, '+1-415-555-6789', 'Celular'), (3, '+1-212-555-3456', 'Fijo'), (3, '+1-212-555-7890', 'Celular'), (4, '+1-716-555-4567', 'Fijo'), (4, '+1-716-555-8901', 'Celular'), (5, '+1-416-555-5678', 'Fijo'), (5, '+1-416-555-9012', 'Celular'), (6, '+1-613-555-6789', 'Fijo'), (6, '+1-613-555-0123', 'Celular'), (7, '+1-604-555-7890', 'Fijo'), (7, '+1-604-555-1234', 'Celular'), (8, '+1-250-555-8901', 'Fijo'), (8, '+1-250-555-3456', 'Celular'), (9, '+52-55-5555-6789', 'Fijo'), (9, '+52-55-5555-1234', 'Celular'), (10, '+52-55-5555-2345', 'Fijo'), (10, '+52-55-5555-6789', 'Celular');

-- DIRECCION DE PROOVEDORES
-- ************************************	
INSERT INTO supplier_address (city_id, supplier_id, address_line1, address_line2) VALUES(1, 1, '1 Infinite Loop', 'Cupertino, CA 95014'), (2, 1, 'San Francisco Office', '123 Market Street'), (3, 2, 'Samsung Building', '234 Elm Street'), (4, 2, 'Samsung Office', '567 Broadway'), (5, 3, 'OnePlus HQ', '890 King Street'), (6, 3, 'OnePlus Branch', '456 Queen Street'), (7, 4, 'Sony Center', '789 Main Street'), (8, 4, 'Sony Lab', '123 Pine Avenue'), (9, 5, 'Xiaomi HQ', '101 Battery Street'), (10, 5, 'Xiaomi Lab', '202 Calle de la Reforma'), (11, 6, 'Motorola Office', '303 Avenida Juarez'), (12, 6, 'Motorola R&D', '404 Calle Hidalgo'), (13, 7, 'Nokia HQ', '505 Avenida Paulista'), (14, 7, 'Nokia Branch', '606 Rua dos Três Irmãos'), (15, 8, 'Alcatel Office', '707 Avenida Atlântica'), (16, 8, 'Alcatel Lab', '808 Rua São Bento'); 

-- PRODUCTOS
-- ************************************	
INSERT INTO product (price_buy, price_sale, stock, product_gama_id, supplier_id, code, name, description) VALUES(300, 399, 50, 1, 1, 'SP-1001', 'iPhone 14 Pro', 'Smartphone de ultima generacion con pantalla Super Retina XDR y camara de 48 MP.'), (250, 329, 40, 1, 2, 'SP-1002', 'Samsung Galaxy S23', 'Smartphone con pantalla Dynamic AMOLED 2X y camara de 50 MP.'), (600, 799, 20, 2, 3, 'FL-2001', 'OnePlus 11', 'Telefono flagship con procesador Snapdragon 8 Gen 1 y pantalla Fluid AMOLED.'), (550, 699, 25, 2, 4, 'FL-2002', 'Sony Xperia 1 IV', 'Telefono premium con pantalla 4K HDR OLED y camara triple de 12 MP.'), (150, 199, 70, 3, 5, 'MG-3001', 'Xiaomi Redmi Note 11', 'Telefono con pantalla AMOLED de 6.43" y bateria de 5000 mAh.'), (130, 179, 65, 3, 6, 'MG-3002', 'Motorola Moto G Power', 'Telefono economico con bateria de 5000 mAh y pantalla Max Vision de 6.6"'), (80, 99, 50, 4, 7, 'EC-4001', 'Nokia G20', 'Telefono basico con pantalla HD+ y bateria de 5050 mAh.'), (70, 89, 50, 4, 8, 'EC-4002', 'Alcatel 1B', 'Telefono simple con pantalla de 5.5" y almacenamiento de 32 GB.'), (10, 15, 30, 5, 9, 'AC-5001', 'Funda Protectora Universal', 'Funda protectora para smartphones de diferentes tamaños, con diseño elegante.'), (8, 12, 50, 5, 10, 'AC-5002', 'Cargador Rapido USB-C', 'Cargador rapido USB-C compatible con la mayoria de los smartphones modernos.'); 

-- ESTADOS DE PEDIDO
-- ************************************	
INSERT INTO order_status (order_status_id, description, is_active, name) VALUES (1, 'Pedido aun no ha sido procesado', 'Si', 'Pendiente'), (2, 'Pedido ha sido rechazado por alguna razon', 'Si', 'Rechazado'), (3, 'Pedido ha sido entregado al cliente', 'Si', 'Entregado');

-- METODO DE PAGO
-- ************************************	
INSERT INTO pay_method (name, description) VALUES  ('Tarjeta de Credito', 'Pago mediante tarjeta de credito emitida por bancos o entidades financieras.'), ('PayPal', 'Pago en linea a traves de la plataforma de PayPal.'), ('Transferencia Bancaria', 'Pago mediante transferencia directa desde una cuenta bancaria.');

-- CLIENTES
-- ************************************		
INSERT INTO customer (is_active, created_at, customer_id, employee_id, updated_at, document_number, first_name, first_surname, last_name, last_surname, document_type) VALUES(1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 1, 1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC1234567890', 'Juan', 'Perez', 'Martinez', 'Gomez', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 2, 2, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC0987654321', 'Maria', 'Garcia', 'Rodriguez', 'Martinez', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 3, 3, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC1122334455', 'Luis', 'Rodriguez', 'Sanchez', 'Gomez', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 4, 4, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC2233445566', 'Ana', 'Fernandez', 'Hernandez', 'Soto', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 5, 5, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC3344556677', 'Carlos', 'Lopez', 'Jimenez', 'Castro', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 6, 6, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC4455667788', 'Sofia', 'Torres', 'Perez', 'Gomez', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 7, 7, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC5566778899', 'Miguel', 'Gomez', 'Hernandez', 'Vargas', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 8, 8, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC6677889900', 'Laura', 'Castro', 'Hidalgo', 'Moreno', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 9, 9, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC7788990011', 'Roberto', 'Sanchez', 'Lopez', 'Ruiz', 'CEDULA_CIUDADANIA'), (1, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 10, 10, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'), 'CC8899001122', 'Valeria', 'Vargas', 'Cordoba', 'Mendoza', 'CEDULA_CIUDADANIA'); 

-- DIRECCIONES DE CLIENTE
-- ************************************	
INSERT INTO customer_address (city_id, customer_address_id, customer_id, address_line1, address_line2) VALUES(1, 1, 1, '123 Main St', 'Apt 4B'), (2, 2, 2, '456 Market St', 'Suite 300'), (3, 3, 3, '789 Broadway', 'Floor 10'), (4, 4, 4, '101 Elm St', 'Apt 12A'), (5, 5, 5, '202 King St', 'Suite 10'), (6, 6, 6, '303 Queen St', 'Floor 5'), (7, 7, 7, '404 Granville St', 'Apt 202'), (8, 8, 8, '505 Government St', 'Suite 150'), (9, 9, 9, '606 Reforma Ave', 'Apt 8B'), (10, 10, 10, '707 Independencia Ave', 'Floor 3'); 

-- TELEFONO DE CLIENTE
-- ************************************	
INSERT INTO customer_phone (customer_id, customer_phone_id, phone_number, telephone_type) VALUES(1, 1, '555-1000', 'CELULAR'), (2, 2, '555-2000', 'FIJO'), (3, 3, '555-3000', 'CELULAR'), (4, 4, '555-4000', 'FIJO'), (5, 5, '555-5000', 'CELULAR'), (6, 6, '555-6000', 'FIJO'), (7, 7, '555-7000', 'CELULAR'), (8, 8, '555-8000', 'FIJO'), (9, 9, '555-9000', 'CELULAR'), (10, 10, '555-0101', 'FIJO'); 

-- ORDENES
-- ************************************		
INSERT INTO orders (deliver_date, expected_date, order_date, customer_id, order_id, order_status_id, commentary, order_type) VALUES('2024-08-15', '2024-08-14', '2024-08-01', 1, 1, 1, 'Urgente', 'COMPRA'), ('2024-08-20', '2024-08-19', '2024-08-05', 2, 2, 2, 'Requiere revision', 'VENTA'), ('2024-09-01', '2024-08-30', '2024-08-10', 3, 3, 3, 'Entregado a tiempo', 'COMPRA'), ('2024-09-10', '2024-09-09', '2024-08-15', 4, 4, 1, 'En espera de confirmacion', 'VENTA'), ('2024-09-15', '2024-09-14', '2024-08-20', 5, 5, 2, 'Solicitud de cambio de producto', 'COMPRA'), ('2024-10-01', '2024-09-30', '2024-08-25', 6, 6, 3, 'Cliente satisfecho', 'VENTA'), ('2024-10-05', '2024-10-04', '2024-09-01', 7, 7, 1, 'Verificar stock', 'COMPRA'), ('2024-10-15', '2024-10-14', '2024-09-10', 8, 8, 2, 'Faltan documentos', 'VENTA'), ('2024-11-01', '2024-10-31', '2024-09-20', 9, 9, 3, 'Entrega programada', 'COMPRA'), ('2024-11-10', '2024-11-09', '2024-09-25', 10, 10, 1, 'En revision final', 'VENTA'); 

-- DETALLES DE ORDENES
-- ************************************	
INSERT INTO order_detail (order_id, product_id, amount, unit_price, total_price) VALUES(1, 1, 2, 300, 600),    (1, 2, 1, 250, 250),    (2, 3, 1, 600, 600),    (2, 4, 2, 550, 1100),  (3, 5, 3, 150, 450),    (3, 6, 1, 130, 130),    (4, 7, 4, 80, 320),     (4, 8, 5, 70, 350),     (5, 9, 10, 10, 100),    (5, 10, 7, 8, 56);      

-- METODOS DE PAGO
-- ************************************
INSERT INTO pay_method (name, description) VALUES('Tarjeta de Credito', 'Pago con tarjeta de credito Visa, MasterCard, American Express.'), ('Tarjeta de Debito', 'Pago con tarjeta de debito de cualquier entidad bancaria.'), ('Transferencia Bancaria', 'Pago mediante transferencia desde una cuenta bancaria.'), ('PayPal', 'Pago a traves de la plataforma PayPal.'), ('Stripe', 'Pago a traves de la plataforma Stripe.'), ('Efectivo', 'Pago en efectivo al momento de la entrega o en puntos de pago autorizados.'), ('Bitcoin', 'Pago utilizando criptomoneda Bitcoin.'), ('Apple Pay', 'Pago a traves de la plataforma Apple Pay en dispositivos Apple.'), ('Google Pay', 'Pago a traves de la plataforma Google Pay en dispositivos Android.'), ('Cheque', 'Pago mediante cheque bancario.'); 

-- PAGOS
-- ************************************	
INSERT INTO transactions (amount, order_id, pay_method_id, transaction_date) VALUES(150.00, 1, 1, '2024-08-05 14:30:00'), (200.00, 2, 2, '2024-08-05 15:00:00'), (300.00, 3, 3, '2024-08-05 15:30:00'), (250.00, 4, 4, '2024-08-05 16:00:00'), (120.00, 5, 5, '2024-08-05 16:30:00'), (400.00, 6, 6, '2024-08-05 17:00:00'), (180.00, 7, 7, '2024-08-05 17:30:00'), (220.00, 8, 8, '2024-08-05 18:00:00'), (90.00, 9, 9, '2024-08-05 18:30:00'), (130.00, 10, 10, '2024-08-05 19:00:00'), (160.00, 1, 11, '2024-08-05 19:30:00'), (170.00, 2, 12, '2024-08-05 20:00:00'), (190.00, 3, 13, '2024-08-05 20:30:00'); 


-- INSERT INTO permission(permission_id, name) VALUES(1, 'READ'), (2,'WRITE');