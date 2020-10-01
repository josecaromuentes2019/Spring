INSERT INTO clientes(nombre,apellido,email,create_at,foto) VALUES('Jose','Caro','jose@correo.com','2020-07-12','');
INSERT INTO clientes(nombre,apellido,email,create_at,foto) VALUES('Juan','Coronado','juan@correo.com','2019-02-12','');
INSERT INTO clientes(nombre,apellido,email,create_at,foto) VALUES('Jairo','Cuadrado','jairo@correo.com','2020-07-17','');
INSERT INTO clientes(nombre,apellido,email,create_at,foto) VALUES('Jonh','Cardona','jonh@correo.com','2016-03-24','');


INSERT INTO productos(nombre,precio,create_at) VALUES ('computador Hp',1000000,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('computador Dell',1500000,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('computador Asus',1300000,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Impresora Lacer hp',300000,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Mesa pc',100000,NOW());

INSERT INTO facturas(descripcion,observacion,create_at,cliente_id) VALUES ('Dispositivos Electronicos',null,now(),1);
INSERT INTO items(cantidad,factura_id,producto_id) VALUES (3,1,1);
INSERT INTO items(cantidad,factura_id,producto_id) VALUES (1,1,4);

INSERT INTO facturas(descripcion,observacion,create_at,cliente_id) VALUES ('Material de oficina',null,now(),1);
INSERT INTO items(cantidad,factura_id,producto_id) VALUES (2,2,5);

INSERT INTO facturas(descripcion,observacion,create_at,cliente_id) VALUES ('Computaores',null,now(),1);
INSERT INTO items(cantidad,factura_id,producto_id) VALUES (2,3,2);
