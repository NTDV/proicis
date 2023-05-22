import React from 'react';
import './unconfirmed.css'
import ConfirmTelegram from '../../../UI/ConfirmTelegram';

const Unconfirmed = ({user}) => {
    return (
        <div className='unconfirmed'>
            <h2>Похоже, ваш профиль пока не подтвердил модератор</h2>
            <span>Пожалуйста, проверьте корректность ваших данных</span>
            <span>Если данные не совпадают, пожалуйста, сообщите модератору!</span>
            <div className='userdata'>
                <span>ID профиля: {user.id} </span>
                <span>Фамилия: {user.secondName} </span>
                <span>Имя: {user.firstName} </span>
                <span>Отчество: {user.thirdName} </span>
                <span>Группа: {user.group} </span>
                <span>Организация (для наставников): {user.organization} </span>
                {user.telegramUsername !== "" ?
                user.telegramUsername :
                [<a>Привязать телеграм</a>, 
                <ConfirmTelegram/>]}
            </div>
        </div>
    );
};

export default Unconfirmed;