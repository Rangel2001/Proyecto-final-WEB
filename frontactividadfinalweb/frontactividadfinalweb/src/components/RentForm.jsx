import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { TextField, Button, Box } from '@mui/material';

const RentForm = () => {
    const locationz = useLocation();
    const { car } = locationz.state || {};
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');
    const [cedula, setCedula] = useState('');
    const [address, setAddress] = useState('');
    const [phone, setPhone] = useState('');

    const [startDateRent, setStartDateRent] = useState('');
    const [endDateRent, setEndDateRent] = useState('');
    const [isAvailable, setIsAvailable] = useState(true);
    const [dateError, setDateError] = useState('');

    // Obtener la fecha actual en formato YYYY-MM-DD
    const getCurrentDate = () => {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    };

    const currentDate = getCurrentDate();

    useEffect(() => {
        if (startDateRent && endDateRent) {
            checkAvailability();
        }
    }, [startDateRent, endDateRent]);

    const checkAvailability = async () => {
        if (new Date(startDateRent) > new Date(endDateRent)) {
            setDateError('Start date cannot be later than end date.');
            setIsAvailable(false);
            return;
        } else {
            setDateError('');
        }

        try {
            const response = await axios.get('http://localhost:8088/api/v1/rents/availability', {
                params: {
                    carId: car.id,
                    startDateRent,
                    endDateRent
                }
            });
            setIsAvailable(response.data);
        } catch (error) {
            console.error('Error checking car availability:', error);
            setIsAvailable(false);
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!isAvailable) {
            alert('Car is not available for the selected dates.');
            return;
        }

        const clientData = {
            name,
            lastName,
            cedula,
            address,
            phone
        };

        const carData = {
            model: car.model,
            location: car.location,
            price: car.price
        };

        const rentData = {
            car: { id: car.id },
            client: clientData,
            startDateRent,
            endDateRent
        };

        try {
            // Crear el cliente
            const clientResponse = await axios.post('http://localhost:8088/api/v1/clients', clientData);
            const clientId = clientResponse.data.id;

            // Crear la renta
            await axios.post('http://localhost:8088/api/v1/rents', {
                ...rentData,
                client: { id: clientId }
            });

            // Rent summary
            navigate('/rent-summary', { state: { car: carData, client: clientData, startDateRent, endDateRent } });

            alert('Car rented successfully!');
        } catch (error) {
            console.error('Error renting car:', error);
            alert('Failed to rent car.');
        }
    };

    if (!car) {
        return <div>No car selected for renting.</div>;
    }

    return (
        <Box component="form" onSubmit={handleSubmit}>
            <h2>Rent Car: {car.model}</h2>
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
            <TextField
                id="startDateRent"
                label="Start Date"
                type="date"
                value={startDateRent}
                onChange={(e) => setStartDateRent(e.target.value)}
                InputLabelProps={{
                    shrink: true,
                }}
                inputProps={{
                    min: currentDate,  // Establecer la fecha mínima como la fecha actual
                }}
                fullWidth
                margin="normal"
                required
                error={!isAvailable || !!dateError}
                helperText={!isAvailable ? "Selected date range is not available." : dateError}
            />
            <TextField
                id="endDateRent"
                label="End Date"
                type="date"
                value={endDateRent}
                onChange={(e) => setEndDateRent(e.target.value)}
                InputLabelProps={{
                    shrink: true,
                }}
                inputProps={{
                    min: currentDate,  // Establecer la fecha mínima como la fecha actual
                }}
                fullWidth
                margin="normal"
                required
                error={!isAvailable || !!dateError}
                helperText={!isAvailable ? "Selected date range is not available." : dateError}
            />
            <Button type="submit" variant="contained" color="primary" disabled={!isAvailable || !!dateError}>
                Save Rent
            </Button>
        </Box>
    );
};

export default RentForm;
