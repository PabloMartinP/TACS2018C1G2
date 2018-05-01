import React from 'react';
import FontIcon from 'material-ui/FontIcon';
import {red900} from 'material-ui/styles/colors';
import './Header.css';

export default ({userName, message}) => (
    <div className="header">
      <FontIcon className="material-icons" color={red900} title="Logout">power_settings_new</FontIcon>
      <p>{(message || 'Bienvenido') + ' ' + userName + '!'}</p>
    </div>
)
