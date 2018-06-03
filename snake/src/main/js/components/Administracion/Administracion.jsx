import React, {Component} from 'react';
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';
import Checkbox from 'material-ui/Checkbox';
import '../Portfolio/Portfolio.css';
import {SnakeRestAPI} from '../../models/SnakeRestAPI';
const moment = require('moment');
import {CantidadDeTxs} from '../../models/CantidadDeTxs';

export default class Administracion extends Component {
    constructor(props) {
        super(props);
        this.state = {
            usuario: null,
            cantTransacciones: CantidadDeTxs.inicializarCantDeTransacciones(),
            infoUsuarios: {
                usuario: {
                    name: 'Usuario',
                    value: 'Chester'
                },
                cantCripto: {
                    name: 'Cantidad de criptomonedas',
                    value: '2 (bitcoin, etherum)'
                },
                cantTxs: {
                    name: 'Cantidad de transacciones',
                    value: 100
                },
                ultimoAcceso: {
                    name: 'Último acceso',
                    value: moment(new Date()).format('DD/MM/YYYY')
                }
            },
            showSecondFilter: false,
            userFilter1: '',
            userFilter2: '',
            errors: {
                error1: {
                    text: 'Debe completar este campo',
                    displayError: false
                },
                error2: {
                    text: 'Debe completar ambos campos',
                    displayError: false
                }
            },
            resultadoComparacion: ''
        };
    }

    actualizarInfoTxs() {
        SnakeRestAPI.obtenerCantidadDeTransacciones(
            CantidadDeTxs.formatCantTxs(this.state.cantTransacciones))
            .then(cantidades => this.setState({cantTransacciones: cantidades}));
    }

    displaySecondFilter() {
        if (this.state.showSecondFilter) {
            return (
                <div className="row mb-20">
                    <div className="input col-md-3">
                        <br/>
                        <TextField
                            hintText="Usuario"
                            style={{width: "90%", marginLeft: "10px"}}
                            onChange={(e) => {
                                const { errors } = this.state;
                                errors.error2.displayError = false;
                                this.setState({userFilter2: e.target.value});
                            }}
                            errorText={
                                this.state.errors.error2.displayError ?
                                this.state.errors.error2.text :
                                ''
                            }
                        />
                    </div>
                    <div>
                        <br/>
                        <RaisedButton primary={true} onClick={this.compareUsers.bind(this)}>
                            COMPARAR
                        </RaisedButton>
                    </div>
                    <div className="comparisson-result">
                        <br />
                        <p>{this.state.resultadoComparacion}</p>
                    </div>
                </div>
            );
        }
        return '';
    }

    searchUsers() {
        if (this.state.userFilter1 === '') {
            const { errors } = this.state;
            errors.error1.displayError = true;
            this.setState({errors});
            return;
        }
    }

    compareUsers() {
        if (this.state.userFilter1 === '' || this.state.userFilter2 === '') {
            const { errors } = this.state;
            errors.error2.displayError = true;
            this.setState({errors});
            return;
        }
        this.setState({
            resultadoComparacion: this.state.userFilter1 + " tiene mayor capital que "
                + this.state.userFilter2
        });
    }

    componentDidMount() {
        this.actualizarInfoTxs();
    }

    render() {
        return (
            <div className="table-container admin">
                <h5> Información sobre transacciones </h5>
                <Table>
                    <TableHeader displaySelectAll={false}>
                        <TableRow>
                            <TableHeaderColumn></TableHeaderColumn>
                            <TableHeaderColumn>Cantidad de Transacciones</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false}>
                        {
                            this.state.cantTransacciones.map((cantTransaccion, index) => (
                                <TableRow key={index}>
                                    <TableRowColumn>{cantTransaccion.name}</TableRowColumn>
                                    <TableRowColumn>{cantTransaccion.value}</TableRowColumn>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
                <h5> Información sobre usuarios </h5>
                <div className="row mb-20">
                    <div className="input col-md-3">
                        <br/>
                        <TextField
                            hintText="Usuario"
                            style={{width: "90%", marginLeft: "10px"}}
                            onChange={(e) => {
                                const { errors } = this.state;
                                errors.error1.displayError = false;
                                errors.error2.displayError = false;
                                this.setState({userFilter1: e.target.value, errors})
                            }}
                            errorText={
                                this.state.errors.error1.displayError ?
                                this.state.errors.error1.text :
                                ''
                            }
                        />
                    </div>
                    <div>
                        <br/>
                        <RaisedButton primary={true} onClick={this.searchUsers.bind(this)}>
                            BUSCAR
                        </RaisedButton>
                    </div>
                    <div className="col-md-7 checkbox-input">
                        <br/>
                        <div className="row">
                            <div className="col-md-2">
                                <Checkbox
                                    checked={this.state.showSecondFilter}
                                    onCheck={(event) => {
                                        const { errors } = this.state;
                                        errors.error2.displayError = false;
                                        this.setState(
                                            {
                                                showSecondFilter: event.target.checked,
                                                userFilter2: '',
                                                errors,
                                                resultadoComparacion: ''
                                            })
                                        }
                                    }
                                >
                                </Checkbox>
                            </div>
                            <div className="col-md-7">
                                <p>Comparar con otro usuario </p>
                            </div>
                        </div>
                    </div>
                </div>
                {this.displaySecondFilter()}
                <Table>
                    <TableBody displayRowCheckbox={false}>
                        {
                            Object.values(this.state.infoUsuarios).map((info, index) => (
                                <TableRow key={index}>
                                    <TableRowColumn>{info.name}</TableRowColumn>
                                    <TableRowColumn>{info.value}</TableRowColumn>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
            </div>
        )
    }
}
