import React, { Component } from 'react'
import TextField from 'material-ui/TextField';
import './Portfolio.css';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import ContentAdd from 'material-ui/svg-icons/content/add';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';

export default class AgregarTransaccion extends Component {
    constructor(props) {
        super(props)
        this.state = {type: '', coin: '', amount: ''}
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
                        <MenuItem value={'compra'} primaryText="Compra" />
                        <MenuItem value={'venta'} primaryText="Venta" />
                    </SelectField>
                </div>
                <div className="input col-md-3">
                    <SelectField
												floatingLabelText="Moneda"
												value={this.state.coin}
          							onChange={(event, index, value) => this.setState({coin: value})}
                    >
                        <MenuItem value={'bitcoin'} primaryText="Bitcoin" />
                        <MenuItem value={'etherum'} primaryText="Etherum" />
                        <MenuItem value={'otra'} primaryText="Otra" />
                    </SelectField>
                </div>
                <div className="input col-md-3">
										<br/>
                    $<TextField
                        hintText="Valor"
                        style={{width: "90%", marginLeft: "10px"}}
                    />
                </div>
								<div>
									<br/>
									<FloatingActionButton mini={true} style={{boxShadow: "0", marginLeft: "20px"}}>
											<ContentAdd />
									</FloatingActionButton>
								</div>
            </div>
             
        )
    }
}
