import { useState } from 'react';
import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Briefcase, Zap, Shield, Award, Users, Building, ArrowRight, Search } from 'lucide-react';
import SearchBar from '../components/SearchBar';
import JobCard from '../components/JobCard';
import searchService from '../api/searchService';
import jobService from '../api/jobService';

export default function HomePage() {
  const [searchResults, setSearchResults] = useState([]);
  const [searchLoading, setSearchLoading] = useState(false);
  const [searched, setSearched] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');

  const handleSearch = async (query) => {
    setSearchLoading(true);
    setSearchQuery(query);
    try {
      const response = await searchService.semanticSearch(query);
      const results = response.data.results || [];

      // Fetch full job details for each result
      const jobDetails = await Promise.all(
        results.map(async (r) => {
          try {
            const jobRes = await jobService.getById(r.jobId);
            return { ...jobRes.data, score: r.score };
          } catch {
            return { id: r.jobId, title: `Job #${r.jobId}`, score: r.score };
          }
        })
      );
      setSearchResults(jobDetails);
      setSearched(true);
    } catch (error) {
      console.error('Search error:', error);
      setSearchResults([]);
      setSearched(true);
    }
    setSearchLoading(false);
  };

  const fadeInUp = {
    hidden: { opacity: 0, y: 30 },
    visible: { opacity: 1, y: 0, transition: { duration: 0.6 } }
  };

  const staggerContainer = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: { staggerChildren: 0.1 }
    }
  };

  return (
    <div className="min-h-screen bg-background dark:bg-dark-bg">
      {/* Hero Section */}
      <section className="relative pt-32 pb-20 lg:pt-48 lg:pb-32 overflow-hidden">
        {/* Background Decorative Elements */}
        <div className="absolute top-0 left-1/2 -translate-x-1/2 w-full h-full max-w-7xl pointer-events-none">
          <div className="absolute top-20 left-10 w-72 h-72 bg-primary-400/20 rounded-full blur-3xl mix-blend-multiply dark:mix-blend-screen"></div>
          <div className="absolute top-40 right-10 w-96 h-96 bg-accent/20 rounded-full blur-3xl mix-blend-multiply dark:mix-blend-screen"></div>
        </div>

        <div className="container mx-auto px-4 relative z-10 text-center">
          <motion.div
            initial="hidden"
            animate="visible"
            variants={staggerContainer}
            className="max-w-4xl mx-auto"
          >
            <motion.div variants={fadeInUp} className="inline-flex items-center gap-2 px-4 py-2 rounded-full bg-primary-50 dark:bg-primary-900/30 text-primary-600 mb-8 border border-primary-100 dark:border-primary-800">
              <span className="flex h-2 w-2 rounded-full bg-primary-600 animate-pulse"></span>
              <span className="text-sm font-medium">Over 10,000+ jobs added this week</span>
            </motion.div>
            
            <motion.h1 variants={fadeInUp} className="text-5xl lg:text-7xl font-bold text-gray-900 dark:text-white tracking-tight mb-6">
              Find Your <span className="text-gradient">Dream Job</span> Today
            </motion.h1>
            
            <motion.p variants={fadeInUp} className="text-xl text-gray-600 dark:text-gray-300 mb-10 max-w-2xl mx-auto">
              Apply to thousands of jobs from top companies. Our AI understands context, skills, and intent to match you perfectly.
            </motion.p>

            <motion.div variants={fadeInUp} className="mb-16">
              <SearchBar onSearch={handleSearch} loading={searchLoading} />
            </motion.div>
          </motion.div>
        </div>
      </section>

      {/* Featured Companies Logos */}
      <section className="py-10 border-y border-gray-100 dark:border-gray-800 bg-white dark:bg-dark-card/50">
        <div className="container mx-auto px-4">
          <p className="text-center text-sm font-medium text-gray-500 mb-6 uppercase tracking-widest">Trusted by leading companies</p>
          <div className="flex flex-wrap justify-center items-center gap-8 md:gap-16 opacity-60 grayscale hover:grayscale-0 transition-all duration-500">
            {/* Using text placeholders since we don't have images */}
            <h3 className="text-2xl font-bold text-gray-400 hover:text-gray-900 dark:hover:text-white transition-colors">Google</h3>
            <h3 className="text-2xl font-bold text-gray-400 hover:text-[#00a4ef] transition-colors">Microsoft</h3>
            <h3 className="text-2xl font-bold text-gray-400 hover:text-[#ff9900] transition-colors">Amazon</h3>
            <h3 className="text-2xl font-bold text-gray-400 hover:text-[#007cc3] transition-colors">Infosys</h3>
            <h3 className="text-2xl font-bold text-gray-400 hover:text-[#1d1d1d] dark:hover:text-white transition-colors">TCS</h3>
          </div>
        </div>
      </section>

      {/* Search Results */}
      {searched && (
        <section className="py-20" id="search-results">
          <div className="container mx-auto px-4">
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              className="flex justify-between items-end mb-10"
            >
              <div>
                <h2 className="text-3xl font-bold text-gray-900 dark:text-white">
                  {searchResults.length > 0
                    ? `Results for "${searchQuery}"`
                    : `No results for "${searchQuery}"`}
                </h2>
                <p className="text-gray-500 mt-2">Showing {searchResults.length} matching opportunities</p>
              </div>
            </motion.div>

            {searchResults.length > 0 ? (
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {searchResults.map((job, index) => (
                  <JobCard key={job.id} job={job} index={index} />
                ))}
              </div>
            ) : (
              <div className="text-center py-20 bg-white dark:bg-dark-card rounded-2xl border border-gray-100 dark:border-gray-800">
                <div className="w-20 h-20 bg-gray-50 dark:bg-gray-800 rounded-full flex items-center justify-center mx-auto mb-4">
                  <Search size={32} className="text-gray-400" />
                </div>
                <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-2">No jobs found</h3>
                <p className="text-gray-500">Try adjusting your search criteria or keywords.</p>
              </div>
            )}
          </div>
        </section>
      )}

      {/* Stats Section */}
      <section className="py-20 bg-primary-600 dark:bg-primary-900 text-white">
        <div className="container mx-auto px-4">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8 text-center divide-y md:divide-y-0 md:divide-x divide-primary-400/30">
            <motion.div
              initial={{ opacity: 0, scale: 0.9 }}
              whileInView={{ opacity: 1, scale: 1 }}
              viewport={{ once: true }}
              className="p-6"
            >
              <h4 className="text-5xl font-bold mb-2">10K+</h4>
              <p className="text-primary-100">Active Jobs</p>
            </motion.div>
            <motion.div
              initial={{ opacity: 0, scale: 0.9 }}
              whileInView={{ opacity: 1, scale: 1 }}
              viewport={{ once: true }}
              transition={{ delay: 0.1 }}
              className="p-6"
            >
              <h4 className="text-5xl font-bold mb-2">5K+</h4>
              <p className="text-primary-100">Top Companies</p>
            </motion.div>
            <motion.div
              initial={{ opacity: 0, scale: 0.9 }}
              whileInView={{ opacity: 1, scale: 1 }}
              viewport={{ once: true }}
              transition={{ delay: 0.2 }}
              className="p-6"
            >
              <h4 className="text-5xl font-bold mb-2">50K+</h4>
              <p className="text-primary-100">Candidates Hired</p>
            </motion.div>
          </div>
        </div>
      </section>

      {/* How It Works Section */}
      <section className="py-24">
        <div className="container mx-auto px-4">
          <div className="text-center max-w-2xl mx-auto mb-16">
            <h2 className="text-3xl lg:text-4xl font-bold text-gray-900 dark:text-white mb-4">Why Choose JobPortal?</h2>
            <p className="text-gray-600 dark:text-gray-300">We make finding your next career move easier, faster, and more intelligent.</p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              className="glass p-8 rounded-2xl text-center group"
            >
              <div className="w-16 h-16 bg-primary-50 dark:bg-primary-900/30 rounded-2xl flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform">
                <Zap size={32} className="text-primary-600" />
              </div>
              <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-3">AI Semantic Search</h3>
              <p className="text-gray-600 dark:text-gray-400">Describe your ideal job naturally. Our AI matches your intent and skills using advanced vector search.</p>
            </motion.div>

            <motion.div
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: 0.1 }}
              className="glass p-8 rounded-2xl text-center group"
            >
              <div className="w-16 h-16 bg-accent/10 rounded-2xl flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform">
                <Shield size={32} className="text-accent" />
              </div>
              <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-3">Verified Companies</h3>
              <p className="text-gray-600 dark:text-gray-400">Every employer on our platform is verified to ensure you get high-quality, legitimate job offers.</p>
            </motion.div>

            <motion.div
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: 0.2 }}
              className="glass p-8 rounded-2xl text-center group"
            >
              <div className="w-16 h-16 bg-purple-50 dark:bg-purple-900/30 rounded-2xl flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform">
                <Award size={32} className="text-purple-600" />
              </div>
              <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-3">Easy Application</h3>
              <p className="text-gray-600 dark:text-gray-400">Apply with a single click using your saved profile and track your status in real-time.</p>
            </motion.div>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20">
        <div className="container mx-auto px-4">
          <div className="bg-gray-900 dark:bg-dark-card rounded-3xl p-10 md:p-16 relative overflow-hidden text-center shadow-2xl">
            <div className="absolute top-0 right-0 w-64 h-64 bg-primary-600/20 rounded-full blur-3xl"></div>
            <div className="absolute bottom-0 left-0 w-64 h-64 bg-accent/20 rounded-full blur-3xl"></div>
            
            <div className="relative z-10 max-w-2xl mx-auto">
              <h2 className="text-3xl md:text-5xl font-bold text-white mb-6">Ready to upgrade your career?</h2>
              <p className="text-gray-400 text-lg mb-10">Join thousands of professionals who have found their dream jobs through JobPortal.</p>
              <div className="flex flex-col sm:flex-row items-center justify-center gap-4">
                <Link to="/register" className="w-full sm:w-auto inline-flex items-center justify-center px-8 py-4 rounded-xl bg-gradient-to-r from-primary-500 to-accent text-white font-semibold shadow-glow hover:scale-105 transition-all">
                  Create Account <ArrowRight size={20} className="ml-2" />
                </Link>
                <Link to="/jobs" className="w-full sm:w-auto inline-flex items-center justify-center px-8 py-4 rounded-xl bg-white/10 text-white font-semibold hover:bg-white/20 transition-all border border-white/10">
                  Browse Jobs
                </Link>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
