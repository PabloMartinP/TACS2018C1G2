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

export default class Portfolio extends Component {
    constructor(props) {
        super(props)
        this.state = {portfolio: null}
    }

    componentDidMount() {
        fetch('http://tacs2018-snake.herokuapp.com/api/usuarios/1/portfolio')
            .then(response => response.json())
            .then(result => this.setState({portfolio: result}))
    }

    render() {
        return (
            <div className="table-container">
                <AgregarTransaccion/>
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
                                this.state.portfolio.map((billetera, index) => <Billetera key={index} billetera={billetera}/>)
                                : <TableRow></TableRow>
                        }
                    </TableBody>
                </Table>
            </div>
        )
    }
}
