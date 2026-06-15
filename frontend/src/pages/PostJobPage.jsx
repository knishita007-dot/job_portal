import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import jobService from '../api/jobService';
import searchService from '../api/searchService';
import api from '../api/axiosConfig';
import './PostJobPage.css';

export default function PostJobPage() {
  const { user } = useAuth();
  const navigate = useNavigate();
  const [companies, setCompanies] = useState([]);
  const [skills, setSkills] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  const [form, setForm] = useState({
    title: '', companyId: '', location: '', salaryMin: '', salaryMax: '',
    jobType: 'full-time', experience: '', skillIds: [],
    description: '', requirements: '', benefits: ''
  });

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [compRes, skillRes] = await Promise.all([
        api.get('/companies'),
        api.get('/skills')
      ]);
      setCompanies(Array.isArray(compRes.data) ? compRes.data : []);
      setSkills(Array.isArray(skillRes.data) ? skillRes.data : []);
    } catch (error) {
      console.error('Failed to load form data:', error);
      setCompanies([]);
      setSkills([]);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!user) { setMessage('Please login first'); return; }
    setLoading(true);

    try {
      // 1. Create job in Spring Boot (PostgreSQL)
      const jobData = {
        title: form.title,
        companyId: parseInt(form.companyId),
        location: form.location,
        salaryMin: form.salaryMin ? parseFloat(form.salaryMin) : null,
        salaryMax: form.salaryMax ? parseFloat(form.salaryMax) : null,
        jobType: form.jobType,
        experience: form.experience,
        postedBy: user.id,
        skillIds: form.skillIds.map(Number)
      };
      const jobRes = await jobService.create(jobData);
      const newJobId = jobRes.data.id;

      // 2. Create description + embedding in Node.js (MongoDB)
      const selectedSkillNames = skills
        .filter(s => form.skillIds.includes(s.id.toString()))
        .map(s => s.name);

      const selectedCompany = companies.find(c => c.id === parseInt(form.companyId));

      await searchService.createDescription({
        jobId: newJobId,
        description: form.description,
        requirements: form.requirements.split('\n').filter(Boolean),
        benefits: form.benefits.split('\n').filter(Boolean),
        title: form.title,
        skills: selectedSkillNames,
        location: form.location,
        jobType: form.jobType,
        company: selectedCompany?.name || ''
      });

      setMessage('Job posted successfully!');
      setTimeout(() => navigate(`/jobs/${newJobId}`), 1500);
    } catch (error) {
      setMessage(error.response?.data?.message || 'Failed to post job');
    }
    setLoading(false);
  };

  const handleSkillToggle = (skillId) => {
    const id = skillId.toString();
    setForm(prev => ({
      ...prev,
      skillIds: prev.skillIds.includes(id)
        ? prev.skillIds.filter(s => s !== id)
        : [...prev.skillIds, id]
    }));
  };

  return (
    <div className="min-h-screen pt-24 pb-12 bg-gray-50 dark:bg-dark-bg">
      <div className="max-w-4xl mx-auto px-4 md:px-6 animate-fade-in-up">
        <div className="mb-8 text-center md:text-left">
          <h1 className="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-3">Post a New Job</h1>
          <p className="text-gray-500 dark:text-gray-400">Create a job listing — it will be stored in PostgreSQL with description and embeddings in MongoDB</p>
        </div>

        {message && (
          <div className={`p-4 mb-6 rounded-xl text-sm font-medium ${message.includes('success') ? 'bg-green-50 text-green-800 border border-green-200 dark:bg-green-900/30 dark:text-green-300 dark:border-green-800' : 'bg-red-50 text-red-800 border border-red-200 dark:bg-red-900/30 dark:text-red-300 dark:border-red-800'}`}>
            {message}
          </div>
        )}

        <form onSubmit={handleSubmit} className="bg-white dark:bg-dark-card rounded-2xl shadow-xl border border-gray-100 dark:border-gray-800 p-6 md:p-8">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
            <div className="space-y-2">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Job Title *</label>
              <input className="w-full px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" required value={form.title} onChange={e => setForm({...form, title: e.target.value})} placeholder="e.g., Senior Java Developer" id="input-title" />
            </div>
            <div className="space-y-2">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Company *</label>
              <select className="w-full px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" required value={form.companyId} onChange={e => setForm({...form, companyId: e.target.value})} id="input-company">
                <option value="">Select company</option>
                {companies.map(c => <option key={c.id} value={c.id}>{c.name}</option>)}
              </select>
            </div>
            <div className="space-y-2">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Location</label>
              <input className="w-full px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" value={form.location} onChange={e => setForm({...form, location: e.target.value})} placeholder="e.g., Bangalore, India or Remote" id="input-location" />
            </div>
            <div className="space-y-2">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Job Type</label>
              <select className="w-full px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" value={form.jobType} onChange={e => setForm({...form, jobType: e.target.value})} id="input-type">
                {['full-time', 'part-time', 'contract', 'internship', 'remote'].map(t => (
                  <option key={t} value={t}>{t}</option>
                ))}
              </select>
            </div>
            <div className="space-y-2">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Min Salary (₹)</label>
              <input className="w-full px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" type="number" value={form.salaryMin} onChange={e => setForm({...form, salaryMin: e.target.value})} placeholder="e.g., 1000000" id="input-salary-min" />
            </div>
            <div className="space-y-2">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Max Salary (₹)</label>
              <input className="w-full px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" type="number" value={form.salaryMax} onChange={e => setForm({...form, salaryMax: e.target.value})} placeholder="e.g., 2000000" id="input-salary-max" />
            </div>
          </div>

          <div className="space-y-2 mb-6">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Experience</label>
            <input className="w-full px-4 py-3 rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" value={form.experience} onChange={e => setForm({...form, experience: e.target.value})} placeholder="e.g., 3-5 years" id="input-experience" />
          </div>

          <div className="space-y-2 mb-6">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-3">Skills</label>
            <div className="flex flex-wrap gap-3">
              {skills.map(skill => {
                const isSelected = form.skillIds.includes(skill.id.toString());
                return (
                  <button key={skill.id} type="button"
                    className={`px-4 py-2 border rounded-full text-sm cursor-pointer transition-all ${isSelected ? 'bg-primary-50 dark:bg-primary-900/30 border-primary-500 text-primary-700 dark:text-primary-300 font-semibold' : 'border-gray-200 dark:border-gray-700 hover:border-primary-400 hover:text-primary-600 dark:text-gray-300 bg-white dark:bg-dark-bg'}`}
                    onClick={() => handleSkillToggle(skill.id)}
                  >
                    {skill.name}
                  </button>
                )
              })}
            </div>
          </div>

          <div className="space-y-2 mb-6">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Job Description *</label>
            <textarea className="w-full px-4 py-3 min-h-[120px] rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" required value={form.description} onChange={e => setForm({...form, description: e.target.value})} placeholder="Describe the role, responsibilities, and what makes it exciting..." id="input-description" />
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
            <div className="space-y-2">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Requirements (one per line)</label>
              <textarea className="w-full px-4 py-3 min-h-[120px] rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" value={form.requirements} onChange={e => setForm({...form, requirements: e.target.value})} placeholder="3+ years Java&#10;Spring Boot expertise&#10;PostgreSQL experience" id="input-requirements" />
            </div>
            <div className="space-y-2">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Benefits (one per line)</label>
              <textarea className="w-full px-4 py-3 min-h-[120px] rounded-xl border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors" value={form.benefits} onChange={e => setForm({...form, benefits: e.target.value})} placeholder="Health insurance&#10;Flexible hours&#10;Remote work" id="input-benefits" />
            </div>
          </div>

          <div className="flex justify-end border-t border-gray-100 dark:border-gray-800 pt-6">
            <button type="submit" className="inline-flex justify-center items-center px-8 py-3 text-base font-semibold rounded-xl text-white bg-gradient-to-r from-primary-600 to-accent shadow-glow hover:scale-[1.02] transition-transform focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500" disabled={loading} id="btn-post-job">
              {loading ? 'Posting...' : '🚀 Post Job'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
