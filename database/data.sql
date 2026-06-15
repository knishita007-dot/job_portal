-- =============================================
-- Seed Data for Job Portal
-- =============================================

-- Skills
INSERT INTO skills (name) VALUES ('Java') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('Python') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('JavaScript') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('React') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('Node.js') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('Spring Boot') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('PostgreSQL') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('MongoDB') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('Docker') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('AWS') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('Kubernetes') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('TypeScript') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('Machine Learning') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('REST API') ON CONFLICT (name) DO NOTHING;
INSERT INTO skills (name) VALUES ('Git') ON CONFLICT (name) DO NOTHING;

-- Companies
INSERT INTO companies (name, address, industry, website) VALUES
('TechCorp Solutions', 'Bangalore, India', 'Information Technology', 'https://techcorp.example.com')
ON CONFLICT DO NOTHING;
INSERT INTO companies (name, address, industry, website) VALUES
('CloudNine Technologies', 'Hyderabad, India', 'Cloud Computing', 'https://cloudnine.example.com')
ON CONFLICT DO NOTHING;
INSERT INTO companies (name, address, industry, website) VALUES
('DataMinds Analytics', 'Mumbai, India', 'Data Science', 'https://dataminds.example.com')
ON CONFLICT DO NOTHING;
INSERT INTO companies (name, address, industry, website) VALUES
('WebStack Innovations', 'Pune, India', 'Web Development', 'https://webstack.example.com')
ON CONFLICT DO NOTHING;
INSERT INTO companies (name, address, industry, website) VALUES
('SecureNet Systems', 'Chennai, India', 'Cybersecurity', 'https://securenet.example.com')
ON CONFLICT DO NOTHING;

-- Users
INSERT INTO users (email, password, full_name, role, phone) VALUES
('employer@techcorp.com', 'password123', 'Rahul Sharma', 'employer', '+91-9876543210')
ON CONFLICT (email) DO NOTHING;
INSERT INTO users (email, password, full_name, role, phone) VALUES
('hr@cloudnine.com', 'password123', 'Priya Patel', 'employer', '+91-9876543211')
ON CONFLICT (email) DO NOTHING;
INSERT INTO users (email, password, full_name, role, phone) VALUES
('applicant1@gmail.com', 'password123', 'Amit Kumar', 'applicant', '+91-9876543212')
ON CONFLICT (email) DO NOTHING;
INSERT INTO users (email, password, full_name, role, phone) VALUES
('applicant2@gmail.com', 'password123', 'Sneha Reddy', 'applicant', '+91-9876543213')
ON CONFLICT (email) DO NOTHING;
INSERT INTO users (email, password, full_name, role, phone) VALUES
('admin@jobportal.com', 'admin123', 'Admin User', 'admin', '+91-9876543200')
ON CONFLICT (email) DO NOTHING;

-- Jobs
INSERT INTO jobs (title, company_id, location, salary_min, salary_max, job_type, experience, posted_by, is_active) VALUES
('Senior Java Developer', 1, 'Bangalore, India', 1500000, 2500000, 'full-time', '3-5 years', 1, true)
ON CONFLICT DO NOTHING;
INSERT INTO jobs (title, company_id, location, salary_min, salary_max, job_type, experience, posted_by, is_active) VALUES
('Cloud DevOps Engineer', 2, 'Remote', 1200000, 2000000, 'remote', '2-4 years', 2, true)
ON CONFLICT DO NOTHING;
INSERT INTO jobs (title, company_id, location, salary_min, salary_max, job_type, experience, posted_by, is_active) VALUES
('Junior React Developer', 4, 'Pune, India', 600000, 1000000, 'full-time', '0-1 years', 1, true)
ON CONFLICT DO NOTHING;
INSERT INTO jobs (title, company_id, location, salary_min, salary_max, job_type, experience, posted_by, is_active) VALUES
('Data Scientist', 3, 'Mumbai, India', 1800000, 3000000, 'full-time', '3-6 years', 2, true)
ON CONFLICT DO NOTHING;
INSERT INTO jobs (title, company_id, location, salary_min, salary_max, job_type, experience, posted_by, is_active) VALUES
('Python Backend Engineer', 1, 'Hyderabad, India', 1000000, 1800000, 'full-time', '2-3 years', 1, true)
ON CONFLICT DO NOTHING;
INSERT INTO jobs (title, company_id, location, salary_min, salary_max, job_type, experience, posted_by, is_active) VALUES
('Cybersecurity Analyst', 5, 'Chennai, India', 900000, 1500000, 'full-time', '1-3 years', 2, true)
ON CONFLICT DO NOTHING;
INSERT INTO jobs (title, company_id, location, salary_min, salary_max, job_type, experience, posted_by, is_active) VALUES
('Full Stack Intern', 4, 'Remote', 300000, 500000, 'internship', '0 years', 1, true)
ON CONFLICT DO NOTHING;
INSERT INTO jobs (title, company_id, location, salary_min, salary_max, job_type, experience, posted_by, is_active) VALUES
('ML Engineer', 3, 'Bangalore, India', 2000000, 3500000, 'full-time', '4-7 years', 2, true)
ON CONFLICT DO NOTHING;

-- Job-Skills associations
INSERT INTO job_skills (job_id, skill_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (1, 6) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (1, 7) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (1, 14) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (2, 9) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (2, 10) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (2, 11) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (3, 3) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (3, 4) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (3, 12) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (4, 2) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (4, 13) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (5, 2) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (5, 14) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (5, 8) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (6, 2) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (6, 10) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (7, 3) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (7, 4) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (7, 5) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (8, 2) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (8, 13) ON CONFLICT DO NOTHING;
INSERT INTO job_skills (job_id, skill_id) VALUES (8, 9) ON CONFLICT DO NOTHING;

-- Sample applications
INSERT INTO applications (job_id, user_id, status, resume_url, cover_letter) VALUES
(1, 3, 'reviewing', 'https://storage.example.com/resumes/amit_resume.pdf', 'I am excited to apply for the Senior Java Developer position...')
ON CONFLICT DO NOTHING;
INSERT INTO applications (job_id, user_id, status, resume_url, cover_letter) VALUES
(3, 4, 'submitted', 'https://storage.example.com/resumes/sneha_resume.pdf', 'As a recent graduate with strong React skills...')
ON CONFLICT DO NOTHING;
INSERT INTO applications (job_id, user_id, status, resume_url, cover_letter) VALUES
(2, 3, 'shortlisted', 'https://storage.example.com/resumes/amit_resume.pdf', 'With my experience in AWS and Docker...')
ON CONFLICT DO NOTHING;
