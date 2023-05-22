import React from "react";
import ModerCards from "./ModerCards";
import Header from "../../../../UI/Header";

const ModerMain = (props) => {

    return (
        <div className="moder">
            <Header user={props.user} toggle_disabled={true}/>
            <ModerCards/>
        </div>
    )
};

export default ModerMain;