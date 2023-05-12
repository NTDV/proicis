import React from "react";
import LoginForm from "./loginForm";
import './css/welcome.css';

const WelcomePage = (props) => {
    return (
        <div className="login_container">
            <LoginForm whoAmI={props.whoAmI} endpoint={props.endpoint} setSentRequest={props.setSentRequest}/>
        </div>
    )
};

export default WelcomePage;