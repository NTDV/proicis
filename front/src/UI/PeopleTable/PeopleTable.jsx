import React from 'react';
import './PeopleTable.css';

const PeopleTable = ({users, selected, setSelected}) => {

    if (users.length === 0) {
        return (<div></div>)
    }

    function handleCheck(e) {
        if (e.target.checked) {
            setSelected([...selected, e.target.name]);
        }
        else {
            setSelected(selected.filter((id) => (id !== e.target.name)))
        }
    }

    const keys = Object.keys(users[0]);

    return (
        <table className='PeopleTable'>
            <thead>
                <tr>
                    <th></th>
                    {keys.map((key)=> <th scope="col" key={key}>{
                    key === 'firstName' ? 'Имя' :
                    key === 'secondName' ? 'Фамилия' :
                    key === 'thirdName' ? 'Отчество' :
                    key === 'telegramUsername' ? 'Телеграм' :
                    key === 'state' ? 'Статус' :
                    key
                    }</th>)}
                </tr>
            </thead>
            <tbody>
                {users.map((user) => 
                <tr key={user.id}>
                    <td><input type="checkbox" name={user.id} onClick={handleCheck}></input></td>
                    {keys.map((key) => <td key={user.id + "." + key}> {user[key]}</td>)}
                </tr>)}
            </tbody>
        </table>
    );
};

export default PeopleTable;