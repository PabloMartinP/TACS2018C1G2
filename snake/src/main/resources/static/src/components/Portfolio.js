import React, { Component } from 'react'
import Billetera from './Billetera'


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
                        "cotizacion": { "href": "http://tacs2018-snake.herokuapp.com/api/monedas/bitcoin/cotizacion" }
                    }
                },
                "cantidad": 10.000000000000,
                "_links": {
                    "transacciones": { "href": "http://tacs2018-snake.herokuapp.com/api/usuarios/1/monedas/bitcoin/transacciones" },
                    "usuario": {"href": "http://tacs2018-snake.herokuapp.com/api/usuarios/1" }
                }
            },
            {
                "moneda": {
                    "nombre": "ethereum",
                    "_links": {
                        "cotizacion": {
                            "href": "http://tacs2018-snake.herokuapp.com/api/monedas/ethereum/cotizacion"
                        }
                    }
                },
                "cantidad": 5.000000000000,
                "_links": {
                    "transacciones": { "href": "http://tacs2018-snake.herokuapp.com/api/usuarios/1/monedas/ethereum/transacciones" },
                    "usuario": { "href":"http://tacs2018-snake.herokuapp.com/api/usuarios/1" }
                }
            },
            {
                "moneda": {
                    "nombre": "doge",
                    "_links": {
                        "cotizacion": { "href":"http://tacs2018-snake.herokuapp.com/api/monedas/doge/cotizacion" }
                    }
                },
                "cantidad": 0.001000000000,
                "_links":{
                    "transacciones": { "href":"http://tacs2018-snake.herokuapp.com/api/usuarios/1/monedas/doge/transacciones" },
                    "usuario": { "href":"http://tacs2018-snake.herokuapp.com/api/usuarios/1" }
                }
            }
        ]
        this.setState({portfolio: result})
        // fetch('http://tacs2018-snake.herokuapp.com/api/usuarios/1/portfolio')
            // .then(response => response.json())
            // .then(result => this.setState({portfolio: result}))
    }

    render() {
        return (
            <div>
              <p>Portfolio:</p>
              { this.state.portfolio ? this.state.portfolio.map((billetera, index) => <Billetera key={index} billetera={billetera} />) : <p>Cargando...</p> }
            </div>
        )
    }
}
