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
INSERT INTO usuario (nombre, correo, password, rol, fecha_registro) VALUES ('Lucía Ramos', 'lucia@betteroption.com', 'clave123', 'ROLE_MICROEMPRESARIO', '2025-05-18');
INSERT INTO usuario (nombre, correo, password, rol, fecha_registro) VALUES ('Carlos Mejía', 'carlos@gmail.com', 'pass456', 'ROLE_USER', '2025-05-15');
INSERT INTO usuario (nombre, correo, password, rol, fecha_registro) VALUES ('Ana Torres', 'ana@empresa.com', 'secreta789', 'ROLE_MICROEMPRESARIO', '2025-05-10');

-- ===============================
-- MICROEMPRESAS
-- ===============================
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('TechStyle Perú', 'Moda', 'Av. Primavera 123', 987654321, 'contacto@techstyle.pe', 'Moda urbana y juvenil', 1);
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('Sabor Natural', 'Alimentos', 'Jr. Cusco 456', 912345678, 'ventas@sabornatural.pe', 'Snacks saludables hechos en Perú', 2);
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('EcoDecor', 'Hogar', 'Calle Los Álamos 321', 900123456, 'eco@decor.pe', 'Decoración ecológica y sostenible', 3);
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('FitLife', 'Fitness', 'Av. La Marina 789', 987111222, 'contacto@fitlife.pe', 'Accesorios para entrenamiento personal', 5);
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('PetFriends', 'Mascotas', 'Jr. Mascotas 101', 901234567, 'info@petfriends.pe', 'Productos y alimentos para mascotas', 3);

-- ===============================
-- CATEGORÍAS
-- ===============================
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Ropa Deportiva', 'Prendas para ejercicio y deporte');
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Snacks Saludables', 'Alimentos naturales y funcionales');
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Decoración', 'Productos de decoración ecológicos');
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Accesorios Fitness', 'Equipos y herramientas para ejercicio');
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('Mascotas', 'Artículos para el cuidado de mascotas');

-- ===============================
-- PRODUCTOS
-- ===============================
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Polera Urban Fit', 'Polera deportiva unisex con diseño moderno', 89.90, 50, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY5B-MWugB04O2TdvO3Li7VTGvfttdzVTOMQ&s', 1, 1);
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Chips de Quinua', 'Snack crocante de quinua horneada', 12.50, 100, 'https://qfoods.pe/wp-content/uploads/2022/04/chips.png', 2, 2);
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Leggins Flex', 'Leggins elásticos para entrenamiento', 75.00, 35, 'https://rematexperu.com/cdn/shop/files/12.png?v=1731374274&width=500?width=450&quality=90', 1, 1);
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Maceta Reciclada', 'Maceta hecha de materiales reciclados', 30.00, 20, 'https://discomgrup.com/wp-content/webp-express/webp-images/uploads/2021/11/jardineras-plastico-reciclado-1-500x623.png.webp', 3, 3);
INSERT INTO producto (nombre_producto, descripcion, precio, stock, imagen, id_microempresa, id_categoria) VALUES ('Barra Energética Andina', 'Barra con maca, kiwicha y cacao', 6.50, 150, 'https://mamalamasnacks.com/wp-content/uploads/2021/08/BARRA_Maca_Bitter-1.png', 2, 2);

-- ===============================
-- CATÁLOGO DE PROMOCIONES
-- ===============================
INSERT INTO catalogo_promociones (nombre_catalogo, descripcion, fecha_inicio, fecha_fin, id_microempresa) VALUES ('Promos de Temporada', 'Descuentos por campaña otoño-invierno', '2025-05-20', '2025-06-30', 1);
INSERT INTO catalogo_promociones (nombre_catalogo, descripcion, fecha_inicio, fecha_fin, id_microempresa) VALUES ('Lanzamiento Saludable', 'Precios especiales por nuevos productos', '2025-05-15', '2025-06-15', 2);
INSERT INTO catalogo_promociones (nombre_catalogo, descripcion, fecha_inicio, fecha_fin, id_microempresa) VALUES ('Decor Eco Sale', 'Descuentos por el Día del Medio Ambiente', '2025-06-01', '2025-06-10', 3);
INSERT INTO catalogo_promociones (nombre_catalogo, descripcion, fecha_inicio, fecha_fin, id_microempresa) VALUES ('Fit Descuentos', 'Promos de inicio de temporada de entrenamiento', '2025-03-01', '2025-03-31', 4);
INSERT INTO catalogo_promociones (nombre_catalogo, descripcion, fecha_inicio, fecha_fin, id_microempresa) VALUES ('Mimos para tu Mascota', 'Ofertas para tus mascotas favoritas', '2025-04-01', '2025-04-30', 5);

