import React from "react";
import ModerCards from "./ModerCards";
import LogoutEl from "./../../../../UI/LogoutEl";

const ModerMain = (props) => {
    return (
        <div className="moder">
            <header>
                <span>{props.user.firstName} {props.user.secondName[0]}. {props.user.thirdName[0]}.</span>
                <LogoutEl/>
            </header>
            <ModerCards/>
        </div>
    )
};

export default ModerMain;