import React from "react";
import './UI.css'

const CreationForm = ({children, visible, setVisible}) => {
    
    return (
        <div className={"creationForm" + (visible ? " active" : "")}>
            <div className="creationForm_content">
                {children}
            </div>
        </div>
    )
};

export default CreationForm;