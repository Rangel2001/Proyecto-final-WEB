import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Cars = () => {
    const [cars, setCars] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8088/api/v1/cars')
            .then(response => {
                setCars(response.data);
            })
            .catch(error => {
                console.error('Error fetching cars:', error);
            });
    }, []);

    return (
        <div>
            <h2>Cars</h2>
            <ul>
                {cars.map(car => (
                    <li key={car.id}>
                        <strong>Model:</strong> {car.model}, <strong>Location:</strong> {car.location}, <strong>Price:</strong> {car.price}, <strong>startDate:</strong> {car.startDate}, <strong>endDate:</strong> {car.endDate}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Cars;
