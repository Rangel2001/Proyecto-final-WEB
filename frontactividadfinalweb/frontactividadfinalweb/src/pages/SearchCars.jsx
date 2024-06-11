import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { Grid, Typography, TextField, Select, MenuItem, Button, List, ListItem, ListItemText } from '@mui/material';

const SearchCars = () => {
    const [location, setLocation] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [locations, setLocations] = useState([]);
    const [availableCars, setAvailableCars] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('http://localhost:8088/api/v1/cars')
            .then(response => {
                const uniqueLocations = [...new Set(response.data.map(car => car.location))];
                setLocations(uniqueLocations);
            })
            .catch(error => {
                console.error('Error fetching locations:', error);
            });
    }, []);

    const handleSearch = () => {
        axios.get('http://localhost:8088/api/v1/cars', {
            params: {
                location: location,
                startDate: startDate,
                endDate: endDate
            }
        })
            .then(response => {
                const filteredCars = response.data.filter(car => car.location === location);
                setAvailableCars(filteredCars);
            })
            .catch(error => {
                console.error('Error fetching available cars:', error);
            });
    };

    const handleRentClick = (car) => {
        navigate('/rent-form', { state: { car } });
    }

    return (
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <Typography variant="h2">Search Cars</Typography>
            </Grid>
            <Grid item xs={12} sm={6}>
                <TextField
                    id="location"
                    label="Location"
                    value={location}
                    onChange={(e) => setLocation(e.target.value)}
                    select
                    fullWidth
                    margin="normal"
                    required
                >
                    <MenuItem value="">Select a location</MenuItem>
                    {locations.map(loc => (
                        <MenuItem key={loc} value={loc}>{loc}</MenuItem>
                    ))}
                </TextField>
            </Grid>
            <Grid item xs={12} sm={6}>
                <TextField
                    id="startDate"
                    label="Start Date"
                    type="date"
                    value={startDate}
                    onChange={(e) => setStartDate(e.target.value)}
                    fullWidth
                    margin="normal"
                    required
                />
            </Grid>
            <Grid item xs={12} sm={6}>
                <TextField
                    id="endDate"
                    label="End Date"
                    type="date"
                    value={endDate}
                    onChange={(e) => setEndDate(e.target.value)}
                    fullWidth
                    margin="normal"
                    required
                />
            </Grid>
            <Grid item xs={12}>
                <Button variant="contained" color="primary" onClick={handleSearch}>Search</Button>
            </Grid>
            <Grid item xs={12}>
                <Typography variant="h3">Available Cars:</Typography>
                <List>
                    {availableCars.map(car => (
                        <ListItem key={car.id}>
                            <ListItemText primary={`${car.model} - ${car.location}`} />
                            <Button variant="contained" color="primary" onClick={() => handleRentClick(car)}>Rentar</Button>
                        </ListItem>
                    ))}
                </List>
            </Grid>
        </Grid>
    );
}

export default SearchCars;