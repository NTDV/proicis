import axios from 'axios';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import consts from './../consts.json';
import ClipboardButton from './ClipboardButton';

const ConnectTelegram = () => {

    let reduxDispatch = useDispatch();
    
    const [connectTelegCode, setConnectTelegCode] = useState(-1);
    const [login, setLogin] = useState('');
    

    function connectTG() {
        reduxDispatch({type: "server/waiting"});
        axios.post(consts.qlendpoint, {
            query: 
            `
            query {
                generateSecretCode
                getMyLogin
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
            reduxDispatch({type: "server/gotResponse"});
            if (e.data.errors === undefined) {
              setConnectTelegCode(e.data.data.generateSecretCode);
              setLogin(e.data.data.getMyLogin)
            }
            else {
              reduxDispatch({type: "server/error", payload: e.data.errors[0].message});
            }
          })
          .catch((e) => {
            reduxDispatch({type: "server/gotResponse"});
          });
    }

    return (
        <div>
            {connectTelegCode === -1 ?
            <button onClick={connectTG}>Привязать телеграм</button> : 
            <p>Личный код (действует 15 минут): {connectTelegCode}
                <br/>
                Скопировать команду для бота: <br/>
                <ClipboardButton text={'/bind ' + connectTelegCode + ' ' + login}/>
            </p>}
        </div>
    );
};

export default ConnectTelegram;