const assert = require('assert');
const {Compra, Venta} = require('../models/Transaccion');
const {Usuario, FondosNoSuficientesError, FalloEnRegistroDeTransaccionError} = require('../models/Usuario');


const CotizadorStub = {
    cotizacionDelDia: 0,
    cotizar() {
        return this.cotizacionDelDia;
    }
};

describe('Un usuario', () => {
    let unUsuario;
    let unaCriptomoneda;
    let unCotizador;
    const cotizacionDelDia = 50;
    const SnakeRestAPIStub = {
        registrarTransaccion() {}
    };

    beforeEach(() => {
        unCotizador = Object.create(CotizadorStub);
        unCotizador.cotizacionDelDia = cotizacionDelDia;
        unUsuario = Usuario.crear('unId', 'unNombre', SnakeRestAPIStub, new Date());
        unaCriptomoneda = {};
    });

    it('puede comprar una cierta cantidad de una criptomoneda', () => {
        unUsuario.comprar(unaCriptomoneda, 10, unCotizador);
        assert.equal(unUsuario.cantidadDe(unaCriptomoneda), 10);
    });

    it('no puede vender una criptomoneda que no posee', () => {
        assert.throws(() => unUsuario.vender(unaCriptomoneda, 1, unCotizador), new FondosNoSuficientesError(unUsuario.VENTA_CON_FONDOS_INSUFICIENTES));
        assert.equal(unUsuario.cantidadDe(unaCriptomoneda), 0);
        assert.deepEqual(unUsuario.listarTransacciones(), []);
    });

    it('puede vender criptomonedas que si posee', () => {
        unUsuario.comprar(unaCriptomoneda, 20, unCotizador);
        unUsuario.vender(unaCriptomoneda, 5, unCotizador);
        assert.equal(unUsuario.cantidadDe(unaCriptomoneda), 15);
    });

    it('puede listar las transacciones hechas', () => {
        unUsuario.comprar(unaCriptomoneda, 12, unCotizador);
        unUsuario.vender(unaCriptomoneda, 1, unCotizador);

        const [compra, venta] = unUsuario.listarTransacciones();

        assert.equal(compra.criptomoneda, unaCriptomoneda);
        assert.equal(compra.cantidad, 12);
        assert.equal(compra.cotizacionAlMomentoDeLaTransaccion, cotizacionDelDia);

        assert.equal(venta.criptomoneda, unaCriptomoneda);
        assert.equal(venta.cantidad, 1);
        assert.equal(venta.cotizacionAlMomentoDeLaTransaccion, cotizacionDelDia);
    });

    it('puede listar las transacciones realizadas con una criptomoneda', () => {
        const otraCriptomoneda = {};

        unUsuario.comprar(unaCriptomoneda, 10, unCotizador);
        unUsuario.vender(unaCriptomoneda, 3, unCotizador);
        unUsuario.comprar(otraCriptomoneda, 8, unCotizador);

        const transaccionesDeCriptomoneda = unUsuario.listarTransaccionesDe(unaCriptomoneda);
        assert.deepEqual(transaccionesDeCriptomoneda, [
            Compra.crear(unaCriptomoneda, 10, cotizacionDelDia),
            Venta.crear(unaCriptomoneda, 3, cotizacionDelDia),
        ])
    });

    it('debe informar al SnakeService ante cada transacción que se registre', () => {
        const anotherSnakeRestAPIStub = {
            fueLlamado: false,

            registrarTransaccion() {
                this.fueLlamado = true;
            }
        };

        unUsuario = Usuario.crear('unId', 'unNombre', anotherSnakeRestAPIStub, new Date());
        unUsuario.comprar(unaCriptomoneda, 10, unCotizador);
        assert(anotherSnakeRestAPIStub.fueLlamado);
    });

    it('no debe agregar una transaccion si la llamada a SnakeRestAPI falla', () => {
        const failingSnakeRestAPIControllerStub = {
            fueLlamado: false,

            registrarTransaccion() {
                this.fueLlamado  = true;
                throw new Error();
            }
        };

        unUsuario = Usuario.crear('unId', 'unNombre', failingSnakeRestAPIControllerStub, new Date());
        assert.throws(() => unUsuario.comprar(unaCriptomoneda, 10, unCotizador), FalloEnRegistroDeTransaccionError);
        assert(failingSnakeRestAPIControllerStub.fueLlamado);
        assert.equal(unUsuario.listarTransacciones().length, 0);
    })
});