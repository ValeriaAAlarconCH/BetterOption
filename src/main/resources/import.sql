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
INSERT INTO usuario (nombre, correo, password, rol, fecha_registro) VALUES ('Juan Pérez', 'juanperez@gmail.com', 123456789, 'ROLE_USER', CURRENT_DATE);
INSERT INTO usuario (nombre, correo, password, rol, fecha_registro) VALUES ('Admin Tech', 'admin@betteroption.com', 987654321, 'ROLE_ADMIN', CURRENT_DATE);

-- ===============================
-- MICROEMPRESAS
-- ===============================
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('TechStyle Perú', 'Moda', 'Av. Primavera 123', 987654321, 'contacto@techstyle.pe', 'Moda urbana y juvenil', 1);
INSERT INTO microempresa (nombre_negocio, rubro, direccion, telefono, email, descripcion, id_usuario) VALUES ('Sabor Natural', 'Alimentos', 'Jr. Cusco 456', 912345678, 'ventas@sabornatural.pe', 'Snacks saludables hechos en Perú', 2);

