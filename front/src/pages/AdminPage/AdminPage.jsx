import React from 'react';
import './admin.css';

const AdminPage = ({setRole}) => {

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