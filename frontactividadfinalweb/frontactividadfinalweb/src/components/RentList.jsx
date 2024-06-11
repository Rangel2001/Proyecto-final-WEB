import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Box, Typography, Grid, Card, CardContent, List, ListItem, ListItemText } from '@mui/material';

const RentList = () => {
    const [rents, setRents] = useState([]);

    useEffect(() => {
        const fetchRents = async () => {
            const response = await axios.get('http://localhost:8088/api/v1/rents');
            setRents(response.data);
        };
        fetchRents();
    }, []);

    return (
        <Box>
            <Typography variant="h4" gutterBottom>Rents</Typography>
            <Grid container spacing={2}>
                {rents.map(rent => (
                    <Grid item key={rent.id} xs={12} sm={6} md={4}>
                        <Card>
                            <CardContent>
                                <Typography variant="h6">Car Model: {rent.car.model}</Typography>
                                <List>
                                    <ListItem>
                                        <ListItemText primary="Client" secondary={`${rent.client.name} ${rent.client.lastName}`} />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Start Date" secondary={rent.startDateRent} />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="End Date" secondary={rent.endDateRent} />
                                    </ListItem>
                                </List>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
};

export default RentList;