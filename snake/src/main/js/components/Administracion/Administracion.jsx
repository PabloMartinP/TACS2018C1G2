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
import {Usuario} from '../../models/Usuario';
import InfoDeUsuario from "../InfoDeUsuario/InfoDeUsuario.jsx";

export default class Administracion extends Component {
    constructor(props) {
        super(props);
        this.state = {
            usuarioBuscado: null,
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
            comparadorDeUsuariosHabilitado: false,
            nombreDeUsuarioABuscar: '',
            nombreDeUsuarioAComparar: '',
            errores: {
                errorEnCampoDeBusqueda: {
                    text: 'Debe completar este campo',
                    displayError: false
                },
                errorEnCampoDeComparacion: {
                    text: 'Debe completar ambos campos',
                    displayError: false
                }
            },
            resultadoComparacion: '',
            disableCompareButton: false
        };
    }

    actualizarInfoTxs() {
        SnakeRestAPI.obtenerCantidadDeTransacciones(
            CantidadDeTxs.formatCantTxs(this.state.cantTransacciones))
            .then(cantidades => this.setState({cantTransacciones: cantidades}));
    }


    displaySecondFilter() {
        if (this.state.comparadorDeUsuariosHabilitado) {
            return (
                <div className="row mb-20">
                    <div className="input col-md-3">
                        <br/>
                        <TextField
                            hintText="Usuario"
                            style={{width: "90%", marginLeft: "10px"}}
                            onChange={(e) => {
                                const {errores} = this.state;
                                errores.errorEnCampoDeComparacion.displayError = false;
                                this.setState({nombreDeUsuarioAComparar: e.target.value});
                            }}
                            errorText={
                                this.state.errores.errorEnCampoDeComparacion.displayError ?
                                    this.state.errores.errorEnCampoDeComparacion.text :
                                    ''
                            }
                        />
                    </div>
                    <div>
                        <br/>
                        <RaisedButton primary={true} onClick={this.compareUsers.bind(this)}
                                      disabled={this.state.disableCompareButton}>
                            COMPARAR
                        </RaisedButton>
                    </div>
                    <div className="comparisson-result">
                        <br/>
                        <p>{this.state.resultadoComparacion}</p>
                    </div>
                </div>
            );
        }
        return '';
    }

    searchUsers() {
        if (this.state.nombreDeUsuarioABuscar === '') {
            const {errores} = this.state;
            errores.errorEnCampoDeBusqueda.text = 'Debe completar este campo';
            errores.errorEnCampoDeBusqueda.displayError = true;
            this.setState({errors: errores});
            return;
        }
        SnakeRestAPI.obtenerUsuarioPorUsername(this.state.nombreDeUsuarioABuscar)
            .then(usuario => this.setState({usuarioBuscado: usuario}))
            .catch(() => {
                this.state.errores.errorEnCampoDeBusqueda = {text: 'Usuario no encontrado', displayError: true};
                this.setState({usuarioBuscado: null});
            });
    }

    compareUsers() {
        const {errores} = this.state;
        if (this.state.nombreDeUsuarioABuscar === '' || this.state.nombreDeUsuarioAComparar === '') {
            errores.errorEnCampoDeComparacion.text = 'Debe completar ambos campos';
            errores.errorEnCampoDeComparacion.displayError = true;
            this.setState({errors: errores});
            return;
        }
        this.setState({disableCompareButton: true});
        SnakeRestAPI.compararUsuarios([this.state.nombreDeUsuarioABuscar, this.state.nombreDeUsuarioAComparar])
            .then(usuariosPortfolios => {
                if (usuariosPortfolios[0] === '') {
                    errores.errorEnCampoDeBusqueda.text = 'El usuario ingresado no existe';
                    errores.errorEnCampoDeBusqueda.displayError = true;
                    this.setState({errors: errores, disableCompareButton: false});
                    return;
                } else if (usuariosPortfolios[1] === '') {
                    errores.errorEnCampoDeComparacion.text = 'El usuario ingresado no existe';
                    errores.errorEnCampoDeComparacion.displayError = true;
                    this.setState({errors: errores, disableCompareButton: false});
                    return;
                }
                SnakeRestAPI.obtenerCotizador()
                    .then(cotizador => {
                        this.setState({
                            resultadoComparacion: Usuario.obtenerUsuarioConMayorCapital(
                                cotizador, usuariosPortfolios, this.state.nombreDeUsuarioABuscar,
                                this.state.nombreDeUsuarioAComparar),
                            disableCompareButton: false
                        })
                    });
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
                                const {errores} = this.state;
                                errores.errorEnCampoDeBusqueda.displayError = false;
                                errores.errorEnCampoDeComparacion.displayError = false;
                                this.setState({nombreDeUsuarioABuscar: e.target.value, errors: errores})
                            }}
                            errorText={
                                this.state.errores.errorEnCampoDeBusqueda.displayError ?
                                    this.state.errores.errorEnCampoDeBusqueda.text :
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
                                    checked={this.state.comparadorDeUsuariosHabilitado}
                                    onCheck={(event) => {
                                        const {errores} = this.state;
                                        errores.errorEnCampoDeComparacion.displayError = false;
                                        this.setState(
                                            {
                                                comparadorDeUsuariosHabilitado: event.target.checked,
                                                nombreDeUsuarioAComparar: '',
                                                errors: errores,
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
                { this.state.usuarioBuscado ? <InfoDeUsuario usuario={this.state.usuarioBuscado}/> : null }
            </div>
        )
    }
}
