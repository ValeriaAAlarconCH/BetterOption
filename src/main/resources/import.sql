INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_MICROEMPRESARIO');
INSERT INTO users(username, password) VALUES ('user1','$2a$12$tlMaQAFeJ82Bxx3dIsNZfuSoTy/86E3KDuv8osmM7Wmd/skEQRw0.');
INSERT INTO users(username, password) VALUES ('admin','$2a$12$cmgc7jkDr1PntzM2xNiXA.0GQNCHIgxgWo8vBWr5FIJJtwZUES/Ly');
INSERT INTO users(username, password) VALUES ('microempresario','$2a$12$.qcDOn80r9rG9.hI17WO7.S1KHpXwzuo6lMM5zckopfoh0LWaYoM2');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3);