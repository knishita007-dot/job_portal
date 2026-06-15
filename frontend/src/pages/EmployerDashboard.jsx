import { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { Users, Briefcase, Eye, TrendingUp, Plus } from 'lucide-react';
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import Card from '../components/ui/Card';
import Button from '../components/ui/Button';
import jobService from '../api/jobService';

export default function EmployerDashboard() {
  const { user } = useAuth();
  const [jobs, setJobs] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (user && user.id) {
      jobService.getByUser(user.id)
        .then(res => {
          // Sort jobs by newest first
          const sortedJobs = (res.data || []).sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
          setJobs(sortedJobs);
        })
        .catch(err => console.error("Error fetching jobs:", err))
        .finally(() => setLoading(false));
    }
  }, [user]);

  // Mock data for Employer
  const stats = [
    { label: 'Active Jobs', value: jobs.length, icon: Briefcase, color: 'text-blue-500', bg: 'bg-blue-50 dark:bg-blue-900/20' },
    { label: 'Total Applicants', value: 0, icon: Users, color: 'text-purple-500', bg: 'bg-purple-50 dark:bg-purple-900/20' },
    { label: 'Views This Week', value: 0, icon: Eye, color: 'text-green-500', bg: 'bg-green-50 dark:bg-green-900/20' },
    { label: 'Hiring Rate', value: '0%', icon: TrendingUp, color: 'text-accent', bg: 'bg-accent/10' },
  ];

  return (
    <div className="min-h-screen pt-24 pb-12 bg-gray-50 dark:bg-dark-bg">
      <div className="container mx-auto px-4 md:px-6">
        <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-8 gap-4">
          <div>
            <h1 className="text-2xl md:text-3xl font-bold text-gray-900 dark:text-white">Employer Dashboard</h1>
            <p className="text-gray-500 dark:text-gray-400 mt-1">Manage your job postings and applicants efficiently.</p>
          </div>
          <Link to="/post-job">
            <Button variant="primary" className="shadow-lg hover:shadow-primary-500/25">
              <Plus size={18} className="mr-2" /> Post New Job
            </Button>
          </Link>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {stats.map((stat, idx) => (
            <motion.div key={idx} initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ delay: idx * 0.1 }}>
              <Card className="p-6 flex items-center gap-4">
                <div className={`w-14 h-14 rounded-2xl flex items-center justify-center ${stat.bg}`}>
                  <stat.icon className={`w-7 h-7 ${stat.color}`} />
                </div>
                <div>
                  <p className="text-sm font-medium text-gray-500 dark:text-gray-400">{stat.label}</p>
                  <h3 className="text-2xl font-bold text-gray-900 dark:text-white">{stat.value}</h3>
                </div>
              </Card>
            </motion.div>
          ))}
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          <div className="lg:col-span-2">
            <Card className="p-6">
              <div className="flex justify-between items-center mb-6">
                <h2 className="text-lg font-bold text-gray-900 dark:text-white">Recent Job Postings</h2>
                <Link to="/jobs">
                  <Button variant="ghost" size="sm">View All</Button>
                </Link>
              </div>
              <div className="space-y-4">
                {loading ? (
                  <p className="text-gray-500 text-center py-4">Loading your jobs...</p>
                ) : jobs.length === 0 ? (
                  <div className="text-center py-8 text-gray-500 dark:text-gray-400">
                    <Briefcase className="w-12 h-12 mx-auto mb-3 opacity-20" />
                    <p>You haven't posted any jobs yet.</p>
                    <Link to="/post-job" className="text-primary-500 hover:underline mt-2 inline-block">Post your first job</Link>
                  </div>
                ) : (
                  jobs.map(job => (
                    <div key={job.id} className="p-4 border border-gray-100 dark:border-gray-800 rounded-xl flex flex-col sm:flex-row sm:items-center justify-between gap-4 transition-all hover:shadow-md dark:hover:border-gray-700">
                      <div>
                        <Link to={`/jobs/${job.id}`}>
                          <h4 className="font-semibold text-gray-900 dark:text-white hover:text-primary-500 transition-colors">{job.title}</h4>
                        </Link>
                        <p className="text-sm text-gray-500 mt-1">{job.location} • {job.jobType}</p>
                      </div>
                      <div className="flex items-center gap-4">
                        <div className="text-center px-4 border-r border-gray-100 dark:border-gray-700">
                          <span className="block text-xl font-bold text-gray-900 dark:text-white">0</span>
                          <span className="text-xs text-gray-500 uppercase tracking-wider">Applicants</span>
                        </div>
                        <Link to={`/jobs/${job.id}`}>
                          <Button variant="outline" size="sm">View</Button>
                        </Link>
                      </div>
                    </div>
                  ))
                )}
              </div>
            </Card>
          </div>

          <div className="lg:col-span-1">
            <Card className="p-6">
              <h2 className="text-lg font-bold text-gray-900 dark:text-white mb-6">Recent Applicants</h2>
              <div className="space-y-4">
                {[1,2,3,4].map(i => (
                  <div key={i} className="flex items-center gap-3">
                    <div className="w-10 h-10 rounded-full bg-gray-200 dark:bg-gray-800"></div>
                    <div className="flex-1 min-w-0">
                      <p className="text-sm font-medium text-gray-900 dark:text-white truncate">John Doe</p>
                      <p className="text-xs text-gray-500 truncate">Applied for React Developer</p>
                    </div>
                    <span className="text-xs font-medium text-primary-600 bg-primary-50 dark:bg-primary-900/30 px-2 py-1 rounded">New</span>
                  </div>
                ))}
              </div>
            </Card>
          </div>
        </div>
      </div>
    </div>
  );
}
