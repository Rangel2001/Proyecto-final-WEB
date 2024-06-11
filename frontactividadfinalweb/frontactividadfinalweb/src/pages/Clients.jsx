import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Clients = () => {
    const [clients, setClients] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8088/api/v1/clients')
            .then(response => {
                setClients(response.data);
            })
            .catch(error => {
                console.error('Error fetching clients:', error);
            });
    }, []);

    return (
        <div>
            <h2>Clients</h2>
            <ul>
                {clients.map(client => (
                    <li key={client.id}>
                        <strong>Name:</strong> {client.name}, <strong>Last Name:</strong> {client.lastName}, <strong>Cedula:</strong> {client.cedula}, <strong>Address:</strong> {client.address}, <strong>Phone:</strong> {client.phone}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Clients;
