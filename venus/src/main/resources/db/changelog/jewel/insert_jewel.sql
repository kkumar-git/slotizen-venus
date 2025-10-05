--liquibase formatted sql
--changeset kkumar:insert_jewel

insert
	into
	public.users
(id,
	first_name,
	last_name,
	email,
	phone,
	"password",
	enabled,
	created_at,
	updated_at)
values(nextval('users_id_seq'::regclass), 'Jewel', 'SuperAdmin', 'jewel@superadmin.com', '+917025568086', '$2a$10$7uPOW2IYFDqMiGfDudBMT.OcUDDWpBhM3X2c6O5kYxBPBGIj9KxYi', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert
    into
    public.user_roles
(user_id, role)
values(currval('users_id_seq'::regclass), 'ROLE_SUPER_ADMIN');

insert
    into
    public.business_profile
(business_id,
    business_name,            
    logo_url,
    business_type,
    description,
    address,
    city,
    state,
    zip_code,
    phone,
    website,
    timezone,
    slug,
    active,
    competition_level,
    completed,
    created_at)
values(nextval('business_profile_business_id_seq'::regclass), 'Jewel Children with Autism & Rehabilitation Center - INDIA',
    'http://localhost:8081/static/user-image/user-image_16_20251005_145725.jpg',
    'therapy-and-rehabilitation-centre',
    'Jewel Autism Centre is a multi-speciality therapy clinic founded in 2008 specially designed to bridge the gaps in the developmental history of a child. Our centre offers the best autism treatment in India',
    'Cheriyapally Hospital Buildings, Ooppoottil Kavala',
    'Chalukunnu, Kottayam',
    'Kerala',
    '686001',
    '+9197454 51747',
    'https://www.jewelautismcentre.com/',
    'Asia/Kolkata',
    'jewel-autism-centre-india',
    true,
    5,
    true,
    CURRENT_TIMESTAMP);

    insert
    into
    public.business_profile
(business_id,
    business_name,            
    logo_url,
    business_type,
    description,
    address,
    city,
    state,
    zip_code,
    phone,
    website,
    timezone,
    slug,
    active,
    competition_level,
    completed,
    created_at)
values(nextval('business_profile_business_id_seq'::regclass), 'Jewel Child Developmental Centre',
    'http://localhost:8081/static/user-image/user-image_16_20251005_145725.jpg',
    'therapy-and-rehabilitation-centre',
    'Jewel Autism Centre is a multi-speciality therapy clinic founded in 2008 specially designed to bridge the gaps in the developmental history of a child. Our centre offers the best autism treatment in India',
    'Chandrasekharan Nair Road, Near Kendriya Bhavan',
    'CSEZ (P.O), Kakkanad',
    'Kerala',
    '682037',
    '+9197454 51747',
    'https://www.jewelautismcentre.com/',
    'Asia/Kolkata',
    'jewel-autism-centre',
    true,
    5,
    true,
    CURRENT_TIMESTAMP);

    insert
    into
    public.business_profile
(business_id,
    business_name,            
    logo_url,
    business_type,
    description,
    address,
    city,
    state,
    zip_code,
    phone,
    website,
    timezone,
    slug,
    active,
    competition_level,
    completed,
    created_at)
values(nextval('business_profile_business_id_seq'::regclass), 'Jewel Children with Autism & Rehabilitation Center - DUBAI',
    'http://localhost:8081/static/user-image/user-image_16_20251005_145725.jpg',
    'therapy-and-rehabilitation-centre',
    'Jewel Autism Centre is a multi-speciality therapy clinic founded in 2008 specially designed to bridge the gaps in the developmental history of a child. Our centre offers the best autism treatment in Dubai',
    'Room No. 106 1st Floor',
    'Galadari Mazda Showroom Sheik Zayed Road',
    'Dubai United Arab Emirates',
    '0',
    '+9197454 51747',
    'https://www.jewelautismcentre.com/',
    'Asia/Dubai',
    'jewel-autism-centre-dubai',
    true,
    5,
    true,
    CURRENT_TIMESTAMP);

    insert
    into
    public.business_profile
(business_id,
    business_name,            
    logo_url,
    business_type,
    description,
    address,
    city,
    state,
    zip_code,
    phone,
    website,
    timezone,
    slug,
    active,
    competition_level,
    completed,
    created_at)
values(nextval('business_profile_business_id_seq'::regclass), 'Jewel Child Developmental Centre',
    'http://localhost:8081/static/user-image/user-image_16_20251005_145725.jpg',
    'therapy-and-rehabilitation-centre',
    'Jewel Autism Centre is a multi-speciality therapy clinic founded in 2008 specially designed to bridge the gaps in the developmental history of a child. Our centre offers the best autism treatment in India',
    'Chandrasekharan Nair Road, Near Kendriya Bhavan',
    'CSEZ (P.O), Kakkanad',
    'Kerala',
    '682037',
    '+9197454 51747',
    'https://www.jewelautismcentre.com/',
    'Asia/Kolkata',
    'jewel-autism-centre',
    true,
    5,
    true,
    CURRENT_TIMESTAMP);

    insert
	into
	public.business_hours
(id,
	business_id,
	monday_is_open,
	monday_open_time,
	monday_close_time,
	tuesday_is_open,
	tuesday_open_time,
	tuesday_close_time,
	wednesday_is_open,
	wednesday_open_time,
	wednesday_close_time,
	thursday_is_open,
	thursday_open_time,
	thursday_close_time,
	friday_is_open,
	friday_open_time,
	friday_close_time,
	saturday_is_open,
	saturday_open_time,
	saturday_close_time,
	sunday_is_open,
	sunday_open_time,
	sunday_close_time)
values(nextval('business_hours_id_seq'::regclass), 1, true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30',  true, '08:15', '17:30', false, '', '');

insert
	into
	public.business_hours
(id,
	business_id,
	monday_is_open,
	monday_open_time,
	monday_close_time,
	tuesday_is_open,
	tuesday_open_time,
	tuesday_close_time,
	wednesday_is_open,
	wednesday_open_time,
	wednesday_close_time,
	thursday_is_open,
	thursday_open_time,
	thursday_close_time,
	friday_is_open,
	friday_open_time,
	friday_close_time,
	saturday_is_open,
	saturday_open_time,
	saturday_close_time,
	sunday_is_open,
	sunday_open_time,
	sunday_close_time)
values(nextval('business_hours_id_seq'::regclass), 2, true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30',  true, '08:15', '17:30', false, '', '');

insert
	into
	public.business_hours
(id,
	business_id,
	monday_is_open,
	monday_open_time,
	monday_close_time,
	tuesday_is_open,
	tuesday_open_time,
	tuesday_close_time,
	wednesday_is_open,
	wednesday_open_time,
	wednesday_close_time,
	thursday_is_open,
	thursday_open_time,
	thursday_close_time,
	friday_is_open,
	friday_open_time,
	friday_close_time,
	saturday_is_open,
	saturday_open_time,
	saturday_close_time,
	sunday_is_open,
	sunday_open_time,
	sunday_close_time)
values(nextval('business_hours_id_seq'::regclass), 3, true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30', true, '08:15', '17:30',  true, '08:15', '17:30', false, '', '');

--Occupational Therapy, Speech Therapy, Behaviour Therapy, Remedial Education Program, Social Skills Training, Play Therapy,
--Sensory Integration, Physiotherapy, Social Work, Clinical Psychology, Back to School, Child Guidance, ABAT,
--Developmental Paediatrics, Special Education,  Counseling, 
--OCCUPATIONAL THERAPY, PHYSIOTHERAPY, SPEECH LANGUAGE PATHOLOGY, SPECIAL EDUCATION, BEHAVIOUR THERAPY, ADMINISTRATION, JANITORIAL, ONLINE

insert
	into
	public.departments
(id,
	business_id,
	"name",
	description,
	color,
	is_active,
	created_at,
	updated_at)
values(nextval('departments_id_seq'::regclass), '1', 'OCCUPATIONAL THERAPY', 'Occupational therapy is skilled treatment that helps individuals to achieve independence in all facets of their lives.', '#3B82F6', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert
	into
	public.business_service
(id,
	"name",
	description,
	duration,
	price,
	category,
	business_id,
	created_at,
	updated_at,
	department_id)
values(nextval('business_service_id_seq'::regclass), 'Occupational Therapy', 'Occupational Therapy', '45', '0', 'consultation', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, currval('departments_id_seq'::regclass),);

insert
	into
	public.departments
(id,
	business_id,
	"name",
	description,
	color,
	is_active,
	created_at,
	updated_at)
values(nextval('departments_id_seq'::regclass), '1', 'PHYSIOTHERAPY', 'Physiotherapy is a treatment method that helps restore movement and function when someone is affected by injury, illness, or disability.', '#10B981', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert
	into
	public.departments
(id,
	business_id,
	"name",
	description,
	color,
	is_active,
	created_at,
	updated_at)
values(nextval('departments_id_seq'::regclass), '1', 'SPEECH LANGUAGE PATHOLOGY', 'Speech-language pathology is a field of expertise practiced by a clinician known as a speech-language pathologist (SLP).', '#F59E0B', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert
	into
	public.departments
(id,
	business_id,
	"name",
	description,
	color,
	is_active,
	created_at,
	updated_at)
values(nextval('departments_id_seq'::regclass), '1', 'SPECIAL EDUCATION', 'Special education is a branch of education that addresses the needs of students with disabilities.', '#EF4444', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


insert
	into
	public.departments
(id,
	business_id,
	"name",
	description,
	color,
	is_active,
	created_at,
	updated_at)
values(nextval('departments_id_seq'::regclass), '1', 'BEHAVIOUR THERAPY', 'Behaviour therapy is a type of psychotherapy that focuses on changing undesirable behaviors.', '#8B5CF6', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert
	into
	public.departments
(id,
	business_id,
	"name",
	description,
	color,
	is_active,
	created_at,
	updated_at)
values(nextval('departments_id_seq'::regclass), '1', 'ADMINISTRATION', 'Administration is the management of a business, organization, or institution.', '#EC4899', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert
	into
	public.departments
(id,
	business_id,
	"name",
	description,
	color,
	is_active,
	created_at,
	updated_at)
values(nextval('departments_id_seq'::regclass), '1', 'JANITORIAL', 'Janitorial services are essential for maintaining cleanliness and order in facilities.', '#06B6D4', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert
	into
	public.departments
(id,
	business_id,
	"name",
	description,
	color,
	is_active,
	created_at,
	updated_at)
values(nextval('departments_id_seq'::regclass), '1', 'ONLINE', 'Online services are essential for providing remote support and therapy.', '#F97316', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


