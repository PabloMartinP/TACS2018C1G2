import React from 'react'
import {
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';


export default ({billetera}) => (
    <TableRow>
      <TableRowColumn>{billetera.moneda.nombre}</TableRowColumn>
      <TableRowColumn>{billetera.cantidad}</TableRowColumn>
      <TableRowColumn>USD {billetera.moneda.corizacionActual || '100000'}</TableRowColumn>
      <TableRowColumn>USD <span>-400</span></TableRowColumn> 
    </TableRow>
)
