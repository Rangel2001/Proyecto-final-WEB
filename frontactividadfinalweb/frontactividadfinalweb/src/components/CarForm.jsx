import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box } from '@mui/material';

const CarForm = () => {
    const [model, setModel] = useState('');
    const [location, setLocation] = useState('');
    const [price, setPrice] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        const carData = {
            model,
            location,
            price
        };

        try {
            await axios.post('http://localhost:8088/api/v1/cars', carData);
            alert('Car created successfully!');
        } catch (error) {
            console.error('Error creating car:', error);
            alert('Failed to create car.');
        }
    };

    return (
        <Box component="form" onSubmit={handleSubmit}>
            <h2>Create a New Car</h2>
            <TextField
                label="Model"
                value={model}
                onChange={(e) => setModel(e.target.value)}
                fullWidth
                margin="normal"
                required
            />
            <TextField
                label="Location"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
                fullWidth
                margin="normal"
                required
            />
            <TextField
                label="Price"
                type="number"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                fullWidth
                margin="normal"
                required
            />
            <Button type="submit" variant="contained" color="primary">Create Car</Button>
        </Box>
    );
};

export default CarForm;
