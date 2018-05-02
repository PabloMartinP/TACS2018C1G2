import React from 'react';
import FontIcon from 'material-ui/FontIcon';
import {red900} from 'material-ui/styles/colors';
import AppBar from 'material-ui/AppBar';
import './Header.css';

export default ({userName, message}) => (
    <div className="header mb-20">
      <AppBar
        title={<span>{(message || 'Bienvenido') + ' ' + userName + '!'}</span>}
        iconElementLeft={<div className="icon-left">
          <FontIcon className="material-icons" color={red900} title="Logout">
            power_settings_new
          </FontIcon>
        </div>}
        style={{paddingLeft: "50px", paddingRight: "50px"}}
      />
    </div>
)
