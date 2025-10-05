--liquibase formatted sql
--changeset kkumar:insert_user_roles_jewel

insert
    into
    public.user_roles
(user_id, role)
values(currval('users_id_seq'::regclass), 'ROLE_SUPER_ADMIN');
