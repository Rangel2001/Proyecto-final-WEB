import React from 'react';
import { Button, Box, Typography, Container } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Home = () => {
    const navigate = useNavigate();

    return (
        <Container maxWidth="sm">
            <Box textAlign="center" mt={5}>
                <Typography variant="h3" gutterBottom>
                    Welcome to our Car Rental App!
                </Typography>
                <Typography variant="h6" gutterBottom>
                    Choose an option below to get started.
                </Typography>
                <Box display="flex" justifyContent="space-around" flexWrap="wrap" mt={5}>
                    <Button variant="contained" color="primary" onClick={() => navigate('/create-car')} sx={{ m: 2 }}>
                        Create Car
                    </Button>
                    <Button variant="contained" color="primary" onClick={() => navigate('/cars-list')} sx={{ m: 2 }}>
                        See Cars
                    </Button>
                    <Button variant="contained" color="primary" onClick={() => navigate('/clients-list')} sx={{ m: 2 }}>
                        See Clients
                    </Button>
                    <Button variant="contained" color="primary" onClick={() => navigate('/rents-list')} sx={{ m: 2 }}>
                        See Rents
                    </Button>
                    <Button variant="contained" color="primary" onClick={() => navigate('/search-cars')} sx={{ m: 2 }}>
                        Rent a Car
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default Home;