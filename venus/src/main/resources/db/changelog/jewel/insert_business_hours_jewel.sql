--liquibase formatted sql
--changeset kkumar:insert_business_hours_jewel

--Kottyam, Kerala, India
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

--Kakkanad, Kerala, India
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

--Dubai, United Arab Emirates
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
