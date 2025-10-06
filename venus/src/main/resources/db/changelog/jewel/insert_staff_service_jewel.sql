--liquibase formatted sql
--changeset kkumar:insert_business_staff_member_jewel

 --"Manager","Therapist","Technician","Receptionist","Assistant","Administrator",
 --"Developer","Designer","Support","Other","Behaviour Therapist", "Physiotherapist", "Speech Therapist", "Occupational Therapist", "Special Education", "Counselor", "Psychologist", "Social Worker", "Janitor", "Online Support"

--OCCUPATIONAL THERAPY
INSERT INTO public.business_staff_members
(id, first_name, last_name, email, phone, "role", status, business_id, department_id, hire_date, avatar, created_at, updated_at)
VALUES
(nextval('business_staff_members_id_seq'::regclass), 'Chinnu', 'Eldose', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Deepasri', 'C', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Anu', 'Mathew', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Elakiya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Divyalakshmi', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Ashby', 'Anna George', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Manoj', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Ashna', 'M Johny', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Anija', 'K Mohan', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Saritha', 'C P', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Nikethana', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Sethuhemalekshmi', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Pavithra', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Alwin', 'Samuel', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Liya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Anjaly', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Haran', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Hemanth', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Meethu', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Hishaana', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Swetha', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Swarna', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Asha', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Alfiyamol', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Ambily', 'Prasad', 'jewelcentreforautism@gmail.com', '+919846565524', 'Therapist', 'ACTIVE', 1, 1, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.business_staff_services (staff_id, service_id)
VALUES
(1, 1),(2, 1),(3, 1),(4, 1),(5, 1),(6, 1),(7, 1),(8, 1),(9, 1),(10, 1),
(11, 1),(12, 1),(13, 1),(14, 1),(15, 1),(16, 1),(17, 1),(18, 1),(19, 1),(20, 1),
(21, 1),(22, 1),(23, 1),(24, 1),(25, 1);

--PHYSIOTHERAPY
INSERT INTO public.business_staff_members
(id, first_name, last_name, email, phone, "role", status, business_id, department_id, hire_date, avatar, created_at, updated_at)
VALUES
(nextval('business_staff_members_id_seq'::regclass), 'Ance', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Physiotherapist', 'ACTIVE', 1, 2, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.business_staff_services (staff_id, service_id)
VALUES
(26, 2);
--SPEECH LANGUAGE PATHOLOGY
INSERT INTO public.business_staff_members
(id, first_name, last_name, email, phone, "role", status, business_id, department_id, hire_date, avatar, created_at, updated_at)
VALUES
(nextval('business_staff_members_id_seq'::regclass), 'Amalu', 'Vincent', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Anu', 'Reji', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Naimy', 'Abhraham', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Ashly', 'Joseph', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Trisa', 'Joji', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Anitta', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Ameya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Aparna', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Liji', 'Elizabeth', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Christeena', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Sifna', 'Thajudheen', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Jinimol', 'Thomas', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Ishika', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Sujith', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Joshma', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Fathima', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Amina', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Tiya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Rena', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Seyda', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Aleena', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Gifty', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Chris', 'Tom', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Jiya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Speech Therapist', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.business_staff_services (staff_id, service_id)
VALUES
(27, 3),(28, 3),(29, 3),(30, 3),(31, 3),(32, 3),(33, 3),(34, 3),(35, 3),(36, 3),
(37, 3),(38, 3),(39, 3),(40, 3),(41, 3),(42, 3),(43, 3),(44, 3),(45, 3),(46, 3),
(47, 3),(48, 3),(49, 3),(50, 3);

--SPECIAL EDUCATION
INSERT INTO public.business_staff_members
(id, first_name, last_name, email, phone, "role", status, business_id, department_id, hire_date, avatar, created_at, updated_at)
VALUES
(nextval('business_staff_members_id_seq'::regclass), 'Adithya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Johncy', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Raji', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Arya', 'O', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Anupama', 'P A', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Sandra', 'P S', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Vidya', 'P', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Josna', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Vidhya', 'T', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Drishya', 'K M', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Riya', 'Thomas', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Neethu', 'N', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Nandana', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Sunitha', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Neeraja', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Sandra', 'Ms', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Neeraja', 'Prasad', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Archa', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Krishnaveni', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Parvathy', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Dhanuja', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Aiswarya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Abhirami', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Akshaya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Abina', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Sangeetha', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Athira', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Arathy', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Special Education', 'ACTIVE', 1, 4, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.business_staff_services (staff_id, service_id)
VALUES
(51, 4),(52, 4),(53, 4),(54, 4),(55, 4),(56, 4),(57, 4),(58, 4),(59, 4),(60, 4),
(61, 4),(62, 4),(63, 4),(64, 4),(65, 4),(66, 4),(67, 4),(68, 4),(69, 4),(70, 4),
(71, 4),(72, 4),(73, 4),(74, 4),(75, 4),(76, 4),(77, 4),(78, 4);

--BEHAVIOUR THERAPY
INSERT INTO public.business_staff_members 
(id, first_name, last_name, email, phone, "role", status, business_id, department_id, hire_date, avatar, created_at, updated_at) 
VALUES
(nextval('business_staff_members_id_seq'::regclass), 'Dona', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Behaviour Therapist', 'ACTIVE', 1, 5, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Aina', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Behaviour Therapist', 'ACTIVE', 1, 5, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Fiya', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Behaviour Therapist', 'ACTIVE', 1, 5, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Poornima', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Behaviour Therapist', 'ACTIVE', 1, 5, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Sajitha', 'Nair', 'jewelcentreforautism@gmail.com', '+919846565524', 'Behaviour Therapist', 'ACTIVE', 1, 5, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Azna', 'Ansari', 'jewelcentreforautism@gmail.com', '+919846565524', 'Behaviour Therapist', 'ACTIVE', 1, 5, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.business_staff_services (staff_id, service_id)
VALUES
(79, 5),(80, 5),(81, 5),(82, 5),(83, 5),(84, 5);


--ADMINISTRATION
INSERT INTO public.business_staff_members
(id, first_name, last_name, email, phone, "role", status, business_id, department_id, hire_date, avatar, created_at, updated_at)
VALUES
(nextval('business_staff_members_id_seq'::regclass), 'Febin', 'Benny', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Neethu', 'Retnakumar', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Maria', 'Sibi', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Anju', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Kavitha', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Aswathy', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Fathima', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Rajimol', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Stephy', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Aleena', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Namitha', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Hasna', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Administrator', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--JANITORIAL
INSERT INTO public.business_staff_members
(id, first_name, last_name, email, phone, "role", status, business_id, department_id, hire_date, avatar, created_at, updated_at)
VALUES
(nextval('business_staff_members_id_seq'::regclass), 'Punnose', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Janitor', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Usha', 'Joshy', 'jewelcentreforautism@gmail.com', '+919846565524', 'Janitor', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Rejeena', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Janitor', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Akhil', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Janitor', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Geemon', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Janitor', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Mini', 'Rajan', 'jewelcentreforautism@gmail.com', '+919846565524', 'Janitor', 'ACTIVE', 1, 3, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--ONLINE
INSERT INTO public.business_staff_members
(id, first_name, last_name, email, phone, "role", status, business_id, department_id, hire_date, avatar, created_at, updated_at)
VALUES
(nextval('business_staff_members_id_seq'::regclass), 'Nikil', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Developer', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Jesbin', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Developer', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(nextval('business_staff_members_id_seq'::regclass), 'Nazal', '', 'jewelcentreforautism@gmail.com', '+919846565524', 'Developer', 'ACTIVE', 1, 6, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);