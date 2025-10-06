--liquibase formatted sql
--changeset kkumar:insert_user_business_jewel

--Kottyam, Kerala, India
INSERT INTO public.user_business
(user_id, business_id)
VALUES(1, 1); --jewel@superadmin.com

--Kakkanad, Kerala, India
INSERT INTO public.user_business
(user_id, business_id)
VALUES(1, 2); --jewel@superadmin.com

--Dubai, UAE
INSERT INTO public.user_business
(user_id, business_id)
VALUES(1, 3); --jewel@superadmin.com
