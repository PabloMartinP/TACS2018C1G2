import React, { Component } from 'react'
        import Billetera from './Billetera'
        import {
        Table,
                TableBody,
                TableHeader,
                TableHeaderColumn,
                TableRow
                } from 'material-ui/Table';
import TextField from 'material-ui/TextField';
import './Portfolio.css';
import AgregarTransaccion from './AgregarTransaccion.jsx';

export default class Portfolio extends Component {
    constructor(props) {
        super(props)
        this.state = {portfolio: null}
    }

    componentDidMount() {
        const result = [
            {
                "moneda": {
                    "nombre": "bitcoin",
                    "_links": {
                        "cotizacion": {"href": "/api/monedas/bitcoin/cotizacion"}
                    }
                },
                "cantidad": 10.000000000000,
                "_links": {
                    "transacciones": {"href": "/api/usuarios/1/monedas/bitcoin/transacciones"},
                    "usuario": {"href": "/api/usuarios/1"}
                }
            },
            {
                "moneda": {
                    "nombre": "ethereum",
                    "_links": {
                        "cotizacion": {
                            "href": "/api/monedas/ethereum/cotizacion"
                        }
                    }
                },
                "cantidad": 5.000000000000,
                "_links": {
                    "transacciones": {"href": "/api/usuarios/1/monedas/ethereum/transacciones"},
                    "usuario": {"href": "/api/usuarios/1"}
                }
            },
            {
                "moneda": {
                    "nombre": "doge",
                    "_links": {
                        "cotizacion": {"href": "/api/monedas/doge/cotizacion"}
                    }
                },
                "cantidad": 0.001000000000,
                "_links": {
                    "transacciones": {"href": "/api/usuarios/1/monedas/doge/transacciones"},
                    "usuario": {"href": "/api/usuarios/1"}
                }
            }
        ]
        this.setState({portfolio: result})
        // fetch('/api/usuarios/1/portfolio')
        // .then(response => response.json())
        // .then(result => this.setState({portfolio: result}))
    }

    render() {
        return (
                <div className="table-container">
                    <AgregarTransaccion />
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
                                this.state.portfolio ?
                                        this.state.portfolio.map((billetera, index) => <Billetera key={index} billetera={billetera} />)
                                        : <TableRow></TableRow>
                            }
                        </TableBody>
                    </Table>
                </div>
                )
    }
}
