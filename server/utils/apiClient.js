const axios = require('axios');

// Default to the docker service name for Spring Boot, or a fallback for local testing
const springBootUrl = process.env.SPRINGBOOT_URL || 'http://localhost:8080';

const apiClient = axios.create({
    baseURL: springBootUrl + '/api',
    timeout: 10000,
});

module.exports = apiClient;
