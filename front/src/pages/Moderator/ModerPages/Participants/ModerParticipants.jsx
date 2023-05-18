import React from "react";
import Participants from "./Participants";
import Header from "../../../../UI/Header";
import ModerCards from "../Main/ModerCards";

const ModerParticipants = (props) => {
    
    return (
        <div className="moderParticipants">
            <Header user={props.user} toggle_disabled={false}>
                <ModerCards/>
            </Header>
            <Participants/>
        </div>
    )
};

export default ModerParticipants;