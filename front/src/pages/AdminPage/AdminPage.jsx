import React from 'react';
import './admin.css';
import { useDispatch } from 'react-redux';

const AdminPage = () => {
    
    const reduxDispatch = useDispatch();

    function setRole(role) {
        reduxDispatch({type: "server/loggedIn", payload: role});
    }


    return (
        <div className='admin'>
            <p>Сегодня я буду</p>
            <button onClick={() => {setRole("Moderator")}}>Модератором</button>
            <button onClick={() => {setRole("Participant")}}>Участником</button>
            <button onClick={() => {setRole("Mentor")}}>Ментором</button>
        </div>
    );
};

export default AdminPage;