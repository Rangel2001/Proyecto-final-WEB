import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Box, Typography, Grid, Card, CardContent } from '@mui/material';

const CarList = () => {
    const [cars, setCars] = useState([]);

    useEffect(() => {
        const fetchCars = async () => {
            const response = await axios.get('http://localhost:8088/api/v1/cars');
            setCars(response.data);
        };
        fetchCars();
    }, []);

    return (
        <Box>
            <Typography variant="h4" gutterBottom>Cars</Typography>
            <Grid container spacing={2}>
                {cars.map(car => (
                    <Grid item key={car.id} xs={12} sm={6} md={4}>
                        <Card>
                            <CardContent>
                                <Typography variant="h6">Model: {car.model}</Typography>
                                <Typography>Location: {car.location}</Typography>
                                <Typography>Price: ${car.price}</Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
};

export default CarList;