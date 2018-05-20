import React, {Component} from 'react'
import TextField from 'material-ui/TextField';
import '../Portfolio/Portfolio.css';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import ContentAdd from 'material-ui/svg-icons/content/add';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import {SnakeRestAPI} from '../../models/SnakeRestAPI';
import {Compra, Venta} from '../../models/Transaccion';

export default class AgregarTransaccion extends Component {
    constructor(props) {
        super(props);
        this.state = {transaccion: '', moneda: '', cantidad: ''}
    }

    isDisabled() {
        return [this.state.transaccion, this.state.moneda, this.state.cantidad].some(value => !value);
    }

    clearState() {
        this.setState({transaccion: '', moneda: '', cantidad: ''});
    }

    createNewTransaction() {
        const transaccion = this.state.transaccion.crear(this.state.moneda, this.state.cantidad);
        SnakeRestAPI.registrarTransaccion(transaccion)
            .then(() => {
                this.clearState();
                this.props.actualizarInfoPortfolio();
            })
    }

    render() {
        return (
            <div className="row mb-20">
                <div className="input col-md-3">
                    <SelectField
                        floatingLabelText="Tipo de Transacción"
                        value={this.state.transaccion}
                        onChange={(event, index, value) => this.setState({transaccion: value})} >
                        <MenuItem value={Compra} primaryText="Compra"/>
                        <MenuItem value={Venta} primaryText="Venta"/>
                    </SelectField>
                </div>
                <div className="input col-md-3">
                    <SelectField
                        floatingLabelText="Moneda"
                        value={this.state.moneda}
                        onChange={(event, index, value) => this.setState({moneda: value})}>
                        {this.props.cotizador.listarMonedasCotizadas().map(moneda => <MenuItem key={moneda} value={moneda} primaryText={moneda}/>)}
                    </SelectField>
                </div>
                <div className="input col-md-3">
                    <br/>
                    $<TextField hintText="Valor"
                                style={{width: "90%", marginLeft: "10px"}}
                                value={this.state.cantidad}
                                onChange={event => this.setState({cantidad: event.target.value})}/>
                </div>
                <div>
                    <br/>
                    <FloatingActionButton
                        onClick={() => this.createNewTransaction()}
                        mini={true}
                        disabled={this.isDisabled()}
                        style={{boxShadow: "0", marginLeft: "20px"}}>
                        <ContentAdd/>
                    </FloatingActionButton>
                </div>
            </div>
        )
    }
}
