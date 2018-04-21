INSERT INTO usuario
 (id  ,  username,  password, enabled, ultimo_acceso) VALUES
 (1   , 'Chester',  'Turley',       1,  '2018-04-10');

INSERT INTO billetera
 (id  , usuario_id, moneda_nombre, cantidad) VALUES
 (1   , 1         ,     'bitcoin',     10.0),
 (2   , 1         ,    'ethereum',      5.0),
 (3   , 1         ,        'doge',    0.001);

INSERT INTO transaccion
 (id  , billetera_id, moneda_nombre, cantidad, cotizacion,        fecha,     tipo) VALUES
 (1   ,            1,     'bitcoin',   '0.01',  '8000.01', '2018-04-10', 'COMPRA'),
 (2   ,            1,     'bitcoin',   '0.02',  '8001.50', '2018-04-09',  'VENTA'),
 (3   ,            1,    'ethereum',   '1.00',    '125.5', '2018-04-08', 'COMPRA');