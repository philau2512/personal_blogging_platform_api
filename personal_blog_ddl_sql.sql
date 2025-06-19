use personal_blog_db;

INSERT INTO category (id, name) VALUES
(1, 'Technology'),
(2, 'Health'),
(3, 'Travel'),
(4, 'Education'),
(5, 'Lifestyle');

INSERT INTO blog (id, author, content, create_date, modify_date, title, category_id) VALUES
(1, 'Alice', 'Intro to AI', NOW(), NOW(), 'Understanding AI Basics', 1),
(2, 'Bob', 'Healthy Eating Habits', NOW(), NOW(), 'Nutrition for Life', 2),
(3, 'Charlie', 'Exploring Paris', NOW(), NOW(), 'Top 10 Places in Paris', 3),
(4, 'Diana', 'Online Learning Tips', NOW(), NOW(), 'Effective eLearning', 4),
(5, 'Evan', 'Minimalist Living', NOW(), NOW(), 'Simple Life Hacks', 5),
(6, 'Frank', 'Cloud Technologies', NOW(), NOW(), 'Beginner’s Guide to Cloud', 1),
(7, 'Grace', 'Mental Health Awareness', NOW(), NOW(), 'Coping with Anxiety', 2),
(8, 'Hannah', 'Road Trip Essentials', NOW(), NOW(), 'Travel Light & Smart', 3),
(9, 'Ivan', 'Study Smarter', NOW(), NOW(), 'Time Management for Students', 4),
(10, 'Jane', 'Work-Life Balance', NOW(), NOW(), 'Living in Balance', 5),
(11, 'Kate', 'Machine Learning', NOW(), NOW(), 'Supervised vs. Unsupervised', 1),
(12, 'Leo', 'Fitness at Home', NOW(), NOW(), 'Home Workout Routines', 2),
(13, 'Mia', 'Hidden Beaches', NOW(), NOW(), 'Underrated Coastal Spots', 3),
(14, 'Nick', 'Online Degrees', NOW(), NOW(), 'Top 5 Platforms for Education', 4),
(15, 'Olivia', 'Interior Design', NOW(), NOW(), 'Modern Home Styles', 5);