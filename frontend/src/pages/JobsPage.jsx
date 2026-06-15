import { useState, useEffect } from 'react';
import { Search, MapPin, DollarSign, Briefcase, SlidersHorizontal, ChevronDown } from 'lucide-react';
import { motion } from 'framer-motion';
import JobCard from '../components/JobCard';
import Button from '../components/ui/Button';
import Input from '../components/ui/Input';
import Card from '../components/ui/Card';
import Skeleton from '../components/ui/Skeleton';
import jobService from '../api/jobService';

export default function JobsPage() {
  const [jobs, setJobs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [keyword, setKeyword] = useState('');
  
  // Advanced filters state
  const [filters, setFilters] = useState({
    type: 'all',
    experience: 'all',
    salary: 'all',
    remote: false
  });

  useEffect(() => {
    fetchJobs();
  }, []);

  const fetchJobs = async () => {
    setLoading(true);
    try {
      const response = await jobService.getAll();
      setJobs(response.data || []);
    } catch (error) {
      console.error('Failed to fetch jobs:', error);
    }
    setLoading(false);
  };

  const handleKeywordSearch = async (e) => {
    e.preventDefault();
    if (!keyword.trim()) {
      fetchJobs();
      return;
    }
    setLoading(true);
    try {
      const response = await jobService.search(keyword);
      setJobs(response.data || []);
    } catch (error) {
      console.error('Search failed:', error);
    }
    setLoading(false);
  };

  const handleFilterChange = (key, value) => {
    setFilters(prev => ({ ...prev, [key]: value }));
  };

  // Client-side filtering
  const filteredJobs = jobs.filter(job => {
    if (filters.type !== 'all' && job.type !== filters.type && job.jobType !== filters.type) return false;
    if (filters.remote && (!job.location || !job.location.toLowerCase().includes('remote'))) return false;
    // Add more advanced filtering logic here if needed
    return true;
  });

  return (
    <div className="min-h-screen pt-24 pb-12 bg-gray-50 dark:bg-dark-bg">
      <div className="container mx-auto px-4 md:px-6">
        {/* Header & Main Search */}
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900 dark:text-white mb-2">Find Your Next Role</h1>
          <p className="text-gray-500 dark:text-gray-400 mb-6">Showing {filteredJobs.length} available opportunities</p>
          
          <Card className="p-2 md:p-4 mb-8">
            <form onSubmit={handleKeywordSearch} className="flex flex-col md:flex-row gap-4">
              <div className="flex-grow">
                <Input 
                  icon={Search}
                  placeholder="Job title, keywords, or company" 
                  value={keyword}
                  onChange={(e) => setKeyword(e.target.value)}
                  className="h-12 text-base"
                />
              </div>
              <div className="flex-grow md:max-w-xs">
                <Input 
                  icon={MapPin}
                  placeholder="City, state, or 'Remote'" 
                  className="h-12 text-base"
                />
              </div>
              <Button type="submit" variant="primary" size="lg" className="w-full md:w-auto h-12 px-8">
                Search
              </Button>
            </form>
          </Card>
        </div>

        <div className="flex flex-col lg:flex-row gap-8">
          {/* Sidebar Filters */}
          <div className="w-full lg:w-1/4">
            <Card className="p-6 sticky top-28">
              <div className="flex items-center gap-2 mb-6 border-b border-gray-100 dark:border-gray-800 pb-4">
                <SlidersHorizontal size={20} className="text-primary-600" />
                <h2 className="text-lg font-semibold text-gray-900 dark:text-white">Filters</h2>
              </div>

              <div className="space-y-6">
                {/* Job Type */}
                <div>
                  <h3 className="text-sm font-medium text-gray-900 dark:text-white mb-3">Job Type</h3>
                  <div className="space-y-2">
                    {['all', 'full-time', 'part-time', 'contract', 'internship'].map((type) => (
                      <label key={type} className="flex items-center gap-3 cursor-pointer group">
                        <input 
                          type="radio" 
                          name="jobType"
                          className="w-4 h-4 text-primary-600 border-gray-300 focus:ring-primary-500 dark:border-gray-600 dark:bg-gray-700"
                          checked={filters.type === type}
                          onChange={() => handleFilterChange('type', type)}
                        />
                        <span className="text-gray-600 dark:text-gray-300 capitalize group-hover:text-primary-600 transition-colors">
                          {type === 'all' ? 'All Types' : type.replace('-', ' ')}
                        </span>
                      </label>
                    ))}
                  </div>
                </div>

                {/* Preferences */}
                <div>
                  <h3 className="text-sm font-medium text-gray-900 dark:text-white mb-3">Preferences</h3>
                  <label className="flex items-center gap-3 cursor-pointer group">
                    <input 
                      type="checkbox" 
                      className="w-4 h-4 text-primary-600 border-gray-300 rounded focus:ring-primary-500 dark:border-gray-600 dark:bg-gray-700"
                      checked={filters.remote}
                      onChange={(e) => handleFilterChange('remote', e.target.checked)}
                    />
                    <span className="text-gray-600 dark:text-gray-300 group-hover:text-primary-600 transition-colors">
                      Remote Only
                    </span>
                  </label>
                </div>
                
                {/* Salary Range (Visual only for now) */}
                <div>
                  <h3 className="text-sm font-medium text-gray-900 dark:text-white mb-3 flex justify-between">
                    Salary Range <span className="text-primary-600 font-bold">$0 - $200k+</span>
                  </h3>
                  <input type="range" className="w-full accent-primary-600" min="0" max="200" defaultValue="50" />
                  <div className="flex justify-between text-xs text-gray-400 mt-2">
                    <span>$0</span>
                    <span>$100k</span>
                    <span>$200k+</span>
                  </div>
                </div>
              </div>
            </Card>
          </div>

          {/* Job Grid */}
          <div className="w-full lg:w-3/4">
            {loading ? (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                {[...Array(6)].map((_, i) => (
                  <Card key={i} className="p-6 h-64 flex flex-col">
                    <div className="flex gap-4 mb-4">
                      <Skeleton className="w-12 h-12 rounded-xl" />
                      <div className="flex-1 space-y-2">
                        <Skeleton variant="text" className="w-3/4" />
                        <Skeleton variant="text" className="w-1/2" />
                      </div>
                    </div>
                    <div className="grid grid-cols-2 gap-3 mb-6">
                      <Skeleton variant="text" />
                      <Skeleton variant="text" />
                    </div>
                    <div className="mt-auto flex justify-between">
                      <Skeleton className="w-16 h-6 rounded" />
                      <Skeleton className="w-20 h-8 rounded" />
                    </div>
                  </Card>
                ))}
              </div>
            ) : filteredJobs.length === 0 ? (
              <motion.div 
                initial={{ opacity: 0, scale: 0.95 }}
                animate={{ opacity: 1, scale: 1 }}
                className="text-center py-20 bg-white dark:bg-dark-card rounded-2xl border border-gray-100 dark:border-gray-800"
              >
                <div className="w-20 h-20 bg-gray-50 dark:bg-gray-800 rounded-full flex items-center justify-center mx-auto mb-4">
                  <Briefcase size={32} className="text-gray-400" />
                </div>
                <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-2">No matching jobs</h3>
                <p className="text-gray-500 mb-6">Try adjusting your filters or search terms</p>
                <Button variant="outline" onClick={() => {
                  setKeyword('');
                  setFilters({ type: 'all', experience: 'all', salary: 'all', remote: false });
                  fetchJobs();
                }}>
                  Clear All Filters
                </Button>
              </motion.div>
            ) : (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                {filteredJobs.map((job, index) => (
                  <JobCard key={job.id} job={job} index={index} />
                ))}
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
