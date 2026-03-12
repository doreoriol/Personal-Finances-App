const Card = ({ children, className = '' }) => {
  return (
    <div className={`bg-[#111] border border-gray-800 rounded-xl p-6 ${className}`}>
      {children}
    </div>
  );
};

export const CardHeader = ({ children, className = '' }) => (
  <div className={`flex items-center justify-between mb-4 ${className}`}>
    {children}
  </div>
);

export const CardTitle = ({ children, className = '' }) => (
  <h3 className={`text-sm font-medium text-gray-400 uppercase tracking-wider ${className}`}>
    {children}
  </h3>
);

export const CardContent = ({ children, className = '' }) => (
  <div className={className}>{children}</div>
);

export default Card;
