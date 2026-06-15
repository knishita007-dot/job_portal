import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { User, Mail, Lock, Phone, UserPlus, AlertCircle, Briefcase } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import Card from '../components/ui/Card';
import Button from '../components/ui/Button';
import Input from '../components/ui/Input';

export default function RegisterPage() {
  const { register } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState({ fullName: '', email: '', password: '', role: 'applicant', phone: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      await register(form);
      navigate('/');
    } catch (err) {
      setError(err.response?.data?.message || 'Registration failed');
    }
    setLoading(false);
  };

  return (
    <div className="min-h-screen pt-24 pb-12 bg-gray-50 dark:bg-dark-bg flex items-center justify-center px-4">
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="w-full max-w-md"
      >
        <Card className="p-8 shadow-2xl border-t-4 border-t-accent">
          <div className="text-center mb-8">
            <div className="w-16 h-16 bg-accent/10 rounded-full flex items-center justify-center mx-auto mb-4">
              <UserPlus size={32} className="text-accent" />
            </div>
            <h2 className="text-2xl font-bold text-gray-900 dark:text-white">Create Account</h2>
            <p className="text-gray-500 dark:text-gray-400 mt-2">Join the AI-powered job platform</p>
          </div>

          {error && (
            <div className="mb-6 p-4 rounded-lg bg-red-50 text-red-800 dark:bg-red-900/30 dark:text-red-400 text-sm flex items-start gap-3">
              <AlertCircle size={20} className="shrink-0 mt-0.5" />
              <span>{error}</span>
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-5">
            <div className="space-y-1.5">
              <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Full Name</label>
              <Input 
                icon={User}
                required 
                value={form.fullName} 
                onChange={e => setForm({...form, fullName: e.target.value})} 
                placeholder="Your full name" 
                id="register-name" 
              />
            </div>
            
            <div className="space-y-1.5">
              <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Email Address</label>
              <Input 
                icon={Mail}
                type="email" 
                required 
                value={form.email} 
                onChange={e => setForm({...form, email: e.target.value})} 
                placeholder="you@example.com" 
                id="register-email" 
              />
            </div>
            
            <div className="space-y-1.5">
              <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Password</label>
              <Input 
                icon={Lock}
                type="password" 
                required 
                value={form.password} 
                onChange={e => setForm({...form, password: e.target.value})} 
                placeholder="Create a password" 
                id="register-password" 
              />
            </div>

            <div className="space-y-1.5">
              <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Account Type</label>
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <Briefcase className="h-5 w-5 text-gray-400" />
                </div>
                <select 
                  className="block w-full rounded-lg border-gray-300 bg-white pl-10 pr-4 py-2 text-sm text-gray-900 transition-colors duration-200 focus:border-primary-500 focus:outline-none focus:ring-2 focus:ring-primary-500/20 dark:border-gray-700 dark:bg-dark-bg dark:text-gray-100 dark:focus:border-primary-500 appearance-none" 
                  value={form.role} 
                  onChange={e => setForm({...form, role: e.target.value})} 
                  id="register-role"
                >
                  <option value="applicant">Job Seeker</option>
                  <option value="employer">Employer</option>
                  <option value="admin">Admin</option>
                </select>
              </div>
            </div>

            <div className="space-y-1.5">
              <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Phone (optional)</label>
              <Input 
                icon={Phone}
                value={form.phone} 
                onChange={e => setForm({...form, phone: e.target.value})} 
                placeholder="+91-9876543210" 
                id="register-phone" 
              />
            </div>

            <Button 
              type="submit" 
              variant="gradient" 
              className="w-full h-12 text-lg font-semibold shadow-xl mt-2" 
              disabled={loading} 
              id="btn-register-submit"
            >
              {loading ? 'Creating account...' : 'Create Account'}
            </Button>
          </form>

          <div className="mt-8 pt-6 border-t border-gray-100 dark:border-gray-800 text-center">
            <p className="text-gray-600 dark:text-gray-400">
              Already have an account?{' '}
              <Link to="/login" className="text-primary-600 hover:underline font-semibold">
                Sign in
              </Link>
            </p>
          </div>
        </Card>
      </motion.div>
    </div>
  );
}
