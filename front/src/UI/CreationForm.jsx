import React from "react";
import './UI.css'

const CreationForm = ({children}) => {
    
    return (
        <div className="creationForm">
            <div className="creationForm_content">
                {children}
            </div>
        </div>
    )
};

export default CreationForm;