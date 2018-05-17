import React, {Component} from 'react'
        import TextField from 'material-ui/TextField';
import './Portfolio.css';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import ContentAdd from 'material-ui/svg-icons/content/add';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';

export default class AgregarTransaccion extends Component {
    constructor(props) {
        super(props)
        this.state = {type: '', coin: '', amount: '', monedas: []}
    }

    isDisabled() {
        return Object.values(this.state).some(value => value === '');
    }

    componentDidMount() {
        fetch('/api/monedas')
                .then(response => response.json())
                .then(result => this.setState({monedas: result}))
    }
    createNewTransaction() {
        fetch('/api/transacciones', {
            method: 'POST',
            body: JSON.stringify({monedaNombre: this.state.coin, cantidad: this.state.amount, tipo: this.state.type}),
            headers: {'Content-Type': 'application/json'}
        })
    }

    render() {
        return (
                <div className="row mb-20">
                    <div className="input col-md-3">
                        <SelectField
                            floatingLabelText="Tipo de Transacción"
                            value={this.state.type}
                            onChange={(event, index, value) => this.setState({type: value})}
                            >
                            <MenuItem value={'COMPRA'} primaryText="Compra"/>
                            <MenuItem value={'VENTA'} primaryText="Venta"/>
                        </SelectField>
                    </div>
                    <div className="input col-md-3">
                        <SelectField
                            floatingLabelText="Moneda"
                            value={this.state.coin}
                            onChange={(event, index, value) => this.setState({coin: value})}
                            >
                            { this.state.monedas.map(moneda => <MenuItem key={moneda.nombre} value={moneda.nombre} primaryText={moneda.nombre}/>)}
                        </SelectField>
                    </div>
                    <div className="input col-md-3">
                        <br/>
                        $<TextField hintText="Valor" style={{width: "90%", marginLeft: "10px"}}/>
                    </div>
                    <div>
                        <br/>
                        <FloatingActionButton
                            onClick={ () => this.createNewTransaction()}
                            mini={true}
                            style={{boxShadow: "0", marginLeft: "20px"}}>
                            <ContentAdd/>
                        </FloatingActionButton>
                    </div>
                </div>
                                )
                    }
                }
