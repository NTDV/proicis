import React from 'react';
import './unconfirmed.css'
import ConnectTelegram from '../../../UI/ConnectTelegram';

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
                [
                <p style={{maxWidth: "30rem"}}>
                    Большая часть взаимодействия у вами будет выстраена при помощи уведомлений через
                    телеграм-бота. Чтобы они до вас доходили, необходимо привязать телеграм к аккаунту. 
                    <br/>
                    При нажатии на ссылку ниже будет сгенерирован код. Для привязки телеграма к аккаунту
                    необходимо подать этот код боту @proicisbot с командой /bind. Чтобы операция
                    выполнилась успешно, необходимо также подать с этой командой ваш логин. Пример:
                    <br/>
                    /bind 1234 my_login123
                    <br/>
                    Здесь 1234 - код, my_login123 - логин
                </p>, 
                <ConnectTelegram/>]}
            </div>
        </div>
    );
};

export default Unconfirmed;