import React from 'react';
import { useLocation } from 'react-router-dom';
import {Box, Typography, Grid, Card, CardContent, List, ListItem, ListItemText} from '@mui/material';

const RentSummary = () => {
    const location = useLocation();
    const { car, client, startDateRent, endDateRent } = location.state || {};

    if (!car || !client) {
        return <Typography>No rental summary available.</Typography>;
    }

    return (
        <Box>
            <Typography variant="h4">Rental Summary</Typography>
            <Card>
                <CardContent>
                    <Grid container spacing={2}>
                        <Grid item xs={12} sm={6}>
                            <Typography variant="h6">Car Model: {car.model}</Typography>
                            <Typography variant="h6">Location: {car.location}</Typography>
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <Typography variant="h6">Rental Period: {startDateRent} to {endDateRent}</Typography>
                        </Grid>
                        <Grid item xs={12}>
                            <Typography variant="h6">Client Information:</Typography>
                            <List>
                                <ListItem>
                                    <ListItemText primary="Name" secondary={`${client.name} ${client.lastName}`} />
                                </ListItem>
                                <ListItem>
                                    <ListItemText primary="Address" secondary={client.address} />
                                </ListItem>
                                <ListItem>
                                    <ListItemText primary="Phone" secondary={client.phone} />
                                </ListItem>
                            </List>
                        </Grid>
                    </Grid>
                </CardContent>
            </Card>
        </Box>
    );
};

export default RentSummary;