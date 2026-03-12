const Button = ({ children, type = 'button', onClick, loading = false, variant = 'primary', className = '', disabled }) => {
  const baseStyles = 'w-full py-3 px-4 font-semibold rounded-lg transition-all duration-200 flex items-center justify-center gap-2';
  
  const variants = {
    primary: 'bg-emerald-500 hover:bg-emerald-400 text-white disabled:opacity-50 disabled:cursor-not-allowed',
    secondary: 'bg-gray-800 hover:bg-gray-700 text-white border border-gray-700',
    outline: 'border border-gray-700 text-gray-300 hover:bg-gray-800 hover:text-white'
  };

  return (
    <button
      type={type}
      onClick={onClick}
      disabled={disabled || loading}
      className={`${baseStyles} ${variants[variant]} ${className}`}
    >
      {loading && (
        <svg className="animate-spin h-5 w-5" fill="none" viewBox="0 0 24 24">
          <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4" />
          <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z" />
        </svg>
      )}
      {children}
    </button>
  );
};

export default Button;
