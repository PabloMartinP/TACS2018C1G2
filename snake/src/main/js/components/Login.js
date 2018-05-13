import React, { Component } from 'react';
import './Login.css';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      error: '',
      usernameError: '',
      passwordError: ''
    };
  }

  handleOnInputChange(event) {
    this.setState({[event.target.name]: event.target.value, error: '',
      [event.target.name + 'Error']: ''});
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
          <TextField
              hintText="Contraseña"
              style={{width: "100%"}}
              onChange={this.handleOnInputChange.bind(this)}
              name="password"
              errorText={this.state.passwordError}
              type="password"
          /><br />
        </div>
        <div className="mb-20">
          <RaisedButton label="Ingresar" primary={true} type="submit"/>
        </div>
        <div className="row col-sm-5 col-md-6 col-centered">
          <div className="col-md-6">
              <FlatButton label="Olvidaste tu contraseña" primary={true} />
          </div>
          <div className="col-md-6">
              <FlatButton label="Registrar" primary={true} 
              style={{alignSelf: "flex-end", position: "absolute"}}/>
          </div>
        </div>
      </div>
    );
  }
}

export default Login;
