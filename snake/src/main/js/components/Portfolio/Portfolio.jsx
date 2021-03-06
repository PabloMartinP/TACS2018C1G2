import React, {Component} from 'react'
import Billetera from '../Billetera/Billetera.jsx'
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow
} from 'material-ui/Table';
import './Portfolio.css';
import AgregarTransaccion from '../AgregarTransaccion/AgregarTransaccion.jsx';
import {SnakeRestAPI} from '../../models/SnakeRestAPI';

export default class Portfolio extends Component {
    constructor(props) {
        super(props);
        this.state = {usuario: null, cotizador: null}
    }

    actualizarInfoPortfolio() {
        SnakeRestAPI.obtenerUsuario()
            .then(usuario => this.setState({ usuario }));

        SnakeRestAPI.obtenerCotizador()
            .then(cotizador => this.setState({ cotizador }));
    }

    componentDidMount() {
        this.actualizarInfoPortfolio();
    }

    render() {
        return (
            <div className="table-container">
                { this.state.cotizador ?
                    <AgregarTransaccion
                        cotizador={this.state.cotizador}
                        actualizarInfoPortfolio={this.actualizarInfoPortfolio.bind(this)}/>
                    : null 
                }
                <h5>Portfolio:</h5>
                <Table>
                    <TableHeader displaySelectAll={false}>
                        <TableRow>
                            <TableHeaderColumn>Moneda</TableHeaderColumn>
                            <TableHeaderColumn>Cantidad</TableHeaderColumn>
                            <TableHeaderColumn>Cotización actual</TableHeaderColumn>
                            <TableHeaderColumn>Ganancia/Perdida</TableHeaderColumn>
                            <TableHeaderColumn>Ver Transacciones</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        {
                            (this.state.usuario && this.state.cotizador) ?
                                Array.from(this.state.usuario.listarCriptomonedas()).map(([moneda, cantidad]) => {
                                    return <Billetera key={moneda}
                                    usuario={this.state.usuario}
                                    moneda={moneda}
                                    cotizacion={this.state.cotizador.cotizaciones.get(moneda)}
                                    cantidad={cantidad} />
                                })
                                : <TableRow></TableRow>
                        }
                    </TableBody>
                </Table>
            </div>
        )
    }
}
