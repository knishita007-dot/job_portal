import { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { Users, Briefcase, Building, Activity, ShieldAlert, BarChart3, User } from 'lucide-react';
import Card from '../components/ui/Card';
import Button from '../components/ui/Button';
import userService from '../api/userService';

export default function AdminDashboard() {
  const [usersList, setUsersList] = useState([]);
  const [loadingUsers, setLoadingUsers] = useState(true);

  useEffect(() => {
    userService.getAll()
      .then(res => {
        // Sort newest first
        const sorted = (res.data || []).sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
        setUsersList(sorted);
      })
      .catch(err => console.error("Error fetching users:", err))
      .finally(() => setLoadingUsers(false));
  }, []);

  const stats = [
    { label: 'Total Users', value: usersList.length || '...', icon: Users, color: 'text-blue-500', bg: 'bg-blue-50 dark:bg-blue-900/20' },
    { label: 'Active Jobs', value: '3,210', icon: Briefcase, color: 'text-purple-500', bg: 'bg-purple-50 dark:bg-purple-900/20' },
    { label: 'Companies', value: '845', icon: Building, color: 'text-green-500', bg: 'bg-green-50 dark:bg-green-900/20' },
    { label: 'Platform Activity', value: '+24%', icon: Activity, color: 'text-accent', bg: 'bg-accent/10' },
  ];

  return (
    <div className="min-h-screen pt-24 pb-12 bg-gray-50 dark:bg-dark-bg">
      <div className="container mx-auto px-4 md:px-6">
        <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-8 gap-4">
          <div>
            <h1 className="text-2xl md:text-3xl font-bold text-gray-900 dark:text-white flex items-center gap-2">
              <ShieldAlert className="text-red-500" /> Admin Control Panel
            </h1>
            <p className="text-gray-500 dark:text-gray-400 mt-1">Platform overview and management.</p>
          </div>
          <Button variant="outline" className="shadow-sm">
            <BarChart3 size={18} className="mr-2" /> View Detailed Analytics
          </Button>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {stats.map((stat, idx) => (
            <motion.div key={idx} initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ delay: idx * 0.1 }}>
              <Card className="p-6 flex items-center gap-4 border-l-4" style={{ borderLeftColor: 'currentColor' }}>
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

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <Card className="p-6 h-[400px] flex flex-col">
            <div className="flex justify-between items-center mb-6 shrink-0">
              <h2 className="text-lg font-bold text-gray-900 dark:text-white">Recent Registrations</h2>
              <Button variant="ghost" size="sm">Manage Users</Button>
            </div>
            <div className="space-y-4 overflow-y-auto pr-2 flex-1 scrollbar-thin scrollbar-thumb-gray-300 dark:scrollbar-thumb-gray-600">
              {loadingUsers ? (
                <p className="text-gray-500 text-center py-4">Loading users...</p>
              ) : usersList.length === 0 ? (
                <p className="text-gray-500 text-center py-4">No users found.</p>
              ) : (
                usersList.map((u) => (
                  <div key={u.id} className="flex items-center justify-between p-3 hover:bg-gray-50 dark:hover:bg-gray-800 rounded-lg transition-colors border border-transparent hover:border-gray-100 dark:hover:border-gray-700">
                    <div className="flex items-center gap-3">
                      <div className="w-10 h-10 rounded-full bg-primary-100 dark:bg-primary-900/30 flex items-center justify-center text-primary-600 font-bold shrink-0">
                        {u.fullName ? u.fullName[0].toUpperCase() : <User size={20} />}
                      </div>
                      <div className="min-w-0">
                        <p className="text-sm font-medium text-gray-900 dark:text-white truncate">{u.fullName}</p>
                        <p className="text-xs text-gray-500 truncate flex items-center gap-2">
                          <span>{u.email}</span>
                          <span className="px-1.5 py-0.5 rounded-full bg-gray-100 dark:bg-gray-700 text-[10px] capitalize font-medium">{u.role}</span>
                        </p>
                      </div>
                    </div>
                    <span className="text-xs font-medium text-gray-500 shrink-0">
                      {new Date(u.createdAt).toLocaleDateString()}
                    </span>
                  </div>
                ))
              )}
            </div>
          </Card>

          <Card className="p-6">
            <div className="flex justify-between items-center mb-6">
              <h2 className="text-lg font-bold text-gray-900 dark:text-white">Jobs Pending Approval</h2>
              <Button variant="ghost" size="sm">Review All</Button>
            </div>
            <div className="space-y-4">
              {[1,2,3].map(i => (
                <div key={i} className="p-4 border border-yellow-100 dark:border-yellow-900/30 bg-yellow-50/50 dark:bg-yellow-900/10 rounded-xl flex flex-col sm:flex-row sm:items-center justify-between gap-4">
                  <div>
                    <h4 className="font-semibold text-gray-900 dark:text-white">Marketing Director</h4>
                    <p className="text-sm text-gray-500 mt-1">GrowthCorp Inc.</p>
                  </div>
                  <div className="flex items-center gap-2">
                    <Button variant="outline" size="sm" className="text-red-600 hover:bg-red-50 hover:text-red-700 border-red-200">Reject</Button>
                    <Button variant="primary" size="sm" className="bg-green-600 hover:bg-green-700 text-white shadow-sm">Approve</Button>
                  </div>
                </div>
              ))}
            </div>
          </Card>
        </div>
      </div>
    </div>
  );
}
