import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Briefcase, Bookmark, FileText, CheckCircle, TrendingUp, Clock, Building, MapPin, Mail, Phone } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import Card from '../components/ui/Card';
import Button from '../components/ui/Button';
import applicationService from '../api/applicationService';
import EditProfileModal from '../components/EditProfileModal';

export default function DashboardPage() {
  const { user } = useAuth();
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isEditOpen, setIsEditOpen] = useState(false);

  useEffect(() => {
    const fetchApplications = async () => {
      try {
        const res = await applicationService.getByUser(user.id);
        setApplications(res.data || []);
      } catch (err) {
        console.error('Failed to fetch applications', err);
      }
      setLoading(false);
    };
    if (user) fetchApplications();
  }, [user]);

  const stats = [
    { label: 'Applied Jobs', value: applications.length, icon: Briefcase, color: 'text-blue-500', bg: 'bg-blue-50 dark:bg-blue-900/20' },
    { label: 'Saved Jobs', value: 12, icon: Bookmark, color: 'text-purple-500', bg: 'bg-purple-50 dark:bg-purple-900/20' },
    { label: 'Interviews', value: applications.filter(a => a.status === 'interviewing').length, icon: FileText, color: 'text-green-500', bg: 'bg-green-50 dark:bg-green-900/20' },
    { label: 'Profile Views', value: 48, icon: TrendingUp, color: 'text-accent', bg: 'bg-accent/10' },
  ];

  return (
    <div className="min-h-screen pt-24 pb-12 bg-gray-50 dark:bg-dark-bg">
      <div className="container mx-auto px-4 md:px-6">
        <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-8 gap-4">
          <div>
            <h1 className="text-2xl md:text-3xl font-bold text-gray-900 dark:text-white">Welcome back, {user?.fullName || 'User'}!</h1>
            <p className="text-gray-500 dark:text-gray-400 mt-1">Here is what's happening with your job applications today.</p>
          </div>
          <Link to="/jobs">
            <Button variant="primary" className="hover:scale-105 transition-transform duration-300 shadow-md shadow-primary-500/20">
              Find More Jobs
            </Button>
          </Link>
        </div>

        {/* User Profile */}
        <Card className="mb-8 overflow-hidden border-0 shadow-xl">
          <div className="h-32 bg-gradient-to-r from-primary-600 via-accent to-purple-600 relative">
            <div className="absolute -bottom-12 left-6 md:left-8">
              <div className="w-24 h-24 rounded-2xl bg-white dark:bg-dark-card p-1.5 shadow-lg">
                <div className="w-full h-full rounded-xl bg-gradient-to-br from-primary-50 to-primary-100 dark:from-gray-800 dark:to-gray-900 flex items-center justify-center text-4xl font-bold text-primary-600 dark:text-primary-400 shadow-inner">
                  {user?.fullName?.[0]?.toUpperCase() || 'U'}
                </div>
              </div>
            </div>
          </div>
          <div className="pt-16 pb-6 px-6 md:px-8 flex flex-col md:flex-row items-start md:items-center justify-between gap-4 bg-white dark:bg-dark-card">
            <div>
              <h2 className="text-2xl font-bold text-gray-900 dark:text-white flex items-center gap-2">
                {user?.fullName || 'User'}
                <CheckCircle className="w-5 h-5 text-green-500" />
              </h2>
              <div className="flex flex-wrap items-center gap-4 mt-3 text-sm font-medium text-gray-600 dark:text-gray-400">
                <span className="flex items-center gap-1.5 bg-gray-100 dark:bg-gray-800 px-3 py-1 rounded-full">
                  <Briefcase className="w-4 h-4 text-primary-500" /> 
                  <span className="capitalize">{user?.role || 'Job Seeker'}</span>
                </span>
                <span className="flex items-center gap-1.5">
                  <Mail className="w-4 h-4 text-gray-400" /> {user?.email}
                </span>
                {user?.phone && (
                  <span className="flex items-center gap-1.5">
                    <Phone className="w-4 h-4 text-gray-400" /> {user.phone}
                  </span>
                )}
                {user?.createdAt && (
                  <span className="flex items-center gap-1.5">
                    <Clock className="w-4 h-4 text-gray-400" /> Joined {new Date(user.createdAt).toLocaleDateString()}
                  </span>
                )}
              </div>
            </div>
            <Button 
              onClick={() => setIsEditOpen(true)}
              className="bg-primary-600 hover:bg-primary-700 text-white shadow-lg shadow-primary-500/30 transition-all hover:-translate-y-0.5 hover:scale-105"
            >
              Edit Profile
            </Button>
          </div>
        </Card>

        {/* Stats Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {stats.map((stat, idx) => (
            <motion.div
              key={idx}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: idx * 0.1 }}
            >
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

        {/* Recent Applications */}
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          <div className="lg:col-span-2">
            <Card className="p-6">
              <div className="flex justify-between items-center mb-6">
                <h2 className="text-lg font-bold text-gray-900 dark:text-white">Recent Applications</h2>
                <Button variant="ghost" size="sm">View All</Button>
              </div>

              {loading ? (
                <div className="animate-pulse space-y-4">
                  {[1,2,3].map(i => (
                    <div key={i} className="h-20 bg-gray-100 dark:bg-gray-800 rounded-xl"></div>
                  ))}
                </div>
              ) : applications.length === 0 ? (
                <div className="text-center py-10">
                  <div className="w-16 h-16 bg-gray-50 dark:bg-gray-800 rounded-full flex items-center justify-center mx-auto mb-4">
                    <Briefcase className="text-gray-400" />
                  </div>
                  <h3 className="font-semibold text-gray-900 dark:text-white">No applications yet</h3>
                  <p className="text-gray-500 text-sm mb-4">Start applying to jobs to see them here.</p>
                </div>
              ) : (
                <div className="space-y-4">
                  {applications.map((app) => (
                    <div key={app.id} className="p-4 border border-gray-100 dark:border-gray-800 rounded-xl flex flex-col sm:flex-row sm:items-center justify-between gap-4 hover:border-primary-200 dark:hover:border-primary-800 transition-colors">
                      <div className="flex items-start sm:items-center gap-4">
                        <div className="w-12 h-12 rounded-xl bg-gray-50 dark:bg-gray-800 flex items-center justify-center text-lg font-bold text-primary-600">
                          {app.job?.companyName?.[0] || 'C'}
                        </div>
                        <div>
                          <h4 className="font-semibold text-gray-900 dark:text-white">{app.job?.title || 'Unknown Job'}</h4>
                          <p className="text-sm text-gray-500 flex items-center gap-2">
                            <Building size={14} /> {app.job?.companyName || 'Company'}
                          </p>
                        </div>
                      </div>
                      
                      <div className="flex flex-col sm:items-end gap-2">
                        <span className={`inline-flex px-2.5 py-1 rounded-full text-xs font-medium capitalize ${
                          app.status === 'applied' ? 'bg-blue-50 text-blue-700 dark:bg-blue-900/30 dark:text-blue-400' :
                          app.status === 'interviewing' ? 'bg-yellow-50 text-yellow-700 dark:bg-yellow-900/30 dark:text-yellow-400' :
                          app.status === 'accepted' ? 'bg-green-50 text-green-700 dark:bg-green-900/30 dark:text-green-400' :
                          'bg-red-50 text-red-700 dark:bg-red-900/30 dark:text-red-400'
                        }`}>
                          {app.status}
                        </span>
                        <span className="text-xs text-gray-400 flex items-center gap-1">
                          <Clock size={12} /> {new Date(app.appliedAt).toLocaleDateString()}
                        </span>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </Card>
          </div>

          <div className="lg:col-span-1 space-y-6">
            <Card className="p-6">
              <h2 className="text-lg font-bold text-gray-900 dark:text-white mb-6">Application Timeline</h2>
              <div className="relative pl-6 border-l-2 border-gray-100 dark:border-gray-800 space-y-8">
                {applications.length === 0 ? (
                  <p className="text-sm text-gray-500">No application activity yet.</p>
                ) : (
                  [...applications]
                    .sort((a, b) => new Date(b.updatedAt) - new Date(a.updatedAt))
                    .slice(0, 5)
                    .map(app => {
                      let title = "Application Submitted";
                      let desc = `You applied for ${app.jobTitle || app.job?.title} at ${app.companyName || app.job?.companyName}`;
                      let color = "bg-gray-300 dark:bg-gray-700";
                      let date = new Date(app.appliedAt).toLocaleDateString();

                      if (app.status === 'interviewing') {
                        title = "Interview Scheduled";
                        desc = `You have an interview for ${app.jobTitle || app.job?.title} at ${app.companyName || app.job?.companyName}`;
                        color = "bg-yellow-500";
                        date = new Date(app.updatedAt).toLocaleDateString();
                      } else if (app.status === 'viewed') {
                        title = "Application Viewed";
                        desc = `${app.companyName || app.job?.companyName} viewed your application`;
                        color = "bg-primary-600";
                        date = new Date(app.updatedAt).toLocaleDateString();
                      } else if (app.status === 'accepted') {
                        title = "Application Accepted!";
                        desc = `Congratulations! You were accepted for ${app.jobTitle || app.job?.title}`;
                        color = "bg-green-600";
                        date = new Date(app.updatedAt).toLocaleDateString();
                      } else if (app.status === 'rejected') {
                        title = "Application Update";
                        desc = `Your application for ${app.jobTitle || app.job?.title} was not successful`;
                        color = "bg-red-500";
                        date = new Date(app.updatedAt).toLocaleDateString();
                      }

                      return (
                        <div key={app.id} className="relative">
                          <div className={`absolute -left-[31px] w-4 h-4 rounded-full ${color} ring-4 ring-white dark:ring-dark-card`}></div>
                          <p className="text-sm font-semibold text-gray-900 dark:text-white">{title}</p>
                          <p className="text-xs text-gray-500 mt-1">{desc}</p>
                          <p className="text-xs text-gray-400 mt-2">{date}</p>
                        </div>
                      );
                    })
                )}
              </div>
            </Card>
          </div>
        </div>
      </div>

      <EditProfileModal 
        isOpen={isEditOpen} 
        onClose={() => setIsEditOpen(false)} 
        onSuccess={() => window.location.reload()} 
      />
    </div>
  );
}
