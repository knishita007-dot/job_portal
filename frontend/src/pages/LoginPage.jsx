import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Mail, Lock, LogIn, AlertCircle } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import Card from '../components/ui/Card';
import Button from '../components/ui/Button';
import Input from '../components/ui/Input';

export default function LoginPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      await login(email, password);
      navigate('/');
    } catch (err) {
      setError(err.response?.data?.message || 'Login failed. Please check your credentials.');
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
        <Card className="p-8 shadow-2xl border-t-4 border-t-primary-500">
          <div className="text-center mb-8">
            <div className="w-16 h-16 bg-primary-50 dark:bg-primary-900/30 rounded-full flex items-center justify-center mx-auto mb-4">
              <LogIn size={32} className="text-primary-600" />
            </div>
            <h2 className="text-2xl font-bold text-gray-900 dark:text-white">Welcome Back</h2>
            <p className="text-gray-500 dark:text-gray-400 mt-2">Sign in to access your account</p>
          </div>

          {error && (
            <div className="mb-6 p-4 rounded-lg bg-red-50 text-red-800 dark:bg-red-900/30 dark:text-red-400 text-sm flex items-start gap-3">
              <AlertCircle size={20} className="shrink-0 mt-0.5" />
              <span>{error}</span>
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-6">
            <div className="space-y-1.5">
              <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Email Address</label>
              <Input 
                icon={Mail}
                type="email" 
                required 
                value={email} 
                onChange={e => setEmail(e.target.value)} 
                placeholder="you@example.com" 
                id="login-email" 
              />
            </div>
            
            <div className="space-y-1.5">
              <div className="flex justify-between items-center">
                <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Password</label>
                <a href="#" className="text-xs text-primary-600 hover:underline">Forgot password?</a>
              </div>
              <Input 
                icon={Lock}
                type="password" 
                required 
                value={password} 
                onChange={e => setPassword(e.target.value)} 
                placeholder="Enter your password" 
                id="login-password" 
              />
            </div>

            <Button 
              type="submit" 
              variant="gradient" 
              className="w-full h-12 text-lg font-semibold shadow-xl" 
              disabled={loading} 
              id="btn-login-submit"
            >
              {loading ? 'Signing in...' : 'Sign In'}
            </Button>
          </form>

          <div className="mt-8 pt-6 border-t border-gray-100 dark:border-gray-800 text-center">
            <p className="text-gray-600 dark:text-gray-400">
              Don't have an account?{' '}
              <Link to="/register" className="text-primary-600 hover:underline font-semibold">
                Sign up
              </Link>
            </p>
          </div>
          
          <div className="mt-8 p-4 bg-blue-50 dark:bg-blue-900/20 rounded-xl text-sm border border-blue-100 dark:border-blue-800">
            <p className="font-semibold text-blue-800 dark:text-blue-300 mb-2">Demo Accounts:</p>
            <p className="text-blue-700 dark:text-blue-400 mb-1">Employer: employer@techcorp.com / password123</p>
            <p className="text-blue-700 dark:text-blue-400">Applicant: applicant1@gmail.com / password123</p>
          </div>
        </Card>
      </motion.div>
    </div>
  );
}
