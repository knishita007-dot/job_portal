import React from 'react';

const Button = React.forwardRef(({ className = '', variant = 'primary', size = 'md', children, ...props }, ref) => {
  const baseStyles = 'inline-flex items-center justify-center rounded-lg font-medium transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:opacity-50 disabled:pointer-events-none';
  
  const variants = {
    primary: 'bg-primary-600 text-white hover:bg-primary-700 shadow-md hover:shadow-lg hover:-translate-y-0.5 focus:ring-primary-500',
    secondary: 'bg-white text-secondary border border-gray-200 hover:bg-gray-50 hover:text-primary-600 focus:ring-gray-200 dark:bg-dark-card dark:border-gray-700 dark:text-gray-300 dark:hover:text-white',
    outline: 'border-2 border-primary-600 text-primary-600 hover:bg-primary-50 focus:ring-primary-500',
    ghost: 'text-gray-600 hover:text-primary-600 hover:bg-primary-50 dark:text-gray-400 dark:hover:text-white dark:hover:bg-white/10',
    gradient: 'bg-gradient-to-r from-primary-500 to-accent text-white hover:shadow-glow hover:-translate-y-1',
  };

  const sizes = {
    sm: 'px-3 py-1.5 text-sm',
    md: 'px-4 py-2 text-base',
    lg: 'px-6 py-3 text-lg',
  };

  return (
    <button
      ref={ref}
      className={`${baseStyles} ${variants[variant]} ${sizes[size]} ${className}`}
      {...props}
    >
      {children}
    </button>
  );
});

Button.displayName = 'Button';
export default Button;
