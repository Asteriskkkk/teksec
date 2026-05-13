-- Sample Data for Fitness Membership Management System
-- This script populates sample membership data for testing

INSERT INTO memberships (plan_name, membership_type, monthly_access_hours, launch_date, expiration_date, monthly_fee, diet_plan_opted, benefits) 
VALUES 
('Gold Plan', 'Premium', 300, '2024-01-01', '2025-01-01', 99.99, true, 'Unlimited gym access, personal trainer, swimming pool, sauna'),
('Silver Plan', 'Standard', 150, '2024-01-01', '2025-01-01', 49.99, false, 'Gym access, yoga classes'),
('Bronze Plan', 'Basic', 60, '2024-01-01', '2025-01-01', 29.99, false, 'Basic gym access, cardio equipment'),
('Platinum Plan', 'Premium', 400, '2024-01-15', '2025-01-15', 149.99, true, 'Unlimited access, 2 personal trainers, all facilities, nutrition consultation'),
('Family Plan', 'Family', 200, '2024-02-01', '2025-02-01', 149.99, true, 'Family gym access, kids program, personal trainer'),
('Student Plan', 'Student', 120, '2024-03-01', '2025-03-01', 19.99, false, 'Student gym access, fitness classes'),
('Corporate Plan', 'Corporate', 250, '2024-01-01', '2025-01-01', 79.99, true, 'Corporate gym access, team training sessions'),
('Elite Plan', 'Premium', 350, '2024-01-10', '2025-01-10', 119.99, true, 'Premium access, swimming pool, spa, nutrition plan');
