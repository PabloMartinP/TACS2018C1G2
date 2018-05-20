const React = require('react');
const ReactDOM = require('react-dom');
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Portfolio from './components/Portfolio';
import './App.css';
import Header from './components/Header';
import Login from './components/Login';

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