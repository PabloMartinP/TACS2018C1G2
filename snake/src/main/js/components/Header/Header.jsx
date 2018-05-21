import React from 'react';
import FontIcon from 'material-ui/FontIcon';
import {white} from 'material-ui/styles/colors';
import AppBar from 'material-ui/AppBar';
import './Header.css';

export default ({userName, message}) => (
    <div className="header mb-20">
      <AppBar
        title={<span>{(message || 'Bienvenido') + ' ' + userName + '!'}</span>}
        iconElementLeft={<div className="icon-left">
          <a onClick={() => document.getElementById('logoutForm').submit()} style={{cursor: "pointer"}}>
            <FontIcon className="material-icons" color={white} title="Logout">
              power_settings_new
            </FontIcon>
          </a>
        </div>}
        style={{paddingLeft: "50px", paddingRight: "50px"}}
      />
    </div>
)
