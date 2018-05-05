const React = require('react');
const ReactDOM = require('react-dom');
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Portfolio from './components/Portfolio';
import './App.css';
import Header from './components/Header';

class App extends React.Component {

	render() {
		return (
            <MuiThemeProvider>
                {/* <Login /> */}
              <div className="App">
                <Header userName="Snake"/>
                <Portfolio />
              </div>
            </MuiThemeProvider>
        );
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
);