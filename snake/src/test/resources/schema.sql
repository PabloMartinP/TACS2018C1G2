CREATE TABLE usuario (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(255),
  password varchar(255) NOT NULL DEFAULT '123456',
  rol varchar(50) NOT NULL,
  enabled bit(1) NOT NULL DEFAULT 1,
  ultimo_acceso datetime,
  telegram_id bigint(20), 
  PRIMARY KEY (id),
  UNIQUE KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE billetera (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  usuario_id bigint(20) NOT NULL,
  moneda_nombre varchar(50),
  cantidad_actual decimal(21,12),
  dinero_invertido decimal(21,12),  
  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  UNIQUE KEY (usuario_id, moneda_nombre)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE transaccion (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  billetera_id bigint(20) NOT NULL,
  moneda_nombre varchar(50),
  cantidad decimal(21,12),
  cotizacion decimal(21,12),
  fecha datetime,
  tipo varchar(50),
  PRIMARY KEY (id),
  FOREIGN KEY (billetera_id) REFERENCES billetera(id),
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;




