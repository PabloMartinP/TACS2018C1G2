import React, {Component} from 'react';
import {
    Table,
    TableBody,
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';
import {Usuario} from '../../models/Usuario';
const moment = require('moment');

export default class InfoDeUsuario extends Component {
    render() {
        const { usuario } = this.props;
        return (
            <Table>
                <TableBody displayRowCheckbox={false}>
                    <TableRow>
                        <TableRowColumn>Nombre de usuario</TableRowColumn>
                        <TableRowColumn>{usuario.nombre}</TableRowColumn>
                    </TableRow>
                    <TableRow>
                        <TableRowColumn>Cantidad de criptomonedas</TableRowColumn>
                        <TableRowColumn>{Array.from(usuario.listarCriptomonedas()).length}</TableRowColumn>
                    </TableRow>
                    <TableRow>
                        <TableRowColumn>Cantidad de transacciones</TableRowColumn>
                        <TableRowColumn>{usuario.listarTransacciones().length}</TableRowColumn>
                    </TableRow>
                    <TableRow>
                        <TableRowColumn>Último acceso</TableRowColumn>
                        <TableRowColumn>{moment(usuario.fechaDeUltimoAcceso).format('DD/MM/YYYY')}</TableRowColumn>
                    </TableRow>
                </TableBody>
            </Table>
        )
    }
}
