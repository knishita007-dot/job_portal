import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { ArrowLeft, MapPin, DollarSign, Clock, Building, Briefcase, Share2, BookmarkPlus, CheckCircle2, AlertCircle } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import jobService from '../api/jobService';
import searchService from '../api/searchService';
import applicationService from '../api/applicationService';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Skeleton from '../components/ui/Skeleton';

export default function JobDetailPage() {
  const { id } = useParams();
  const { user } = useAuth();
  const [job, setJob] = useState(null);
  const [description, setDescription] = useState(null);
  const [loading, setLoading] = useState(true);
  const [applying, setApplying] = useState(false);
  const [applied, setApplied] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

  useEffect(() => {
    fetchJob();
    window.scrollTo(0, 0);
  }, [id]);

  const fetchJob = async () => {
    try {
      const [jobRes, descRes] = await Promise.allSettled([
        jobService.getById(id),
        searchService.getDescription(id)
      ]);
      if (jobRes.status === 'fulfilled') setJob(jobRes.value.data);
      if (descRes.status === 'fulfilled') setDescription(descRes.value.data);
    } catch (error) {
      console.error('Failed to fetch job:', error);
    }
    setLoading(false);
  };

  const handleApply = async () => {
    if (!user) {
      setMessage({ type: 'error', text: 'Please login to apply' });
      return;
    }
    setApplying(true);
    try {
      await applicationService.create({
        jobId: parseInt(id),
        userId: user.id,
        resumeUrl: '',
        coverLetter: ''
      });
      setApplied(true);
      setMessage({ type: 'success', text: 'Application submitted successfully!' });
    } catch (error) {
      setMessage({ type: 'error', text: error.response?.data?.message || 'Failed to apply' });
    }
    setApplying(false);
  };

  const formatSalary = (min, max) => {
    if (!min && !max) return 'Competitive';
    const format = (v) => v >= 100000 ? `$${(v / 1000).toFixed(0)}k` : `$${v.toLocaleString()}`;
    if (min && max) return `${format(min)} - ${format(max)}`;
    if (min) return `From ${format(min)}`;
    return `Up to ${format(max)}`;
  };

  if (loading) {
    return (
      <div className="min-h-screen pt-24 pb-12 bg-gray-50 dark:bg-dark-bg container mx-auto px-4 md:px-6">
        <Skeleton className="w-32 h-6 mb-8 rounded" />
        <div className="flex flex-col lg:flex-row gap-8">
          <div className="w-full lg:w-2/3 space-y-6">
            <Card className="p-8">
              <div className="flex gap-6 mb-8">
                <Skeleton className="w-20 h-20 rounded-2xl" />
                <div className="flex-1 space-y-3">
                  <Skeleton className="w-3/4 h-8 rounded" />
                  <Skeleton className="w-1/2 h-6 rounded" />
                </div>
              </div>
              <div className="flex gap-4 mb-8">
                <Skeleton className="w-24 h-10 rounded-full" />
                <Skeleton className="w-24 h-10 rounded-full" />
                <Skeleton className="w-24 h-10 rounded-full" />
              </div>
              <div className="space-y-4">
                <Skeleton className="w-full h-4 rounded" />
                <Skeleton className="w-full h-4 rounded" />
                <Skeleton className="w-5/6 h-4 rounded" />
                <Skeleton className="w-4/6 h-4 rounded" />
              </div>
            </Card>
          </div>
          <div className="w-full lg:w-1/3">
            <Card className="p-6 sticky top-28">
              <Skeleton className="w-full h-12 mb-4 rounded-xl" />
              <div className="space-y-4 mt-8">
                <Skeleton className="w-1/2 h-5 rounded" />
                <Skeleton className="w-full h-4 rounded" />
                <Skeleton className="w-full h-4 rounded" />
              </div>
            </Card>
          </div>
        </div>
      </div>
    );
  }

  if (!job) {
    return (
      <div className="min-h-screen pt-32 pb-12 bg-gray-50 dark:bg-dark-bg flex items-center justify-center">
        <div className="text-center">
          <div className="w-20 h-20 bg-gray-200 dark:bg-gray-800 rounded-full flex items-center justify-center mx-auto mb-6">
            <AlertCircle size={32} className="text-gray-400" />
          </div>
          <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-2">Job Not Found</h2>
          <p className="text-gray-500 mb-6">The position you're looking for might have been closed or removed.</p>
          <Link to="/jobs">
            <Button variant="primary">Browse Available Jobs</Button>
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen pt-24 pb-12 bg-gray-50 dark:bg-dark-bg">
      <div className="container mx-auto px-4 md:px-6">
        <Link to="/jobs" className="inline-flex items-center text-sm font-medium text-gray-500 hover:text-primary-600 transition-colors mb-6 group">
          <ArrowLeft size={16} className="mr-2 group-hover:-translate-x-1 transition-transform" />
          Back to all jobs
        </Link>

        <div className="flex flex-col lg:flex-row gap-8">
          {/* Main Content */}
          <div className="w-full lg:w-2/3">
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5 }}
            >
              <Card className="p-6 md:p-10 mb-8 overflow-hidden relative">
                {/* Decorative background */}
                <div className="absolute top-0 right-0 w-64 h-64 bg-primary-50 dark:bg-primary-900/10 rounded-bl-full -z-10"></div>
                
                <div className="flex flex-col md:flex-row md:items-start gap-6 mb-8">
                  <div className="w-20 h-20 bg-white dark:bg-dark-card border border-gray-100 dark:border-gray-700 rounded-2xl shadow-sm flex items-center justify-center shrink-0 text-3xl font-bold text-primary-600">
                    {(job.companyName || job.company?.name || 'C')[0].toUpperCase()}
                  </div>
                  <div className="flex-1">
                    <h1 className="text-2xl md:text-3xl font-bold text-gray-900 dark:text-white mb-2">
                      {job.title}
                    </h1>
                    <Link to={`/companies/${job.companyId || '#'}`} className="text-lg text-primary-600 hover:underline font-medium flex items-center gap-2 mb-4">
                      {job.companyName || job.company?.name}
                    </Link>
                    
                    <div className="flex flex-wrap items-center gap-y-3 gap-x-6 text-sm text-gray-600 dark:text-gray-300">
                      {job.location && (
                        <div className="flex items-center gap-1.5">
                          <MapPin size={18} className="text-gray-400" />
                          {job.location}
                        </div>
                      )}
                      <div className="flex items-center gap-1.5">
                        <DollarSign size={18} className="text-gray-400" />
                        {formatSalary(job.salaryMin || job.salary, job.salaryMax)}
                      </div>
                      {job.jobType && (
                        <div className="flex items-center gap-1.5 capitalize">
                          <Briefcase size={18} className="text-gray-400" />
                          {job.jobType.replace('-', ' ')}
                        </div>
                      )}
                    </div>
                  </div>
                </div>

                <div className="flex flex-wrap gap-2 border-t border-gray-100 dark:border-gray-800 pt-6">
                  {job.skills && job.skills.map((skill, idx) => (
                    <span key={idx} className="px-3 py-1 bg-gray-100 dark:bg-gray-800 text-gray-700 dark:text-gray-300 rounded-full text-sm font-medium">
                      {skill}
                    </span>
                  ))}
                  {(!job.skills || job.skills.length === 0) && (
                    <span className="px-3 py-1 bg-gray-100 dark:bg-gray-800 text-gray-700 dark:text-gray-300 rounded-full text-sm font-medium">React</span>
                  )}
                </div>
              </Card>

              {description && (
                <Card className="p-6 md:p-10 mb-8">
                  <h2 className="text-xl font-bold text-gray-900 dark:text-white mb-6">About the Role</h2>
                  
                  <div className="prose prose-gray dark:prose-invert max-w-none text-gray-600 dark:text-gray-300">
                    <div className="whitespace-pre-line mb-8 leading-relaxed">
                      {description.description || "No detailed description provided."}
                    </div>

                    {description.requirements && description.requirements.length > 0 && (
                      <div className="mb-8">
                        <h3 className="text-lg font-semibold text-gray-900 dark:text-white mb-4">Requirements</h3>
                        <ul className="space-y-2">
                          {description.requirements.map((req, idx) => (
                            <li key={idx} className="flex items-start gap-3">
                              <CheckCircle2 size={20} className="text-primary-500 shrink-0 mt-0.5" />
                              <span>{req}</span>
                            </li>
                          ))}
                        </ul>
                      </div>
                    )}

                    {description.benefits && description.benefits.length > 0 && (
                      <div>
                        <h3 className="text-lg font-semibold text-gray-900 dark:text-white mb-4">Benefits</h3>
                        <ul className="space-y-2">
                          {description.benefits.map((ben, idx) => (
                            <li key={idx} className="flex items-start gap-3">
                              <CheckCircle2 size={20} className="text-accent shrink-0 mt-0.5" />
                              <span>{ben}</span>
                            </li>
                          ))}
                        </ul>
                      </div>
                    )}
                  </div>
                </Card>
              )}
            </motion.div>
          </div>

          {/* Sidebar */}
          <div className="w-full lg:w-1/3">
            <motion.div
              initial={{ opacity: 0, x: 20 }}
              animate={{ opacity: 1, x: 0 }}
              transition={{ duration: 0.5, delay: 0.2 }}
              className="sticky top-28"
            >
              <Card className="p-6 border-t-4 border-t-primary-500">
                <div className="mb-6">
                  {message.text && (
                    <div className={`p-4 mb-4 rounded-lg text-sm font-medium flex items-start gap-3 ${
                      message.type === 'success' ? 'bg-green-50 text-green-800 dark:bg-green-900/30 dark:text-green-400' : 'bg-red-50 text-red-800 dark:bg-red-900/30 dark:text-red-400'
                    }`}>
                      {message.type === 'success' ? <CheckCircle2 size={20} className="shrink-0" /> : <AlertCircle size={20} className="shrink-0" />}
                      {message.text}
                    </div>
                  )}

                  {!applied ? (
                    <Button 
                      variant="gradient" 
                      className="w-full h-14 text-lg font-semibold shadow-xl" 
                      onClick={handleApply}
                      disabled={applying || (user && user.role === 'employer')}
                    >
                      {applying ? 'Submitting Application...' : 'Apply Now'}
                    </Button>
                  ) : (
                    <div className="w-full h-14 bg-green-50 dark:bg-green-900/20 text-green-700 dark:text-green-400 rounded-xl flex items-center justify-center gap-2 font-semibold border border-green-200 dark:border-green-800/50">
                      <CheckCircle2 size={20} />
                      Application Submitted
                    </div>
                  )}

                  {!user && (
                    <p className="text-center text-sm text-gray-500 mt-4">
                      <Link to="/login" className="text-primary-600 hover:underline font-medium">Log in</Link> to apply
                    </p>
                  )}
                  {user?.role === 'employer' && (
                    <p className="text-center text-sm text-gray-500 mt-4">
                      Employer accounts cannot apply to jobs.
                    </p>
                  )}
                </div>

                <div className="flex gap-4 border-b border-gray-100 dark:border-gray-800 pb-6 mb-6">
                  <Button variant="outline" className="flex-1 py-2.5" disabled={user?.role === 'employer'}>
                    <BookmarkPlus size={18} className="mr-2" /> Save
                  </Button>
                  <Button variant="outline" className="flex-1 py-2.5" disabled={user?.role === 'employer'}>
                    <Share2 size={18} className="mr-2" /> Share
                  </Button>
                </div>

                <h3 className="font-semibold text-gray-900 dark:text-white mb-4">Job Summary</h3>
                <div className="space-y-4 text-sm">
                  <div className="flex items-start gap-3 text-gray-600 dark:text-gray-300">
                    <Clock size={18} className="text-gray-400 shrink-0 mt-0.5" />
                    <div>
                      <p className="font-medium text-gray-900 dark:text-white">Date Posted</p>
                      <p>{job.createdAt ? new Date(job.createdAt).toLocaleDateString() : 'Recently'}</p>
                    </div>
                  </div>
                  <div className="flex items-start gap-3 text-gray-600 dark:text-gray-300">
                    <MapPin size={18} className="text-gray-400 shrink-0 mt-0.5" />
                    <div>
                      <p className="font-medium text-gray-900 dark:text-white">Location</p>
                      <p>{job.location || 'Remote'}</p>
                    </div>
                  </div>
                  <div className="flex items-start gap-3 text-gray-600 dark:text-gray-300">
                    <Briefcase size={18} className="text-gray-400 shrink-0 mt-0.5" />
                    <div>
                      <p className="font-medium text-gray-900 dark:text-white">Job Type</p>
                      <p className="capitalize">{job.jobType || 'Full-Time'}</p>
                    </div>
                  </div>
                </div>
              </Card>
            </motion.div>
          </div>
        </div>
      </div>
    </div>
  );
}
