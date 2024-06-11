import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Box, Typography, Grid, Card, CardContent, List, ListItem, ListItemText } from '@mui/material';

const ClientList = () => {
    const [clients, setClients] = useState([]);

    useEffect(() => {
        const fetchClients = async () => {
            const response = await axios.get('http://localhost:8088/api/v1/clients');
            setClients(response.data);
        };
        fetchClients();
    }, []);

    return (
        <Box>
            <Typography variant="h4" gutterBottom>Clients</Typography>
            <Grid container spacing={2}>
                {clients.map(client => (
                    <Grid item key={client.id} xs={12} sm={6} md={4}>
                        <Card>
                            <CardContent>
                                <Typography variant="h6">{client.name} {client.lastName}</Typography>
                                <List>
                                    <ListItem>
                                        <ListItemText primary="Cedula" secondary={client.cedula} />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Address" secondary={client.address} />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Phone" secondary={client.phone} />
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

export default ClientList;