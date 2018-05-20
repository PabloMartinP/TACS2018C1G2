import React, {Component} from 'react'
import Billetera from './Billetera'
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow
} from 'material-ui/Table';
import './Portfolio.css';
import AgregarTransaccion from './AgregarTransaccion.jsx';
import {SnakeRestAPI} from '../models/SnakeRestAPI';

export default class Portfolio extends Component {
    constructor(props) {
        super(props);
        this.state = {usuario: null, cotizador: null}
    }

    componentDidMount() {
        SnakeRestAPI.obtenerUsuarioPorId(this.props.usuarioId)
            .then(usuario => this.setState({ usuario }));

        SnakeRestAPI.obtenerCotizador()
            .then(cotizador => this.setState({ cotizador }));
    }

    render() {
        return (
            <div className="table-container">
                { this.state.cotizador ? <AgregarTransaccion cotizador={this.state.cotizador}/> : null }
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
                                Array.from(this.state.usuario.listarCriptomonedas()).map(([moneda, cantidad]) => <Billetera key={moneda}
                                                                                                                            usuario={this.state.usuario}
                                                                                                                            moneda={moneda}
                                                                                                                            cotizacion={this.state.cotizador.cotizar(moneda)}
                                                                                                                            cantidad={cantidad} />)
                                : <TableRow></TableRow>
                        }
                    </TableBody>
                </Table>
            </div>
        )
    }
}
