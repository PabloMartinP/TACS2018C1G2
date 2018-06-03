const React = require('react');
const ReactDOM = require('react-dom');
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Portfolio from './components/Portfolio/Portfolio.jsx';
import Administracion from './components/Administracion/Administracion.jsx';
import './App.css';
import Header from './components/Header/Header.jsx';
import Login from './components/Login/Login.jsx';

class App extends React.Component {
    getComponent() {
        console.log(this.props.reactComponent);
        switch (this.props.reactComponent) {
            case 'portfolio':
                return (
                    <div className="App">
                        <Header userName={'Chester'}/>
                        <Portfolio usuarioId={1}/>
                    </div>
                );
            case 'admin':
                return (
                    <div className="App">
                        <Header userName={'Chester'}/>
                        <Administracion/>
                    </div>
                );
            default:
                return <Login/>;
        }
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