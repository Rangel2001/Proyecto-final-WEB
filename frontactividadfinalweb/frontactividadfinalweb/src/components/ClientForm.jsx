import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box } from '@mui/material';

const ClientForm = () => {
    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');
    const [cedula, setCedula] = useState('');
    const [address, setAddress] = useState('');
    const [phone, setPhone] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        const clientData = {
            name,
            lastName,
            cedula,
            address,
            phone,
        };

        try {
            await axios.post('http://localhost:8088/api/v1/clients', clientData);
            alert('Client created successfully!');
        } catch (error) {
            console.error('Error creating client:', error);
            alert('Failed to create client.');
        }
    };

    return (
        <Box component="form" onSubmit={handleSubmit}>
            <h2>Create a New Client</h2>
            <TextField
                label="Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                fullWidth
                margin="normal"
                required
            />
            <TextField
                label="Last Name"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                fullWidth
                margin="normal"
                required
            />
            <TextField
                label="Cedula"
                value={cedula}
                onChange={(e) => setCedula(e.target.value)}
                fullWidth
                margin="normal"
                required
            />
            <TextField
                label="Address"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                fullWidth
                margin="normal"
                required
            />
            <TextField
                label="Phone"
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
                fullWidth
                margin="normal"
                required
            />
            <Button type="submit" variant="contained" color="primary">Create Client</Button>
        </Box>
    );
};

export default ClientForm;
