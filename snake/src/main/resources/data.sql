
-- password = 123456
INSERT INTO usuario
 (id  ,  username,  password,          rol, enabled, ultimo_acceso, telegram_id) VALUES
 (1   , 'chester', '$2a$10$o7uXsmd6UB.zqJpcjV8aqOR.fGhRV6UgcmGpmhh4uqII8tQ382NpK',  'ROLE_USER',       1,  '2018-04-10', 1234),
 (2   ,   'homer',   '$2a$10$o7uXsmd6UB.zqJpcjV8aqOR.fGhRV6UgcmGpmhh4uqII8tQ382NpK',  'ROLE_USER',       1,  '2018-04-11', 5678),
 (3   ,   'admin',   '$2a$10$o7uXsmd6UB.zqJpcjV8aqOR.fGhRV6UgcmGpmhh4uqII8tQ382NpK', 'ROLE_ADMIN',       1,  '2018-04-11', 0000);

INSERT INTO billetera
 (id  , usuario_id, moneda_nombre, cantidad, diferencia) VALUES
 (1   , 1         ,     'bitcoin',     10.0,     -100.0),
 (2   , 1         ,    'ethereum',      5.0,      -10.0),
 (3   , 1         ,    'dogecoin',        0,          0),
 (4   , 2         ,     'bitcoin',        0,          0);

INSERT INTO transaccion
 (id  , billetera_id, moneda_nombre, cantidad, cotizacion,        fecha,     tipo) VALUES
 (1   ,            1,     'bitcoin',   '0.01',  '8000.01', '2018-04-10', 'COMPRA'),
 (2   ,            1,     'bitcoin',   '0.02',  '8001.50', '2018-04-09',  'VENTA'),
 (3   ,            1,    'ethereum',   '1.00',    '125.5', '2018-04-08', 'COMPRA');