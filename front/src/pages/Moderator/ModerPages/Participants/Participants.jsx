import React from "react";
import './css/ModerParticipants.css';
import CreationForm from "../../../../UI/CreationForm";

const Participants = (props) => {

    return (
        <div>
            <div className="control_participants">
                <input></input>
                <button>Создать участника</button>
                <CreationForm>
                    1
                </CreationForm>
            </div>
        </div>
    )
};

export default Participants;