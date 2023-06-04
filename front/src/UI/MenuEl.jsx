import React from "react";
import './UI.css';
import menuSVG from './../images/menu.svg';
import { Link } from "react-router-dom";
import consts from './../consts.json';

const MenuEl = (props) => {
    
    return (
        <div className="menu_container">
            <button className="menu_button" onClick={props.callbackf} disabled={props.toggle_disabled}>
                <img src={menuSVG} alt="menu"/>
            </button>
            <Link to={consts.siteDomain} style={{textDecoration: "none", outline: "0"}}>
                <div className="home_button" style={(props.toggle_disabled) ? {display: 'none'} : {}}>
                    На главную
                </div>
            </Link>
        </div>
    )
};

export default MenuEl;