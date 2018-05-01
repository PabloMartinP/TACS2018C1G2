import React, { Component } from 'react'
// import Header from './components/Header'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
// import Portfolio from './components/Portfolio'
import './App.css'
import Login from './components/Login';


class App extends Component {
    constructor(props) {
        super(props)
        this.state = { bitcoin: null, otraCosa: 1 }
    }

    componentDidMount() {
        setTimeout(() => {
            fetch('https://api.coinmarketcap.com/v1/ticker/bitcoin/')
                .then(response => response.json())
                .then(result => this.setState({ bitcoin: result[0] }))
        }, 3000);
    }


    render() {
        return (
            <MuiThemeProvider>
                <Login />
              {/* <div className="App">
                <Header userName="Snake"/>
                <Portfolio />
              </div> */}
            </MuiThemeProvider>
        );
    }
}



export default App;
