import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import Header from '../../UI/Header';
import Unconfirmed from './Unconfirmed/Unconfirmed';

const ParticipantPage = ({user}) => {

    return (
        <div className='participant'>
            <BrowserRouter>
                <Header user={user} toggle_disabled={false}/>
                {user.state !== "Confirmed" ?
                <Unconfirmed/> :
                <h1>There is nothing to see here, move along</h1>}
            </BrowserRouter>
        </div>
    );
};

export default ParticipantPage;