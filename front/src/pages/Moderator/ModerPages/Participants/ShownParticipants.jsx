import React from 'react';

const ShownParticipants = ({users}) => {
    return (
        <div>
            {
                users.map((user) => <p key={user.id}>
                    {user.firstName + " " + user.secondName + " " + user.thirdName}
                </p>)
            }
        </div>
    );
};

export default ShownParticipants;