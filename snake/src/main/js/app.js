const React = require('react');
const ReactDOM = require('react-dom');
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Portfolio from './components/Portfolio/Portfolio.jsx';
import './App.css';
import Header from './components/Header/Header.jsx';
import Login from './components/Login/Login.jsx';

class App extends React.Component {
    getComponent() {
        switch (this.props.reactComponent) {
            case 'portfolio':
                return (
                    <div className="App">
                        <Header userName={'Chester'}/>
                        <Portfolio usuarioId={1}/>
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