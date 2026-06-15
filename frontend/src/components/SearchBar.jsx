import { useState } from 'react';
import { Search, Loader2 } from 'lucide-react';
import Button from './ui/Button';

export default function SearchBar({ onSearch, loading }) {
  const [query, setQuery] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (query.trim()) {
      onSearch(query);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="relative w-full max-w-3xl mx-auto shadow-xl rounded-full">
      <div className="relative flex items-center w-full h-16 rounded-full bg-white dark:bg-dark-card border border-gray-200 dark:border-gray-700 overflow-hidden focus-within:ring-2 focus-within:ring-primary-500 focus-within:border-transparent transition-all">
        <div className="pl-6 flex items-center justify-center pointer-events-none">
          <Search className="h-5 w-5 text-gray-400" />
        </div>
        <input
          type="text"
          className="peer h-full w-full bg-transparent px-4 text-gray-900 dark:text-white placeholder-gray-400 focus:outline-none sm:text-lg"
          placeholder="e.g. 'React Developer in New York' or 'Remote Java Backend'"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
        <div className="pr-2">
          <Button 
            type="submit" 
            variant="gradient" 
            className="rounded-full px-8 py-3 whitespace-nowrap"
            disabled={loading}
          >
            {loading ? (
              <>
                <Loader2 className="mr-2 h-5 w-5 animate-spin" />
                Searching
              </>
            ) : (
              'Search Jobs'
            )}
          </Button>
        </div>
      </div>
    </form>
  );
}
