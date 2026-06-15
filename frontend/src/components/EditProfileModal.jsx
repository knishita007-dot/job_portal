import { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { X, User, Phone, Save } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import userService from '../api/userService';
import Input from './ui/Input';
import Button from './ui/Button';

export default function EditProfileModal({ isOpen, onClose, onSuccess }) {
  const { user, login } = useAuth(); // We can't directly update AuthContext user without triggering a re-login or having an update context method. 
  // Wait, AuthContext has no updateUser method. We might need to manually update local storage or just reload.
  
  const [formData, setFormData] = useState({
    fullName: user?.fullName || '',
    phone: user?.phone || '',
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  if (!isOpen) return null;

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    try {
      const response = await userService.update(user.id, formData);
      
      // Update local storage so the UI reflects it immediately
      const updatedUser = { ...user, ...response.data };
      localStorage.setItem('user', JSON.stringify(updatedUser));
      
      // Trigger success callback to force a re-render or reload
      onSuccess(updatedUser);
      onClose();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to update profile');
    }
    setLoading(false);
  };

  return (
    <AnimatePresence>
      <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/50 backdrop-blur-sm">
        <motion.div
          initial={{ opacity: 0, scale: 0.95, y: 20 }}
          animate={{ opacity: 1, scale: 1, y: 0 }}
          exit={{ opacity: 0, scale: 0.95, y: 20 }}
          className="bg-white dark:bg-dark-card w-full max-w-md rounded-2xl shadow-2xl overflow-hidden border border-gray-100 dark:border-gray-800"
        >
          <div className="px-6 py-4 border-b border-gray-100 dark:border-gray-800 flex items-center justify-between bg-gray-50/50 dark:bg-gray-800/30">
            <h3 className="text-lg font-bold text-gray-900 dark:text-white">Edit Profile</h3>
            <button 
              onClick={onClose}
              className="p-2 text-gray-400 hover:text-gray-600 dark:hover:text-gray-300 rounded-full hover:bg-gray-100 dark:hover:bg-gray-800 transition-colors"
            >
              <X size={20} />
            </button>
          </div>

          <form onSubmit={handleSubmit} className="p-6 space-y-5">
            {error && (
              <div className="p-3 text-sm text-red-600 bg-red-50 dark:bg-red-900/20 dark:text-red-400 rounded-lg">
                {error}
              </div>
            )}

            <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
              <div className="space-y-1.5 md:col-span-2">
                <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Email Address (Non-editable)</label>
                <div className="relative opacity-70 cursor-not-allowed">
                  <Input 
                    icon={User}
                    value={user?.email || ''} 
                    disabled
                    readOnly
                  />
                </div>
              </div>

              <div className="space-y-1.5">
                <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Full Name</label>
                <Input 
                  icon={User}
                  required 
                  value={formData.fullName} 
                  onChange={e => setFormData({...formData, fullName: e.target.value})} 
                  placeholder="Your full name" 
                />
              </div>

              <div className="space-y-1.5">
                <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Phone Number</label>
                <Input 
                  icon={Phone}
                  value={formData.phone} 
                  onChange={e => setFormData({...formData, phone: e.target.value})} 
                  placeholder="Your phone number" 
                />
              </div>

              <div className="space-y-1.5 md:col-span-2">
                <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Account Type (Role)</label>
                <div className="relative opacity-70 cursor-not-allowed">
                  <Input 
                    icon={User}
                    value={user?.role === 'employer' ? 'Employer' : 'Job Seeker'} 
                    disabled
                    readOnly
                  />
                </div>
              </div>
            </div>

            <div className="pt-4 flex gap-3">
              <Button type="button" variant="outline" className="flex-1" onClick={onClose}>
                Cancel
              </Button>
              <Button type="submit" variant="primary" className="flex-1" disabled={loading}>
                {loading ? 'Saving...' : (
                  <span className="flex items-center gap-2 justify-center">
                    <Save size={18} /> Save Changes
                  </span>
                )}
              </Button>
            </div>
          </form>
        </motion.div>
      </div>
    </AnimatePresence>
  );
}
