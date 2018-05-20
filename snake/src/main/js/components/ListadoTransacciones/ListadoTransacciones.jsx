import React, { Component } from 'react'
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';
import '../Portfolio/Portfolio.css';

export default class Transacciones extends Component {

    render() {
        return (
            <Table>
                <TableHeader displaySelectAll={false}>
                    <TableRow>
                        <TableHeaderColumn>Tipo de Transacción</TableHeaderColumn>
                        <TableHeaderColumn>Cantidad</TableHeaderColumn>
                        <TableHeaderColumn>Cotización</TableHeaderColumn>
                        <TableHeaderColumn>Fecha</TableHeaderColumn>
                    </TableRow>
                </TableHeader>
                <TableBody displayRowCheckbox={false}>
                    { this.props.usuario.listarTransaccionesDe(this.props.moneda).map((transaccion, index) =>
                        <TableRow key={index}>
                            <TableRowColumn>{transaccion.nombre}</TableRowColumn>
                            <TableRowColumn>{transaccion.cantidad}</TableRowColumn>
                            <TableRowColumn>USD {transaccion.cotizacionAlMomentoDeLaTransaccion}</TableRowColumn>
                            <TableRowColumn>09/03/2018</TableRowColumn>
                        </TableRow>
                    )}
                </TableBody>
            </Table>
        )
    }
}
