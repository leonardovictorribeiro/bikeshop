drop table if exists public.tools;
create table public.tools
(
    id serial not null,
    "name" varchar(250) not null,
    brand varchar(60) not null,
    "availability" varchar(12) default 'UNAVAILABLE' not null,
    created_at timestamp default Now() not null,
    updated_at timestamp default Now() not null,
    "version" int not null
);

alter table public.tools 
add constraint tools_pkey primary key(id);

alter table public.tools
add constraint availability_check
check ("availability" in ('UNAVAILABLE', 'AVAILABLE'));

drop sequence if exists tool_id_seq;
create sequence tool_id_seq;

alter table public.tools 
alter column id set default nextval('tool_id_seq');