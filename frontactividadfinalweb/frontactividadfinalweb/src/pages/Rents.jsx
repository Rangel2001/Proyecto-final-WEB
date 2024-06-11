import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Rents = () => {
    const [rents, setRents] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8088/api/v1/rents')
            .then(response => {
                setRents(response.data);
            })
            .catch(error => {
                console.error('Error fetching rents:', error);
            });
    }, []);

    return (
        <div>
            <h2>Rents</h2>
            <ul>
                {rents.map(rent => (
                    <li key={rent.id}>
                        <strong>Car:</strong> {rent.car.model}, <strong>Client:</strong> {rent.client.name + " " + rent.client.lastName}, <strong>Start Date:</strong> {rent.car.startDate}, <strong>End Date:</strong> {rent.car.endDate}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Rents;
