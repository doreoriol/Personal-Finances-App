import { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');
    const email = localStorage.getItem('email');

    if (token && userId) {
      setUser({ userId: parseInt(userId), email });
    }
    setLoading(false);
  }, []);

  const login = (data) => {
    localStorage.setItem('token', data.accessToken);
    localStorage.setItem('userId', data.userId);
    localStorage.setItem('email', data.email);
    setUser({ userId: data.userId, email: data.email });
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    localStorage.removeItem('email');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
