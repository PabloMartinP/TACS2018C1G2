import React from 'react'

export default ({billetera}) => (
    <div>
      {billetera.moneda.nombre}  Cantidad: {billetera.cantidad}  Cotización actual: USD {billetera.moneda.corizacionActual || '100000'} Ganancia/Perdida: USD <span>-400</span>
    </div>
)
