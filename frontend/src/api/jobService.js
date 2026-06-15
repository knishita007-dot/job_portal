import api from './axiosConfig';

const jobService = {
  getAll: () => api.get('/jobs'),
  getById: (id) => api.get(`/jobs/${id}`),
  create: (data) => api.post('/jobs', data),
  update: (id, data) => api.put(`/jobs/${id}`, data),
  delete: (id) => api.delete(`/jobs/${id}`),
  getByType: (type) => api.get(`/jobs/type/${type}`),
  getByLocation: (location) => api.get(`/jobs/location/${location}`),
  getByUser: (userId) => api.get(`/jobs/user/${userId}`),
  search: (keyword) => api.get(`/jobs/search?keyword=${encodeURIComponent(keyword)}`),
};

export default jobService;
