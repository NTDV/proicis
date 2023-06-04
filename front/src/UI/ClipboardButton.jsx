import React from 'react';

const ClipboardButton = ({text}) => {

    function copyToClipboard() {
        navigator.clipboard.writeText(text).then().catch();
    }

    return (
        <button onClick={copyToClipboard}>
            {text} 📋
        </button>
    );
};

export default ClipboardButton;