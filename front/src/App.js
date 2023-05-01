import React, { useState } from 'react'
import WelcomePage from './pages/WelcomePage/WelcomePage';
import './App.css'
import axios from 'axios';

function App() {

  //  query: "{\n  getMyRole\n}"

  const endpoint = 'https://charityradar.online/';
  const qlendpoint = 'https://charityradar.online/graphql';

  function log_in() {
    console.log("Sent request");
    axios.post(endpoint + "login", {
      username: 'Admin',
      password: 'TbJDt27H@3U'
    },
    {
      headers: {
        "content-type": "application/x-www-form-urlencoded",
      },
      withCredentials: true
    })
    .then((e) => {console.log(e)})
    .catch((e) => {console.log(e)});
  }

  function whoAmI() {
    
    axios.post(qlendpoint, {
      query: "query {  getMyRole }"
    },
    {
      headers: {
        accept: "application/json",
        "content-type": "application/json",
        Authorization: "Basic QWRtaW46VGJKRHQyN0hAM1U="
      },
      withCredentials: true
    })
    .then((e) => {
      setMyRole(e.data.data.getMyRole);
      console.log(e.data.data.getMyRole);
    })
    .catch((e) => {console.log(e)});
  }

  
  function log_out() {
    console.log("Sent request2");
    axios.post(endpoint + "logout", { },
    {
      headers: {
        "content-type": "application/x-www-form-urlencoded"
      },
      withCredentials: true
    })
    .then((e) => {console.log(e)})
    .catch((e) => {console.log(e)});
  }

  const [myRole, setMyRole] = useState('None');

  return (
    <div className="App">
      {
        myRole === 'None' ?
        <WelcomePage/> :
        myRole === 'Moder' ?
        <h1>No site</h1> :
        <h1>Nothing</h1>
      }
      <button onClick={log_in}>LOGIN</button>
      <button onClick={whoAmI}>Request</button>
      <button onClick={log_out}>LOGOUT</button>
    </div>
  );
}

export default App;