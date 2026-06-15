import { Link } from 'react-router-dom';
import { Briefcase, Globe, Mail, Phone, MessageCircle } from 'lucide-react';

export default function Footer() {
  const currentYear = new Date().getFullYear();

  return (
    <footer className="bg-white dark:bg-dark-card border-t border-gray-100 dark:border-gray-800 pt-16 pb-8">
      <div className="container mx-auto px-4 md:px-6">

        <div className="grid grid-cols-1 md:grid-cols-4 gap-12 mb-12">

          {/* Brand */}
          <div className="col-span-1">
            <Link to="/" className="flex items-center gap-2 mb-6 group">
              
              <div className="bg-gradient-to-br from-blue-500 to-purple-500 text-white p-2 rounded-xl shadow-lg">
                <Briefcase size={24} />
              </div>

              <span className="text-2xl font-bold text-gray-900 dark:text-white tracking-tight">
                Job<span className="text-blue-600">Portal</span>
              </span>
            </Link>

            <p className="text-gray-500 dark:text-gray-400 mb-6 max-w-sm">
              Connecting top talent with innovative companies worldwide.
              Your next career move starts here.
            </p>

            {/* Social Icons */}
            <div className="flex items-center gap-4">

              <a href="#" className="w-10 h-10 rounded-full bg-gray-50 dark:bg-gray-800 flex items-center justify-center text-gray-500 hover:text-primary-600 hover:bg-primary-50 transition-colors">
                <Globe size={18} />
              </a>
              <a href="#" className="w-10 h-10 rounded-full bg-gray-50 dark:bg-gray-800 flex items-center justify-center text-gray-500 hover:text-primary-600 hover:bg-primary-50 transition-colors">
                <MessageCircle size={18} />
              </a>
              <a href="#" className="w-10 h-10 rounded-full bg-gray-50 dark:bg-gray-800 flex items-center justify-center text-gray-500 hover:text-primary-600 hover:bg-primary-50 transition-colors">
                <Mail size={18} />
              </a>
              <a href="#" className="w-10 h-10 rounded-full bg-gray-50 dark:bg-gray-800 flex items-center justify-center text-gray-500 hover:text-primary-600 hover:bg-primary-50 transition-colors">
                <Phone size={18} />
              </a>

            </div>
          </div>

          {/* Candidates */}
          <div>
            <h3 className="text-gray-900 dark:text-white font-semibold mb-6 uppercase text-sm tracking-wider">
              For Candidates
            </h3>

            <ul className="space-y-4 text-gray-500 dark:text-gray-400">
              <li>
                <Link to="/jobs" className="hover:text-blue-600 transition">
                  Browse Jobs
                </Link>
              </li>

              <li>
                <Link to="/companies" className="hover:text-blue-600 transition">
                  Browse Companies
                </Link>
              </li>

              <li>
                <Link to="/dashboard" className="hover:text-blue-600 transition">
                  Candidate Dashboard
                </Link>
              </li>

              <li>
                <Link to="/saved-jobs" className="hover:text-blue-600 transition">
                  Saved Jobs
                </Link>
              </li>
            </ul>
          </div>

          {/* Employers */}
          <div>
            <h3 className="text-gray-900 dark:text-white font-semibold mb-6 uppercase text-sm tracking-wider">
              For Employers
            </h3>

            <ul className="space-y-4 text-gray-500 dark:text-gray-400">

              <li>
                <Link to="/post-job" className="hover:text-blue-600 transition">
                  Post a Job
                </Link>
              </li>

              <li>
                <Link to="/employer" className="hover:text-blue-600 transition">
                  Employer Dashboard
                </Link>
              </li>

              <li>
                <Link to="/pricing" className="hover:text-blue-600 transition">
                  Pricing
                </Link>
              </li>

              <li>
                <Link to="/resources" className="hover:text-blue-600 transition">
                  Hiring Resources
                </Link>
              </li>

            </ul>
          </div>

          {/* Legal */}
          <div>
            <h3 className="text-gray-900 dark:text-white font-semibold mb-6 uppercase text-sm tracking-wider">
              Legal
            </h3>

            <ul className="space-y-4 text-gray-500 dark:text-gray-400">

              <li>
                <Link to="/about" className="hover:text-blue-600 transition">
                  About Us
                </Link>
              </li>

              <li>
                <Link to="/contact" className="hover:text-blue-600 transition">
                  Contact
                </Link>
              </li>

              <li>
                <Link to="/privacy" className="hover:text-blue-600 transition">
                  Privacy Policy
                </Link>
              </li>

              <li>
                <Link to="/terms" className="hover:text-blue-600 transition">
                  Terms of Service
                </Link>
              </li>

            </ul>
          </div>

        </div>

        {/* Bottom */}
        <div className="border-t border-gray-200 dark:border-gray-700 pt-8 flex flex-col md:flex-row items-center justify-between text-sm text-gray-500 dark:text-gray-400">

          <p>
            © {currentYear} JobPortal Inc. All rights reserved. 
            #Katuru Nishita - 2500031391
            $Vanama Srinidhi - 2500031356
            %Thalladha Spoorthi - 2500030449
          </p>

          <div className="flex gap-6 mt-4 md:mt-0">

            <Link to="/privacy" className="hover:text-blue-600">
              Privacy
            </Link>

            <Link to="/terms" className="hover:text-blue-600">
              Terms
            </Link>

            <Link to="/sitemap" className="hover:text-blue-600">
              Sitemap
            </Link>

          </div>
        </div>

      </div>
    </footer>
  );
}