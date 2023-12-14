INSERT INTO clientes (id,nombre,apellido,forma_pago,creado_en,editado_en) VALUES (1,'Andres','Guzman','debito',NULL,NULL),(2,'John','Doe','credito',NULL,NULL),(4,'Pepa','Doe','debito',NULL,NULL),(5,'Roberto','Wutzke','efectivo',NULL,NULL),(6,'Luna','Garcia','paypal',NULL,NULL),(9,'Andres','Suarez','mercadopago',NULL,'2023-12-04 19:34:13'),(10,'Lou','Loe','credito',NULL,NULL),(11,'Betiana','Wutzke','efectivo','2023-12-04 19:32:47',NULL),(12,'Nelson','Perez','mercadopago','2023-12-04 19:44:52','2023-12-04 19:45:50');
INSERT INTO alumnos (id, nombre, apellido) VALUES(1,'Johana','Doe')
INSERT INTO alumnos (id, nombre, apellido) VALUES(2,'Pepe','Gon')
INSERT INTO cursos (id, titulo, profesor) VALUES(1,'Curso SQL','Juan')
INSERT INTO cursos (id, titulo, profesor) VALUES(2,'Curso NodeJS','Juan')
INSERT INTO direcciones(calle, numero) VALUES ('vaticano',123);
INSERT INTO direcciones(calle, numero) VALUES ('colon',456);
INSERT INTO tbl_clientes_direcciones (id_cliente, id_direccion) VALUES (1,1);
INSERT INTO tbl_clientes_direcciones (id_cliente, id_direccion) VALUES (1,2);
INSERT INTO clientes_detalles (prime,puntos_acumulados, cliente_detalle_id) VALUES (1,8000,1);
INSERT INTO tbl_alumnos_cursos (alumno_id, curso_id) VALUES (1,1);
INSERT INTO tbl_alumnos_cursos (alumno_id, curso_id) VALUES (1,2);