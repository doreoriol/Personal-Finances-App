import { useAuth } from '../context/AuthContext';

const StatCard = ({ title, value, change, positive, icon }) => (
  <div className="bg-[#111] border border-gray-800 rounded-xl p-6 hover:border-gray-700 transition-colors">
    <div className="flex items-center justify-between mb-4">
      <span className="text-sm font-medium text-gray-500 uppercase tracking-wider">{title}</span>
      <div className="w-10 h-10 bg-emerald-500/10 rounded-lg flex items-center justify-center">
        {icon}
      </div>
    </div>
    <div className="flex items-end justify-between">
      <div>
        <p className="text-3xl font-bold text-white">{value}</p>
        {change && (
          <p className={`text-sm mt-1 ${positive ? 'text-emerald-400' : 'text-red-400'} flex items-center gap-1`}>
            {positive ? (
              <svg className="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 10l7-7m0 0l7 7m-7-7v18" />
              </svg>
            ) : (
              <svg className="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 14l-7 7m0 0l-7-7m7 7V3" />
              </svg>
            )}
            {change}
          </p>
        )}
      </div>
    </div>
  </div>
);

const Dashboard = () => {
  const { user, logout } = useAuth();

  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 2
    }).format(amount);
  };

  return (
    <div className="min-h-screen bg-[#0a0a0a]">
      <nav className="bg-[#111] border-b border-gray-800 px-6 py-4">
        <div className="max-w-7xl mx-auto flex justify-between items-center">
          <div className="flex items-center gap-3">
            <div className="w-8 h-8 bg-emerald-500 rounded-lg flex items-center justify-center">
              <svg className="w-5 h-5 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
              </svg>
            </div>
            <span className="text-xl font-bold text-white tracking-tight">MyBalance</span>
          </div>
          <div className="flex items-center gap-4">
            <div className="hidden md:flex items-center gap-2 px-3 py-1.5 bg-gray-800/50 rounded-lg">
              <div className="w-2 h-2 bg-emerald-500 rounded-full animate-pulse" />
              <span className="text-sm text-gray-400">Connected</span>
            </div>
            <div className="flex items-center gap-3 pl-4 border-l border-gray-800">
              <div className="text-right hidden sm:block">
                <p className="text-sm font-medium text-white">{user?.email}</p>
                <p className="text-xs text-gray-500">Personal Account</p>
              </div>
              <button
                onClick={logout}
                className="p-2 hover:bg-gray-800 rounded-lg transition-colors"
                title="Logout"
              >
                <svg className="w-5 h-5 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={1.5} d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                </svg>
              </button>
            </div>
          </div>
        </div>
      </nav>

      <main className="max-w-7xl mx-auto p-6">
        <div className="mb-8">
          <h2 className="text-2xl font-bold text-white">Dashboard</h2>
          <p className="text-gray-500 mt-1">Welcome back! Here's your financial overview.</p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
          <StatCard
            title="Total Balance"
            value={formatCurrency(12540.50)}
            change="+12.5% vs last month"
            positive={true}
            icon={
              <svg className="w-5 h-5 text-emerald-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={1.5} d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            }
          />
          <StatCard
            title="Monthly Income"
            value={formatCurrency(8500.00)}
            change="+8.2% vs last month"
            positive={true}
            icon={
              <svg className="w-5 h-5 text-emerald-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={1.5} d="M7 11l5-5m0 0l5 5m-5-5v12" />
              </svg>
            }
          />
          <StatCard
            title="Monthly Expenses"
            value={formatCurrency(3245.75)}
            change="-3.1% vs last month"
            positive={true}
            icon={
              <svg className="w-5 h-5 text-red-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={1.5} d="M17 13l-5 5m0 0l-5-5m5 5V3" />
              </svg>
            }
          />
          <StatCard
            title="Savings Rate"
            value="61.8%"
            change="+5.2% vs last month"
            positive={true}
            icon={
              <svg className="w-5 h-5 text-emerald-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={1.5} d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
              </svg>
            }
          />
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
          <div className="lg:col-span-2 bg-[#111] border border-gray-800 rounded-xl p-6">
            <div className="flex items-center justify-between mb-6">
              <h3 className="text-lg font-semibold text-white">Spending by Category</h3>
              <button className="text-sm text-emerald-400 hover:text-emerald-300 transition-colors">
                View All
              </button>
            </div>
            <div className="space-y-4">
              {[
                { category: 'Housing', amount: 1200, percentage: 37, color: 'bg-blue-500' },
                { category: 'Food & Dining', amount: 650, percentage: 20, color: 'bg-emerald-500' },
                { category: 'Transportation', amount: 450, percentage: 14, color: 'bg-purple-500' },
                { category: 'Utilities', amount: 320, percentage: 10, color: 'bg-yellow-500' },
                { category: 'Entertainment', amount: 280, percentage: 9, color: 'bg-pink-500' },
                { category: 'Other', amount: 345, percentage: 10, color: 'bg-gray-500' }
              ].map((item) => (
                <div key={item.category} className="group">
                  <div className="flex items-center justify-between mb-1">
                    <span className="text-sm text-gray-300">{item.category}</span>
                    <span className="text-sm font-medium text-white">{formatCurrency(item.amount)}</span>
                  </div>
                  <div className="h-2 bg-gray-800 rounded-full overflow-hidden">
                    <div 
                      className={`h-full ${item.color} rounded-full transition-all duration-500 group-hover:opacity-80`}
                      style={{ width: `${item.percentage}%` }}
                    />
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="bg-[#111] border border-gray-800 rounded-xl p-6">
            <div className="flex items-center justify-between mb-6">
              <h3 className="text-lg font-semibold text-white">Recent Transactions</h3>
              <button className="text-sm text-emerald-400 hover:text-emerald-300 transition-colors">
                See All
              </button>
            </div>
            <div className="space-y-4">
              {[
                { desc: 'Salary Deposit', amount: +5000, type: 'income' },
                { desc: 'Whole Foods Market', amount: -156.32, type: 'expense' },
                { desc: 'Netflix Subscription', amount: -15.99, type: 'expense' },
                { desc: 'Uber Ride', amount: -24.50, type: 'expense' },
                { desc: 'Freelance Payment', amount: +850, type: 'income' }
              ].map((tx, i) => (
                <div key={i} className="flex items-center justify-between py-2 border-b border-gray-800 last:border-0">
                  <div className="flex items-center gap-3">
                    <div className={`w-8 h-8 rounded-full flex items-center justify-center ${tx.type === 'income' ? 'bg-emerald-500/10' : 'bg-gray-800'}`}>
                      {tx.type === 'income' ? (
                        <svg className="w-4 h-4 text-emerald-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M7 11l5-5m0 0l5 5m-5-5v12" />
                        </svg>
                      ) : (
                        <svg className="w-4 h-4 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M17 13l-5 5m0 0l-5-5m5 5V3" />
                        </svg>
                      )}
                    </div>
                    <div>
                      <p className="text-sm font-medium text-white">{tx.desc}</p>
                      <p className="text-xs text-gray-500">Today</p>
                    </div>
                  </div>
                  <span className={`text-sm font-semibold ${tx.amount > 0 ? 'text-emerald-400' : 'text-white'}`}>
                    {tx.amount > 0 ? '+' : ''}{formatCurrency(tx.amount)}
                  </span>
                </div>
              ))}
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Dashboard;
