const Input = ({ label, type = 'text', value, onChange, placeholder, icon, required, className = '' }) => {
  return (
    <div className={className}>
      {label && (
        <label className="block text-sm font-medium text-gray-300 mb-2">
          {label}
        </label>
      )}
      <div className="relative">
        {icon && (
          <div className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-500">
            {icon}
          </div>
        )}
        <input
          type={type}
          value={value}
          onChange={onChange}
          placeholder={placeholder}
          required={required}
          className={`w-full ${icon ? 'pl-11' : 'px-4'} py-3 bg-[#111] border border-gray-800 rounded-lg text-white placeholder-gray-500 focus:outline-none focus:border-emerald-500 focus:ring-1 focus:ring-emerald-500 transition-all`}
        />
      </div>
    </div>
  );
};

export default Input;
