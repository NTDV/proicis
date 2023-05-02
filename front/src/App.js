import React, { useEffect, useState } from 'react'
import WelcomePage from './pages/WelcomePage/WelcomePage';
import ConnectingPage from './pages/ConnectingPage/ConnectingPage';
import ModerPage from './pages/Moderator/ModerPage';
import './App.css'
import axios from 'axios';

function App() {

  const endpoint = 'https://charityradar.online/';
  const qlendpoint = 'https://charityradar.online/graphql';

  function log_in(e) {
    e.preventDefault();
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
      query: 
      `
      query 
      {  
        getMyRole 
        getMe {
          id
          firstName
          secondName
          thirdName
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
      setMyRole(e.data.data.getMyRole);
      setUser(e.data.data.getMe);
      setSentRequest(0);
    })
    .catch((e) => {
      console.log(e);
      setMyRole('None');
      setSentRequest(0);
    });
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
    .then((e) => {whoAmI()})
    .catch((e) => {console.log(e)});
  }

  const [myRole, setMyRole] = useState('None');
  const [sentRequest, setSentRequest] = useState(0);
  const [user, setUser] = useState({});

  useEffect(() => {
    setSentRequest(1);
    whoAmI();
  }, []);

  return (
    <div className="App">
      {
        myRole === 'None' ? (
        sentRequest === 0 ?
        <WelcomePage whoAmI={whoAmI} endpoint={endpoint}/> :
        <ConnectingPage/>
        ) :
        myRole === 'Moder' ?
        <ModerPage user={user}/> :
        <h1>Nothing</h1>
      }
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <button onClick={log_in}>LOGIN AS ADMIN</button>
      <button onClick={log_out}>LOGOUT</button>
    </div>
  );
}

export default App;