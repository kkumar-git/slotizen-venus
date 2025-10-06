--liquibase formatted sql
--changeset kkumar:insert_department_jewel


--Occupational Therapy, Speech Therapy, Behaviour Therapy, Remedial Education Program, Social Skills Training, Play Therapy,
--Sensory Integration, Physiotherapy, Social Work, Clinical Psychology, Back to School, Child Guidance, ABAT,
--Developmental Paediatrics, Special Education,  Counseling, 
--OCCUPATIONAL THERAPY, PHYSIOTHERAPY, SPEECH LANGUAGE PATHOLOGY, SPECIAL EDUCATION, BEHAVIOUR THERAPY, ADMINISTRATION, JANITORIAL, ONLINE

--OCCUPATIONAL THERAPY
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
	department_id,
	created_at,
	updated_at)
values(nextval('business_service_id_seq'::regclass), 'Occupational Therapy', 'Occupational Therapy', '45', '0', 'consultation', 1, currval('departments_id_seq'::regclass), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--PHYSIOTHERAPY
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
	public.business_service
(id,
	"name",
	description,
	duration,
	price,
	category,
	business_id,
	department_id,
	created_at,
	updated_at)
values(nextval('business_service_id_seq'::regclass), 'Physiotherapy', 'Physiotherapy', '45', '0', 'consultation', 1, currval('departments_id_seq'::regclass), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--SPEECH LANGUAGE PATHOLOGY
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
	public.business_service
(id,
	"name",
	description,
	duration,
	price,
	category,
	business_id,
	department_id,
	created_at,
	updated_at)
values(nextval('business_service_id_seq'::regclass), 'Speech Language Pathology', 'Speech Language Pathology', '45', '0', 'consultation', 1, currval('departments_id_seq'::regclass), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--SPECIAL EDUCATION
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
	public.business_service
(id,
	"name",
	description,
	duration,
	price,
	category,
	business_id,
	department_id,
	created_at,
	updated_at)
values(nextval('business_service_id_seq'::regclass), 'Special Education', 'Special Education', '45', '0', 'consultation', 1, currval('departments_id_seq'::regclass), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--BEHAVIOUR THERAPY
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
	public.business_service
(id,
	"name",
	description,
	duration,
	price,
	category,
	business_id,
	department_id,
	created_at,
	updated_at)
values(nextval('business_service_id_seq'::regclass), 'Behaviour Therapy', 'Behaviour Therapy', '45', '0', 'consultation', 1, currval('departments_id_seq'::regclass), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--ADMINISTRATION
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

--JANITORIAL
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

-- ONLINE
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


