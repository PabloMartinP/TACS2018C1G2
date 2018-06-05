const React = require('react');
const ReactDOM = require('react-dom');
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Portfolio from './components/Portfolio/Portfolio.jsx';
import Administracion from './components/Administracion/Administracion.jsx';
import './App.css';
import Header from './components/Header/Header.jsx';
import Login from './components/Login/Login.jsx';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '-'
        };
    }

    getComponent() {
        switch (this.props.reactComponent) {
            case 'portfolio':
                return (
                    <div className="App">
                        <Header userName={this.state.username}/>
                        <Portfolio/>
                    </div>
                );
            case 'admin':
                return (
                    <div className="App">
                        <Header userName={this.state.username}/>
                        <Administracion/>
                    </div>
                );
            default:
                return <Login/>;
        }
    }

    componentDidMount() {
        fetch('/api/usuarios/logueado', {credentials: "same-origin"})
            .then(respuesta => respuesta.json())
            .then(usuarioEnJson => {
                this.setState({username: usuarioEnJson.username});
                return usuarioEnJson;
            });
    }

    render() {
        return (
            <MuiThemeProvider>
                {this.getComponent()}
            </MuiThemeProvider>
        );
    }
}

ReactDOM.render(
    <App reactComponent={document.getElementById('app').getAttribute('name')}/>,
    document.getElementById('app')
);