-- ===============================
-- PRODUCTO CATALOGO
-- ===============================
INSERT INTO producto_catalogo (descuento_porcentaje, id_catalogopromociones, id_producto) VALUES (20.0, 1, 1);
INSERT INTO producto_catalogo (descuento_porcentaje, id_catalogopromociones, id_producto) VALUES (15.0, 2, 2);
INSERT INTO producto_catalogo (descuento_porcentaje, id_catalogopromociones, id_producto) VALUES (25.0, 1, 3);
INSERT INTO producto_catalogo (descuento_porcentaje, id_catalogopromociones, id_producto) VALUES (10.0, 3, 4);
INSERT INTO producto_catalogo (descuento_porcentaje, id_catalogopromociones, id_producto) VALUES (12.0, 2, 5);

-- ===============================
-- WISHLISTS
-- ===============================
INSERT INTO wishlist (fecha_agregado, id_usuario) VALUES ('2025-05-20', 1);
INSERT INTO wishlist (fecha_agregado, id_usuario) VALUES ('2025-04-28', 1);
INSERT INTO wishlist (fecha_agregado, id_usuario) VALUES ('2025-04-16', 2);
INSERT INTO wishlist (fecha_agregado, id_usuario) VALUES ( '2025-06-01', 4);
INSERT INTO wishlist (fecha_agregado, id_usuario) VALUES ( '2025-06-02', 5);

-- ===============================
-- RELACIÓN WISHLIST - PRODUCTO
-- ===============================
INSERT INTO wishlist_producto (wishlist_id, producto_id) VALUES (1, 1); -- Wishlist 1 → Producto 1
INSERT INTO wishlist_producto (wishlist_id, producto_id) VALUES (1, 2); -- Wishlist 1 → Producto 2
INSERT INTO wishlist_producto (wishlist_id, producto_id) VALUES (2, 3); -- Wishlist 2 → Producto 3
INSERT INTO wishlist_producto (wishlist_id, producto_id) VALUES (3, 4); -- Wishlist 3 → Producto 4
INSERT INTO wishlist_producto (wishlist_id, producto_id) VALUES (4, 5); -- Wishlist 4 → Producto 5
INSERT INTO wishlist_producto (wishlist_id, producto_id) VALUES (5, 1); -- Wishlist 5 → Producto 1

-- ===============================
-- NOTIFICACIONES
-- ===============================
INSERT INTO notificacion (mensaje, tipo, fecha_envio, leido, id_usuario) VALUES ('¡Tu producto favorito tiene descuento!', 'Promoción', '2025-05-12', false, 1);
INSERT INTO notificacion (mensaje, tipo, fecha_envio, leido, id_usuario) VALUES ('Nuevo catálogo disponible en tu microempresa', 'Alerta', '2025-05-25', false, 1);
INSERT INTO notificacion (mensaje, tipo, fecha_envio, leido, id_usuario) VALUES ('Tu pedido está en camino', 'Sistema', '2025-05-30', true, 2);
INSERT INTO notificacion (mensaje, tipo, fecha_envio, leido, id_usuario) VALUES ('Nuevas promociones activas esta semana', 'Promoción', '2025-06-01', false, 4);
INSERT INTO notificacion (mensaje, tipo, fecha_envio, leido, id_usuario) VALUES ('Catálogo "Decor Eco Sale" ya disponible', 'Promoción', '2025-06-02', false, 3);
