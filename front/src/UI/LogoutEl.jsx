import React from "react";
import './UI.css';
import logoutSVG from './../images/logout.svg';
import axios from "axios";
import consts from './../consts.json';

const logoutEl = () => {

    function logout() {
        axios.post(consts.endpoint + "logout", { },
        {
          headers: {
            "content-type": "application/x-www-form-urlencoded"
          },
          withCredentials: true
        })
        .then((e) => {window.location.reload();})
        .catch((e) => {console.log(e)});
      }

    return (
        <div className='logout' onClick={logout}>
            <img src={logoutSVG} alt="logout" style={{height: '25px'}}/>
        </div>
    )
};

export default logoutEl;