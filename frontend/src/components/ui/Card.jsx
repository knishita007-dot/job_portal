import React from 'react';

const Card = React.forwardRef(({ className = '', hover = false, glass = false, children, ...props }, ref) => {
  const baseStyles = 'bg-white rounded-2xl border border-gray-100 shadow-sm dark:bg-dark-card dark:border-gray-800';
  const hoverStyles = hover ? 'transition-all duration-300 hover:shadow-xl hover:-translate-y-1' : '';
  const glassStyles = glass ? 'glass' : '';

  return (
    <div
      ref={ref}
      className={`${baseStyles} ${hoverStyles} ${glassStyles} ${className}`}
      {...props}
    >
      {children}
    </div>
  );
});

Card.displayName = 'Card';
export default Card;
