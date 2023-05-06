import React from "react";
import './../../css/moder.css'
import ModerCard from "./ModerCard";

const ModerCards = (props) => {
    return (
        <div className="grid_cards">
            <ModerCard text="Участники"/>
            <ModerCard text="Менторы"/>
            <ModerCard text="Команды"/>
            <ModerCard text="Темы"/>
            <ModerCard text="Отчеты"/>
        </div>
    )
};

export default ModerCards;