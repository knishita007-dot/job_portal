import api from './axiosConfig';

const applicationService = {
  getAll: () => api.get('/applications'),
  getById: (id) => api.get(`/applications/${id}`),
  getByUser: (userId) => api.get(`/applications/user/${userId}`),
  getByJob: (jobId) => api.get(`/applications/job/${jobId}`),
  create: (data) => api.post('/applications', data),
  updateStatus: (id, status) => api.patch(`/applications/${id}/status`, { status }),
  delete: (id) => api.delete(`/applications/${id}`),
};

export default applicationService;
