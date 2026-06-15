import React from 'react';

const Skeleton = ({ className = '', variant = 'rectangular', ...props }) => {
  const variants = {
    circular: 'rounded-full',
    rectangular: 'rounded-lg',
    text: 'rounded-md h-4 w-3/4',
  };

  return (
    <div
      className={`animate-pulse bg-gray-200 dark:bg-gray-800 ${variants[variant]} ${className}`}
      {...props}
    />
  );
};

export default Skeleton;
