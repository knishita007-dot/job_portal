import axios from 'axios';

let API_URL = import.meta.env.VITE_API_URL || '/api';

// If running in browser and on Render, dynamically construct the public Gateway URL to match the deployment suffix.
if (typeof window !== 'undefined' && window.location.hostname.endsWith('.onrender.com')) {
  const match = window.location.hostname.match(/jobportal-frontend(.*)\.onrender\.com/);
  if (match) {
    const suffix = match[1];
    API_URL = `https://jobportal-gateway${suffix}.onrender.com/api`;
  }
}

if (API_URL && !API_URL.startsWith('http') && !API_URL.startsWith('/')) {
  API_URL = `https://${API_URL}`;
}

// Ensure absolute URLs end with the /api prefix required by the API Gateway
if (API_URL.startsWith('http') && !API_URL.endsWith('/api')) {
  API_URL = API_URL.replace(/\/+$/, '') + '/api';
}

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 30000,
});

// Request interceptor — attach auth token if available
api.interceptors.request.use(
  (config) => {
    const user = JSON.parse(localStorage.getItem('user') || 'null');
    if (user?.token) {
      config.headers.Authorization = `Bearer ${user.token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response interceptor — handle errors globally
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;
