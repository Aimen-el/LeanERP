create table upload
(
	id bigint auto_increment
		primary key,
	chemin varchar(255) null,
	dateupload timestamp null,
	etat bit null,
	motif varchar(255) null,
	name varchar(255) null,
	username varchar(255) null
)
;

create table user_roles
(
	role_id int auto_increment
		primary key,
	role varchar(45) default 'CONSULTANT' not null
)
;

create table users
(
	user_id int auto_increment
		primary key,
	email varchar(45) null,
	name varchar(45) null,
	password varchar(45) null,
	photo varchar(255) null,
	principal_id varchar(255) null,
	role_id int null,
	roles varchar(45) default 'CONSULTANT' not null,
	constraint FKh555fyoyldpyaltlb7jva35j2
		foreign key (role_id) references user_roles (role_id),
	constraint role_id
		foreign key (role_id) references user_roles (role_id)
)
;

create index role_id_idx
	on users (role_id)
;

