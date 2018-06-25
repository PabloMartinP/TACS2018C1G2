import React, { Component } from 'react';
import './Login.css';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
// import {SnakeRestApi} from '../../models/SnakeRestAPI';

class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            passwordConfirm: '',
            telegramId: '',
            error: '',
            usernameError: '',
            passwordError: '',
            passwordConfirmError: '',
            telegramIdError: ''
        };
    }

    handleOnInputChange(event) {
        this.setState({[event.target.name]: event.target.value, error: '',
            [event.target.name + 'Error']: ''});
    }

    registerUser() {
        const { username, password, telegramId, passwordConfirm } = this.state;
        let emptyfield = false;
        ['username', 'password', 'telegramId', 'passwordConfirm'].forEach(key => {
            if (this.state[key].length === 0) {
                this.setState({[key + 'Error']: 'Debe completar este campo'})
                emptyfield = true;
            }
        });
        if (emptyfield) {
            return;
        }
        if (password !== passwordConfirm) {
            this.setState({passwordConfirmError: 'Las contraseñas deben coincidir'});
            return;
        }
        if (!parseInt(telegramId)) {
            this.setState({telegramIdError: 'Este campo debe ser númerico'});
            return;
        }
        // SnakeRestAPI.registrarUsuario({username, password});
        fetch('/api/usuarios', {
            method: 'POST',
            credentials: "same-origin",
            body: JSON.stringify({ username, password, telegramId }),
            headers: {'Content-Type': 'application/json'}
        })
        .then(r => window.location.replace("/login"))
        .catch(e => this.setState.error("El nombre de usuario ya existe"));
    }

    renderSubmitButton(register) {
        if (register) {
            return (
                <div className="mb-20">
                    <RaisedButton onClick={this.registerUser.bind(this)} label="Registrar" primary={true}/>
                </div>
            );
        }
        return (
            <div className="mb-20">
                <RaisedButton label="Ingresar" primary={true} type="submit"/>
            </div>
        );
    }

    renderLoginButtons(register) {
        if (!register) {
            return (
                <div className="row col-sm-5 col-md-6 col-centered">
                    <div className="col-md-6">
                        <FlatButton label="Olvidaste tu contraseña" primary={true} />
                    </div>
                    <div className="col-md-6">
                        <a href="/register">
                            <FlatButton label="Registrar" primary={true}
                                style={{alignSelf: "flex-end", position: "absolute"}}/>
                        </a>
                    </div>
                </div>
            );
        }
        return '';
    }

    // login(event) {
    //   const errorMessage = "Debe completar este campo";
    //   ['username', 'password'].map(word => {
    //     if (this.state[word].length === 0) {
    //       this.setState({[word + 'Error']: errorMessage});
    //     }
    //     return true;
    //   });
    //   const self = this;
    //   try {
    //     fetch('http://localhost:8080/login/authentication', {mode: 'cors'})
    //       .then(response => response.json())
    //       .then(userValidatedFromServer => {
    //         if (!userValidatedFromServer) {
    //           self.setState({error: 'Usuario o contraseña incorrecta'});
    //         } else {
    //           window.location.replace("http://localhost:8080");
    //         }
    //       });
    //   } catch(exception) {
    //     self.setState({error: 'Error del servidor'});
    //   }
    // }

    render() {
        const { register } = this.props;
        return (
                <div className="App">
                    <header className="App-header mb-20">
                        <img src="/public/logo2.gif" className="logo" alt="logo" />
                        <h1 className="App-title">SNAKE</h1>
                    </header>
                    <div className="mb-20 col-sm-5 col-6 col-centered">
                        <p className="error">{this.state.error}</p>
                        <TextField
                            style={{width: "100%"}}
                            hintText="Usuario"
                            onChange={this.handleOnInputChange.bind(this)}
                            name="username"
                            errorText={this.state.usernameError}
                            /><br />
                        { register ?
                            (
                                <TextField
                                    style={{width: "100%"}}
                                    hintText="Clave para Telegram"
                                    onChange={this.handleOnInputChange.bind(this)}
                                    name="telegramId"
                                    errorText={this.state.telegramIdError}
                                    />
                            )
                            : ''
                        }
                        <br />
                        <TextField
                            hintText="Contraseña"
                            style={{width: "100%"}}
                            onChange={this.handleOnInputChange.bind(this)}
                            name="password"
                            errorText={this.state.passwordError}
                            type="password"
                            /><br />
                        { register ?
                            (
                                <TextField
                                    hintText="Confirmar Contraseña"
                                    style={{width: "100%"}}
                                    onChange={this.handleOnInputChange.bind(this)}
                                    name="passwordConfirm"
                                    errorText={this.state.passwordConfirmError}
                                    type="password"
                                    />
                            )
                            : ''
                        }
                        <br />
                    </div>
                    {this.renderSubmitButton(register)}
                    {this.renderLoginButtons(register)}
                </div>
                );
    }
}

export default Login;
