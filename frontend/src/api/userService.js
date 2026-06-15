import api from './axiosConfig';

const userService = {
  getAll: () => api.get('/users'),
  getById: (id) => api.get(`/users/${id}`),
  register: (data) => api.post('/users/register', data),
  login: (credentials) => api.post('/users/login', credentials),
  update: (id, data) => api.put(`/users/${id}`, data),
  delete: (id) => api.delete(`/users/${id}`),
};

export default userService;
