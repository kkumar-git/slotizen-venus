--liquibase formatted sql
--changeset kkumar:insert_user_jewel

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
