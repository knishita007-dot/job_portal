import React from 'react';

const Input = React.forwardRef(({ className = '', error, icon: Icon, ...props }, ref) => {
  return (
    <div className="relative w-full">
      {Icon && (
        <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
          <Icon className="h-5 w-5 text-gray-400" />
        </div>
      )}
      <input
        ref={ref}
        className={`
          block w-full rounded-lg border-gray-300 bg-white px-4 py-2 text-sm text-gray-900 
          transition-colors duration-200 focus:border-primary-500 focus:outline-none focus:ring-2 focus:ring-primary-500/20
          dark:border-gray-700 dark:bg-dark-bg dark:text-gray-100 dark:focus:border-primary-500
          ${Icon ? 'pl-10' : ''}
          ${error ? 'border-red-500 focus:border-red-500 focus:ring-red-500/20' : ''}
          ${className}
        `}
        {...props}
      />
      {error && <p className="mt-1 text-sm text-red-500">{error}</p>}
    </div>
  );
});

Input.displayName = 'Input';
export default Input;
