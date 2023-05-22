import React, { useState } from "react";
import './css/ModerParticipants.css';
import CreationForm from "../../../../UI/CreationForm";
import CreateParticipantForm from "./CreateParticipantForm";
import ShownParticipants from "./ShownParticipants";
import { useDispatch } from "react-redux";
import axios from "axios";
import consts from './../../../../consts.json';

const Participants = (props) => {

    const [createParticipantForm_visible, setCreateParticipantForm_visible] = useState(0);
    const [shownParticipants, setShownParticipants] = useState([{id: "1", firstName: "Maxim", secondName: "Kudrin", thirdName: "Vitalievitch"}]);
    const reduxDispatch = useDispatch();
    
    function getParticipantsNoTG() {
        reduxDispatch({type: "server/waiting"});
        axios.post(consts.qlendpoint, {
            query: 
            `
            query 
            {  
                getAllParticipants {
                    id
                    firstName
                    secondName
                    thirdName
                    telegramUsername
                }
            }
            `
          },
          {
            headers: {
              accept: "application/json",
              "content-type": "application/json",
            },
            withCredentials: true
          })
          .then((e) => {
            reduxDispatch({type: "server/gotResponse"})
            if (e.data.errors !== undefined) {
              reduxDispatch({type: "server/error", payload: e.data.errors[0].message})
            }
            else {
              let r = e.data.data.getAllParticipants.filter((user) => !(user.telegramUsername.includes("http") || user.telegramUsername.includes("@")));
              console.log(r)
              setShownParticipants(r);
            }
          })
          .catch((e) => {
            reduxDispatch({type: "server/gotResponse"});
          });
    }

    return (
        <div>
            <div className="control_participants">
                <input></input>
                <button onClick={() => {setCreateParticipantForm_visible(1)}}>Создать участника</button>
                <button onClick={getParticipantsNoTG}>Получить участников без тг</button>
                
                <CreationForm visible={createParticipantForm_visible} setVisible={setCreateParticipantForm_visible}>
                    <CreateParticipantForm closeFunc={()=>{setCreateParticipantForm_visible(0)}}/>
                </CreationForm>

                <ShownParticipants users={shownParticipants}/>
            </div>
        </div>
    )
};

export default Participants;