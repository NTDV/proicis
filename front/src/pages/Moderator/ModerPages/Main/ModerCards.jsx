import React from "react";
import './../../css/moder.css'
import ModerCard from "./ModerCard";
import { Link } from "react-router-dom";
import consts from './../../../../consts.json';


const ModerCards = (props) => {
    return (
        <div className="grid_cards">
            <Link to={consts.siteDomain + "/participants"} style={{textDecoration: "none", outline: "0"}}>
                <ModerCard text="Участники"/>
            </Link>
            <Link to={consts.siteDomain + "/"} style={{textDecoration: "none", outline: "0"}}>
                <ModerCard text="Менторы"/>
            </Link>
            <Link to={consts.siteDomain + "/"} style={{textDecoration: "none", outline: "0"}}>
                <ModerCard text="Команды"/>
            </Link>
            <Link to={consts.siteDomain + "/"} style={{textDecoration: "none", outline: "0"}}>
                <ModerCard text="Темы"/>
            </Link>
            <Link to={consts.siteDomain + "/"} style={{textDecoration: "none", outline: "0"}}>
                <ModerCard text="Отчеты"/>
            </Link>
        </div>
    )
};

export default ModerCards;