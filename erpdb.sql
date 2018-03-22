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
	role varchar(45) default 'USER' not null
)
;

create table users
(
	user_id int auto_increment
		primary key,
	name varchar(45) not null,
	email varchar(45) not null,
	password varchar(45) not null,
	enabled tinyint default '1' not null,
	role_id int not null,
	constraint role_id
		foreign key (role_id) references user_roles (role_id)
)
;

create index role_idx
	on users (role_id)
;
