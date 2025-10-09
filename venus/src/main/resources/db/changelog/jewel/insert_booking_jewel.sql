--liquibase formatted sql
--changeset kkumar:insert_booking_jewel

INSERT INTO public.bookings
(id, business_id, client_id, service_id, staff_id, booking_date, booking_time, duration_minutes, status, price, notes, created_at, updated_at, created_by)
VALUES
(nextval('bookings_id_seq'::regclass), 1, 1, 1, 1, '2025-10-10', '08:15', 45, 'CONFIRMED', 700.00, 'Morning session for sensory activities.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 2, 1, 2, '2025-10-10', '09:00', 45, 'COMPLETED', 750.00, 'Motor coordination session.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 3, 1, 3, '2025-10-10', '09:45', 45, 'CONFIRMED', 700.00, 'Speech articulation exercises.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 4, 1, 4, '2025-10-10', '10:45', 45, 'CONFIRMED', 800.00, 'Therapy resumed after tea break.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 5, 1, 5, '2025-10-10', '11:30', 45, 'COMPLETED', 850.00, 'Focus on behavioral improvement.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 6, 1, 6, '2025-10-10', '12:15', 45, 'CONFIRMED', 750.00, 'Pre-lunch therapy session.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 7, 1, 7, '2025-10-10', '13:30', 45, 'COMPLETED', 700.00, 'Post-lunch fine motor activity.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 8, 1, 8, '2025-10-10', '14:15', 45, 'CONFIRMED', 850.00, 'Focus on hand–eye coordination.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 9, 1, 9, '2025-10-10', '15:00', 45, 'CONFIRMED', 900.00, 'Engagement through play therapy.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 10, 1, 10, '2025-10-10', '16:00', 45, 'COMPLETED', 800.00, 'Therapy resumed after evening break.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 11, 3, 26, '2025-10-11', '08:15', 45, 'CONFIRMED', 700.00, 'Morning articulation training.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 12, 3, 27, '2025-10-11', '09:00', 45, 'COMPLETED', 750.00, 'Speech rhythm exercises.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 13, 3, 28, '2025-10-11', '09:45', 45, 'CONFIRMED', 700.00, 'Language comprehension tasks.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 14, 3, 29, '2025-10-11', '10:45', 45, 'CONFIRMED', 800.00, 'Post-break communication training.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 15, 3, 30, '2025-10-11', '11:30', 45, 'COMPLETED', 850.00, 'Behavioral reinforcement plan.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 16, 3, 31, '2025-10-11', '12:15', 45, 'CONFIRMED', 750.00, 'Social interaction improvement.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 17, 3, 32, '2025-10-11', '13:30', 45, 'COMPLETED', 700.00, 'Post-lunch behavioral therapy.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 18, 4, 51, '2025-10-11', '14:15', 45, 'CONFIRMED', 850.00, 'Occupational fine motor activities.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 19, 4, 52, '2025-10-11', '15:00', 45, 'COMPLETED', 900.00, 'Task-based learning session.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(nextval('bookings_id_seq'::regclass), 1, 20, 5, 79, '2025-10-11', '16:00', 45, 'CONFIRMED', 800.00, 'Therapy concluded for the day.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
