import { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';
import { Link } from 'react-router-dom';
import applicationService from '../api/applicationService';
import './ApplicationsPage.css';

export default function ApplicationsPage() {
  const { user } = useAuth();
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (user) fetchApplications();
    else setLoading(false);
  }, [user]);

  const fetchApplications = async () => {
    try {
      const response = await applicationService.getByUser(user.id);
      setApplications(response.data);
    } catch (error) {
      console.error('Failed to fetch applications:', error);
    }
    setLoading(false);
  };

  if (!user) {
    return (
      <div className="page container">
        <div className="empty-state">
          <div className="empty-state-icon">🔒</div>
          <h3>Please login to view your applications</h3>
          <Link to="/login" className="btn btn-primary" style={{ marginTop: '1rem' }}>Login</Link>
        </div>
      </div>
    );
  }

  return (
    <div className="page container animate-fade-in-up">
      <div className="apps-header">
        <h1>My Applications</h1>
        <p>Track the status of your job applications</p>
      </div>

      {loading ? (
        <div className="loading-container"><div className="spinner"></div></div>
      ) : applications.length === 0 ? (
        <div className="empty-state">
          <div className="empty-state-icon">📋</div>
          <h3>No applications yet</h3>
          <p>Start applying for jobs to see your applications here</p>
          <Link to="/jobs" className="btn btn-primary" style={{ marginTop: '1rem' }}>Browse Jobs</Link>
        </div>
      ) : (
        <div className="applications-list">
          {applications.map(app => (
            <div key={app.id} className="card application-card" id={`app-${app.id}`}>
              <div className="app-card-header">
                <div>
                  <h3>
                    <Link to={`/jobs/${app.jobId}`}>{app.jobTitle}</Link>
                  </h3>
                  <p className="app-company">{app.companyName}</p>
                </div>
                <span className={`badge status-${app.status}`}>
                  {app.status}
                </span>
              </div>
              <div className="app-card-footer">
                <span className="app-date">Applied: {new Date(app.appliedAt).toLocaleDateString()}</span>
                {app.updatedAt !== app.appliedAt && (
                  <span className="app-date">Updated: {new Date(app.updatedAt).toLocaleDateString()}</span>
                )}
              </div>
              <div className="status-progress">
                {['submitted', 'reviewing', 'shortlisted', 'interview', 'offered'].map((step, idx) => (
                  <div key={step} className={`progress-step ${
                    ['submitted', 'reviewing', 'shortlisted', 'interview', 'offered'].indexOf(app.status) >= idx
                      ? 'active' : ''
                  }`}>
                    <div className="progress-dot"></div>
                    <span className="progress-label">{step}</span>
                  </div>
                ))}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
