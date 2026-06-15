const express = require('express');
const cors = require('cors');
require('dotenv').config();
const connectDB = require('./config/db');

const app = express();
const PORT = process.env.PORT || 5000;

// Database Connection
connectDB();

// Middleware
app.use(cors());
app.use(express.json());

// Routes
const jobController = require('./controller/jobController');
const applicationController = require('./controller/applicationController');
const searchController = require('./controller/searchController');
const descriptionController = require('./controller/descriptionController');

app.use('/jobs', jobController);
app.use('/applications', applicationController);
app.use('/search', searchController);
app.use('/descriptions', descriptionController);

// Basic Route
app.get('/', (req, res) => {
    res.send('Server is running...');
});

// Start Server
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
