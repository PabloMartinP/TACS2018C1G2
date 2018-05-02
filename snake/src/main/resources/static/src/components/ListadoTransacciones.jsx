import React, { Component } from 'react'
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';
import TextField from 'material-ui/TextField';
import './Portfolio.css';

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
                    <TableRow>
                        <TableRowColumn>Venta</TableRowColumn>
                        <TableRowColumn>3</TableRowColumn>
                        <TableRowColumn>USD 10.105</TableRowColumn>
                        <TableRowColumn>09/03/2018</TableRowColumn>
                    </TableRow>
                    <TableRow>
                        <TableRowColumn>Compra</TableRowColumn>
                        <TableRowColumn>10</TableRowColumn>
                        <TableRowColumn>USD 8.120</TableRowColumn>
                        <TableRowColumn>30/01/2018</TableRowColumn>
                    </TableRow>
                    <TableRow>
                        <TableRowColumn>Compra</TableRowColumn>
                        <TableRowColumn>2</TableRowColumn>
                        <TableRowColumn>USD 15.644</TableRowColumn>
                        <TableRowColumn>21/11/2017</TableRowColumn>
                    </TableRow>
                    <TableRow>
                        <TableRowColumn>Venta</TableRowColumn>
                        <TableRowColumn>3</TableRowColumn>
                        <TableRowColumn>USD 10.105</TableRowColumn>
                        <TableRowColumn>09/03/2018</TableRowColumn>
                    </TableRow>
                    <TableRow>
                        <TableRowColumn>Compra</TableRowColumn>
                        <TableRowColumn>10</TableRowColumn>
                        <TableRowColumn>USD 8.120</TableRowColumn>
                        <TableRowColumn>30/01/2018</TableRowColumn>
                    </TableRow>
                    <TableRow>
                        <TableRowColumn>Compra</TableRowColumn>
                        <TableRowColumn>2</TableRowColumn>
                        <TableRowColumn>USD 15.644</TableRowColumn>
                        <TableRowColumn>21/11/2017</TableRowColumn>
                    </TableRow>
                </TableBody>
            </Table>
        )
    }
}
