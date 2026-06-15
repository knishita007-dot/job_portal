import api from './axiosConfig';

const searchService = {
  semanticSearch: (query, topK = 10) => api.post('/search', { query, topK }),
  getDescription: (jobId) => api.get(`/descriptions/${jobId}`),
  createDescription: (data) => api.post('/descriptions', data),
  updateDescription: (jobId, data) => api.put(`/descriptions/${jobId}`, data),
  healthCheck: () => api.get('/search/health'),
};

export default searchService;
