import React, { useState } from "react";
import "./css/ModerParticipants.css";
import CloseButton from "../../../../UI/CloseButton";
import InputField from "../../../../UI/InputField";
import axios from "axios";
import consts from './../../../../consts.json';

const CreateParticipantForm = ({closeFunc}) => {

    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [secondName, setSecondName] = useState("");
    const [thirdName, setThirdName] = useState("");
    const [urlVK, setUrlVK] = useState("");
    const [urlTG, setUrlTG] = useState("");
    const [group, setGroup] = useState("");
    const [organization, setOrganization] = useState("");
    

    function createParticipant_send() {
        axios.post(consts.qlendpoint, {
            query: 
            `
            mutation {
                registerParticipant (credentialsInput: {
                  login: "` + login + `"
                  password: "` + password + `"
                }, userInput: {
                  firstName: "` + firstName + `"
                  secondName: "` + secondName + `"
                  thirdName: "` + thirdName + `"
                  urlVkontakte: "` + urlVK + `"
                  urlTelegram: "` + urlTG + `"
                  group: "` + group + `"
                  organization: "` + organization + `"
                })
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
            
          })
          .catch((e) => {
            console.log(e);
          });
    }
    
    return (
        <form className="create_participant_form">
            <div style={{display: 'flex', flexDirection: 'row', justifyContent: "space-between", alignItems: "center"}}>
                <h3>Регистрация участника</h3>
                <CloseButton closeFunc={closeFunc}/>
            </div>
            Фамилия
            <InputField stateName="Иванов" required={true}/>
            Имя
            <InputField stateName="Иван" required={true}/>
            Отчество
            <InputField stateName="Иванович" />
            Группа
            <InputField stateName="Б22-504" required={true}/>
            Vk
            <InputField stateName="vk.com/ivanov337123" />
            Telegram
            <InputField stateName="@vanya337123" />
            Организация
            <InputField stateName="НИЯУ МИФИ" />
            <button onClick={createParticipant_send}>Создать участника!</button>
        </form>
    )
};

//query: "mutation {\n  registerParticipant (credentialsInput: {\n    login: \"login\"\n    password: \"password\"\n  }, userInput: {\n    firstName: String!\n    secondName: String!\n    thirdName: String!\n    urlVkontakte: String!\n    urlTelegram: String!\n    group: String!\n    organization: String!\n  })\n}"
export default CreateParticipantForm;