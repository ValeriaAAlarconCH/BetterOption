INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_MICROEMPRESARIO');
INSERT INTO users(username, password) VALUES ('user1','$2a$12$tlMaQAFeJ82Bxx3dIsNZfuSoTy/86E3KDuv8osmM7Wmd/skEQRw0.');
INSERT INTO users(username, password) VALUES ('admin','$2a$12$cmgc7jkDr1PntzM2xNiXA.0GQNCHIgxgWo8vBWr5FIJJtwZUES/Ly');
INSERT INTO users(username, password) VALUES ('microempresario','$2a$12$.qcDOn80r9rG9.hI17WO7.S1KHpXwzuo6lMM5zckopfoh0LWaYoM2');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3);

-- ===============================
-- USUARIOS
-- ===============================
INSERT INTO usuario (nombre, correo, password, rol, fecha_registro) VALUES ('Juan Pérez', 'juanperez@gmail.com', '123456789', 'ROLE_USER', '2025-05-29');
INSERT INTO usuario (nombre, correo, password, rol, fecha_registro) VALUES ('Admin Tech', 'admin@betteroption.com', '987654321', 'ROLE_ADMIN', '2025-05-20');

-- ===============================
-- MICROEMPRESAS
-- ===============================
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('TechStyle Perú', 'Moda', 'Av. Primavera 123', 987654321, 'contacto@techstyle.pe', 'Moda urbana y juvenil', 1);
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('Sabor Natural', 'Alimentos', 'Jr. Cusco 456', 912345678, 'ventas@sabornatural.pe', 'Snacks saludables hechos en Perú', 2);

-- ===============================
-- CATEGORÍAS
-- ===============================
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Ropa Deportiva', 'Prendas para ejercicio y deporte');
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Snacks Saludables', 'Alimentos naturales y funcionales');

-- ===============================
-- PRODUCTOS
-- ===============================
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Polera Urban Fit', 'Polera deportiva unisex con diseño moderno', 89.90, 50, 'https://example.com/polera.jpg', 1, 1);
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Chips de Quinua', 'Snack crocante de quinua horneada', 12.50, 100, 'https://example.com/chips.jpg', 2, 2);
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Leggins Flex', 'Leggins elásticos para entrenamiento', 75.00, 35, 'https://example.com/leggins.jpg', 1, 1);
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Barra Energética Andina', 'Barra con maca, kiwicha y cacao', 6.50, 150, 'https://example.com/barra.jpg', 2, 2);

-- ===============================
-- CATÁLOGO DE PROMOCIONES
-- ===============================
INSERT INTO catalogopromociones (nombre_catalogo, descripcion, fecha_inicio, fecha_fin, id_microempresa) VALUES ('Promos de Temporada', 'Descuentos por campaña otoño-invierno', '2025-04-20', '2025-05-20', 1);
INSERT INTO catalogopromociones (nombre_catalogo, descripcion, fecha_inicio, fecha_fin, id_microempresa) VALUES ('Lanzamiento Saludable', 'Precios especiales por nuevos productos', '2025-03-15', '2025-04-15', 2);

-- ===============================
-- PRODUCTO CATALOGO
-- ===============================
INSERT INTO productocatalogo (descuento_porcentaje, id_catalogopromociones, id_producto) VALUES (20.0, 1, 1); -- Polera Urban Fit
INSERT INTO productocatalogo (descuento_porcentaje, id_catalogopromociones, id_producto) VALUES (15.0, 2, 2); -- Chips de Quinua
INSERT INTO productocatalogo (descuento_porcentaje, id_catalogopromociones, id_producto) VALUES (25.0, 1, 3); -- Leggins Flex

-- ===============================
-- WISHLIST
-- ===============================
INSERT INTO wishlist (fecha_agregado, id_usuario, id_producto) VALUES ('2025-05-20', 1, 1);
INSERT INTO wishlist (fecha_agregado, id_usuario, id_producto) VALUES ('2025-04-28', 1, 2);
INSERT INTO wishlist (fecha_agregado, id_usuario, id_producto) VALUES ('2025-04-16', 1, 3);

-- ===============================
-- NOTIFICACIONES
-- ===============================
INSERT INTO notificacion (mensaje, tipo, fecha_envio, leido, id_usuario) VALUES ('¡Tu producto favorito tiene descuento!', 'Promoción', '2025-05-12', false, 1);
INSERT INTO notificacion (mensaje, tipo, fecha_envio, leido, id_usuario) VALUES ('Nuevo catálogo disponible en tu microempresa', 'Alerta', '2025-05-25',false,1);
