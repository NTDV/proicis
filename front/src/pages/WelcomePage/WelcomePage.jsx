import React from "react";
import LoginForm from "./loginForm";
import './css/welcome.css';

const WelcomePage = (props) => {
    return (
        <div className="login">
            <LoginForm whoAmI={props.whoAmI} endpoint={props.endpoint}/>
        </div>
    )
};

export default WelcomePage;