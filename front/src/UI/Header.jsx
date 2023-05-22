import React, { useState } from 'react';
import LogoutEl from './LogoutEl';
import MenuEl from './MenuEl';

const Header = ({toggle_disabled, user, children}) => {
    //Needs to be put inside a BrowserRouter element

    const [headerShown, setHeaderShown] = useState(0)

    function headerToggle() {
        setHeaderShown(1 - headerShown);
    }

    return (
        <header className="header">
            <div className="headerControls">
                <MenuEl callbackf={headerToggle} toggle_disabled={toggle_disabled}/>
                <div className="leavebtns">
                    <span>{user.secondName} {user.firstName[0]}. {user.thirdName[0]}.</span>
                    <LogoutEl/>
                </div>
            </div><div className="headerNavigation" style={(headerShown === 0) ? {display: 'none'} : {display: 'flex'}}>
                {children}
            </div>
        </header>
    );
};

export default Header;