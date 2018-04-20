INSERT INTO usuario
 (id  ,  username,  password, enabled, ultimo_acceso) VALUES
 (1   , 'Chester',  'Turley',       1,  '2018-04-10');

INSERT INTO billetera
 (id  , usuario_id, moneda_nombre, cantidad) VALUES
 (1   , 1         ,     'bitcoin',     10.0);