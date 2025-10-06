--liquibase formatted sql
--changeset kkumar:insert_user_roles_jewel

insert
    into
    public.user_roles
(user_id, role_id)
values(currval('users_id_seq'::regclass), (select id from public.roles where name = 'ROLE_SUPER_ADMIN'));
