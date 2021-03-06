import React, {Component} from 'react'
import {
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';
import {List, ListItem} from 'material-ui/List';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import RaisedButton from 'material-ui/RaisedButton';
import ListadoTransacciones from '../ListadoTransacciones/ListadoTransacciones.jsx';
import FontIcon from 'material-ui/FontIcon';
import '../Portfolio/Portfolio.css';

export default class Billetera extends Component {

    constructor(props) {
        super(props);
        this.state = { modalOpen: false, difference: null };
    }

    toggle() {
        this.setState({modalOpen: !this.state.modalOpen});
    }

   componentDidMount() {
       this.props.usuario.obtenerBalanceDe(this.props.moneda)
           .then(difference => this.setState({ difference }));
   }

    render() {
        const actions = [
            <FlatButton
                label="Cerrar"
                primary={true}
                onClick={this.toggle.bind(this)}
            />
        ];
        return (
            <TableRow>
                <TableRowColumn>{this.props.moneda}
                </TableRowColumn>
                <TableRowColumn>{this.props.cantidad}</TableRowColumn>
                <TableRowColumn>USD {this.props.cotizacion ? this.props.cotizacion : '-'}</TableRowColumn>
                <TableRowColumn>USD {this.state.difference ? this.state.difference : '-'}</TableRowColumn>
                <TableRowColumn>
                    <RaisedButton
                        label={
                            <FontIcon className="material-icons" title="Ver transacciones" color="white"
                                      style={{marginTop: "5px"}}>
                                monetization_on
                            </FontIcon>
                        }
                        onClick={this.toggle.bind(this)}
                        primary={true}
                        style={{width: "60px", minWidth: "0px"}}
                    />
                    <Dialog
                        title={
                            <div>
                                <h4>Transacciones</h4>
                                <h6 className="subtitle">
                                    {this.props.moneda.toUpperCase() + ' | USD ' +
                                    (this.props.cotizacion ? this.props.cotizacion : '-')}
                                </h6>
                            </div>
                        }
                        modal={false}
                        actions={actions}
                        open={this.state.modalOpen}
                        autoScrollBodyContent={true}
                        onRequestClose={this.toggle.bind(this)}
                    >
                        <ListadoTransacciones usuario={this.props.usuario} moneda={this.props.moneda}/>
                    </Dialog>
                </TableRowColumn>
            </TableRow>
        );
    }
}
