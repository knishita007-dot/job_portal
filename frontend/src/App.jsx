 import { Routes, Route, Navigate } from "react-router-dom";
import { useAuth } from "./context/AuthContext";

import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

// Public Pages
import HomePage from "./pages/HomePage";
import JobsPage from "./pages/JobsPage";
import JobDetailPage from "./pages/JobDetailPage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";

// Protected Pages
import DashboardPage from "./pages/DashboardPage";
import EmployerDashboard from "./pages/EmployerDashboard";
import AdminDashboard from "./pages/AdminDashboard";
import PostJobPage from "./pages/PostJobPage";

import "./App.css";

// Protected Route Component
const ProtectedRoute = ({ children, allowedRoles }) => {
  const { user } = useAuth();

  // If user not logged in
  if (!user) {
    return <Navigate to="/login" replace />;
  }

  // If role not allowed
  if (allowedRoles && !allowedRoles.includes(user.role)) {
    return <Navigate to="/" replace />;
  }

  return children;
};

function App() {
  return (
    <div className="flex flex-col min-h-screen bg-background text-secondary dark:bg-dark-bg dark:text-dark-text transition-colors duration-300">
      
      {/* Navbar */}
      <Navbar />

      {/* Main Content */}
      <main className="flex-grow">
        <Routes>

          {/* ================= PUBLIC ROUTES ================= */}

          <Route path="/" element={<HomePage />} />

          <Route path="/jobs" element={<JobsPage />} />

          <Route path="/jobs/:id" element={<JobDetailPage />} />

          <Route path="/login" element={<LoginPage />} />

          <Route path="/register" element={<RegisterPage />} />

          {/* ================= APPLICANT ROUTES ================= */}

          <Route
            path="/dashboard"
            element={
              <ProtectedRoute allowedRoles={["applicant", "user"]}>
                <DashboardPage />
              </ProtectedRoute>
            }
          />

          {/* Redirect applications page */}
          <Route
            path="/applications"
            element={<Navigate to="/dashboard" replace />}
          />

          {/* ================= EMPLOYER ROUTES ================= */}

          <Route
            path="/employer"
            element={
              <ProtectedRoute allowedRoles={["employer"]}>
                <EmployerDashboard />
              </ProtectedRoute>
            }
          />

          <Route
            path="/post-job"
            element={
              <ProtectedRoute allowedRoles={["employer"]}>
                <PostJobPage />
              </ProtectedRoute>
            }
          />

          {/* ================= ADMIN ROUTES ================= */}

          <Route
            path="/admin"
            element={
              <ProtectedRoute allowedRoles={["admin"]}>
                <AdminDashboard />
              </ProtectedRoute>
            }
          />

          {/* ================= 404 ROUTE ================= */}

          <Route path="*" element={<Navigate to="/" replace />} />

        </Routes>
      </main>

      {/* Footer */}
      <Footer />
    </div>
  );
}

export default App;