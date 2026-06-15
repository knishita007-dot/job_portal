import { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { Briefcase, Menu, X, User, LogOut, Moon, Sun } from 'lucide-react';
import { motion, AnimatePresence } from 'framer-motion';
import { useAuth } from '../context/AuthContext';
import Button from './ui/Button';

export default function Navbar() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [isScrolled, setIsScrolled] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const [darkMode, setDarkMode] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 10);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  useEffect(() => {
    if (darkMode) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }, [darkMode]);

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  const navLinks = [
    { name: 'Home', path: '/' },
    { name: 'Browse Jobs', path: '/jobs' },
    ...(user?.role === 'employer' ? [
      { name: 'Post a Job', path: '/post-job' },
      { name: 'Posted Jobs', path: '/employer' }
    ] : []),
    ...(user?.role === 'admin' ? [{ name: 'Admin Dashboard', path: '/admin' }] : []),
    ...(user && user.role !== 'employer' && user.role !== 'admin' ? [{ name: 'My Applications', path: '/applications' }] : []),
  ];

  return (
    <nav className={`fixed top-0 left-0 right-0 z-50 transition-all duration-300 ${
      isScrolled ? 'glass py-3' : 'bg-transparent py-5'
    }`}>
      <div className="container mx-auto px-4 md:px-6">
        <div className="flex items-center justify-between">
          <Link to="/" className="flex items-center gap-2 group">
            <div className="bg-gradient-to-br from-primary-500 to-accent text-white p-2 rounded-xl shadow-lg group-hover:scale-105 transition-transform">
              <Briefcase size={24} />
            </div>
            <span className="text-xl font-bold text-gray-900 dark:text-white tracking-tight">
              Job<span className="text-primary-600">Portal</span>
            </span>
          </Link>

          {/* Desktop Navigation */}
          <div className="hidden md:flex items-center gap-8">
            <div className="flex items-center gap-6">
              {navLinks.map((link) => (
                <Link
                  key={link.name}
                  to={link.path}
                  className={`text-sm font-medium transition-colors hover:text-primary-600 dark:hover:text-primary-400 ${
                    location.pathname === link.path ? 'text-primary-600 dark:text-primary-400' : 'text-gray-600 dark:text-gray-300'
                  }`}
                >
                  {link.name}
                </Link>
              ))}
            </div>

            <div className="flex items-center gap-4 border-l border-gray-200 dark:border-gray-700 pl-6">
              <button
                onClick={() => setDarkMode(!darkMode)}
                className="p-2 rounded-full hover:bg-gray-100 dark:hover:bg-gray-800 text-gray-600 dark:text-gray-300 transition-colors"
                aria-label="Toggle dark mode"
              >
                {darkMode ? <Sun size={20} /> : <Moon size={20} />}
              </button>

              {user ? (
                <div className="flex items-center gap-4">
                  <div className="flex items-center gap-2 text-sm font-medium text-gray-700 dark:text-gray-200">
                    <div className="w-8 h-8 rounded-full bg-primary-100 dark:bg-primary-900/30 text-primary-600 flex items-center justify-center">
                      <User size={16} />
                    </div>
                    <span className="hidden lg:block">{user.fullName}</span>
                  </div>
                  <Button variant="ghost" size="sm" onClick={handleLogout} className="!px-2">
                    <LogOut size={18} />
                  </Button>
                </div>
              ) : (
                <div className="flex items-center gap-3">
                  <Link to="/login">
                    <Button variant="ghost" size="sm">Log In</Button>
                  </Link>
                  <Link to="/register">
                    <Button variant="primary" size="sm">Sign Up</Button>
                  </Link>
                </div>
              )}
            </div>
          </div>

          {/* Mobile Menu Button */}
          <div className="md:hidden flex items-center gap-4">
            <button
              onClick={() => setDarkMode(!darkMode)}
              className="p-2 rounded-full text-gray-600 dark:text-gray-300"
            >
              {darkMode ? <Sun size={20} /> : <Moon size={20} />}
            </button>
            <button
              onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
              className="p-2 rounded-md text-gray-600 dark:text-gray-300 focus:outline-none"
            >
              {mobileMenuOpen ? <X size={24} /> : <Menu size={24} />}
            </button>
          </div>
        </div>
      </div>

      {/* Mobile Navigation */}
      <AnimatePresence>
        {mobileMenuOpen && (
          <motion.div
            initial={{ opacity: 0, height: 0 }}
            animate={{ opacity: 1, height: 'auto' }}
            exit={{ opacity: 0, height: 0 }}
            className="md:hidden glass border-t border-gray-100 dark:border-gray-800"
          >
            <div className="px-4 py-4 space-y-4">
              {navLinks.map((link) => (
                <Link
                  key={link.name}
                  to={link.path}
                  onClick={() => setMobileMenuOpen(false)}
                  className="block text-base font-medium text-gray-700 dark:text-gray-200 hover:text-primary-600"
                >
                  {link.name}
                </Link>
              ))}
              
              <div className="pt-4 border-t border-gray-200 dark:border-gray-700">
                {user ? (
                  <div className="space-y-4">
                    <div className="flex items-center gap-3 text-gray-700 dark:text-gray-200">
                      <div className="w-10 h-10 rounded-full bg-primary-100 flex items-center justify-center">
                        <User size={20} className="text-primary-600" />
                      </div>
                      <div>
                        <p className="font-medium">{user.fullName}</p>
                        <p className="text-sm text-gray-500 capitalize">{user.role}</p>
                      </div>
                    </div>
                    <Button variant="outline" className="w-full justify-center" onClick={() => { handleLogout(); setMobileMenuOpen(false); }}>
                      <LogOut size={18} className="mr-2" />
                      Logout
                    </Button>
                  </div>
                ) : (
                  <div className="grid grid-cols-2 gap-4">
                    <Link to="/login" onClick={() => setMobileMenuOpen(false)}>
                      <Button variant="outline" className="w-full justify-center">Log In</Button>
                    </Link>
                    <Link to="/register" onClick={() => setMobileMenuOpen(false)}>
                      <Button variant="primary" className="w-full justify-center">Sign Up</Button>
                    </Link>
                  </div>
                )}
              </div>
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </nav>
  );
}
