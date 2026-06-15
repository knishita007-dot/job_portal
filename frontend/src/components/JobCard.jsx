import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { MapPin, DollarSign, Clock, Building, ArrowRight } from 'lucide-react';
import Card from './ui/Card';
import Button from './ui/Button';

export default function JobCard({ job, index = 0 }) {
  // Add some fallback data if properties are missing
  const title = job?.title || 'Unknown Position';
  const company = job?.company?.name || 'Unknown Company';
  const location = job?.location || 'Remote';
  const type = job?.type || 'Full-time';
  const salary = job?.salary ? `$${job.salary}` : 'Competitive';

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      whileInView={{ opacity: 1, y: 0 }}
      viewport={{ once: true }}
      transition={{ duration: 0.4, delay: index * 0.1 }}
    >
      <Card hover className="h-full flex flex-col p-6 group">
        <div className="flex justify-between items-start mb-4">
          <div className="flex items-center gap-4">
            <div className="w-12 h-12 rounded-xl bg-gray-50 dark:bg-gray-800 border border-gray-100 dark:border-gray-700 flex items-center justify-center p-2">
              {/* Initials placeholder for logo */}
              <span className="text-lg font-bold text-primary-600">
                {company.charAt(0)}
              </span>
            </div>
            <div>
              <h3 className="font-semibold text-lg text-gray-900 dark:text-white group-hover:text-primary-600 transition-colors line-clamp-1">
                {title}
              </h3>
              <p className="text-gray-500 dark:text-gray-400 text-sm flex items-center gap-1 mt-1">
                <Building size={14} /> {company}
              </p>
            </div>
          </div>
          {job.score !== undefined && (
            <span className="inline-flex items-center rounded-full bg-green-50 px-2.5 py-0.5 text-xs font-medium text-green-700 dark:bg-green-900/30 dark:text-green-400">
              {(job.score * 100).toFixed(0)}% Match
            </span>
          )}
        </div>

        <div className="grid grid-cols-2 gap-3 mb-6 flex-grow">
          <div className="flex items-center gap-2 text-sm text-gray-600 dark:text-gray-300">
            <MapPin size={16} className="text-gray-400" />
            <span className="truncate">{location}</span>
          </div>
          <div className="flex items-center gap-2 text-sm text-gray-600 dark:text-gray-300">
            <DollarSign size={16} className="text-gray-400" />
            <span className="truncate">{salary}</span>
          </div>
          <div className="flex items-center gap-2 text-sm text-gray-600 dark:text-gray-300 col-span-2">
            <Clock size={16} className="text-gray-400" />
            <span>{type}</span>
          </div>
        </div>

        <div className="flex items-center justify-between mt-auto pt-4 border-t border-gray-100 dark:border-gray-800">
          <div className="flex gap-2">
            <span className="px-2 py-1 text-xs font-medium rounded-md bg-gray-100 text-gray-600 dark:bg-gray-800 dark:text-gray-300">
              Urgent
            </span>
          </div>
          <Link to={`/jobs/${job.id}`}>
            <Button variant="ghost" size="sm" className="group-hover:bg-primary-50 dark:group-hover:bg-primary-900/20 group-hover:text-primary-600">
              Details <ArrowRight size={16} className="ml-1" />
            </Button>
          </Link>
        </div>
      </Card>
    </motion.div>
  );
}
