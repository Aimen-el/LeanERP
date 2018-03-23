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
	role varchar(45) default 'CONSULTANT' not null,
	users varchar(45) null
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
	role_id int default '2' not null,
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

create table users_roles
(
	user_id int not null,
	role_id int not null,
	constraint FK2o0jvgh89lemvvo17cbqvdxaa
		foreign key (user_id) references users (user_id),
	constraint FKfivrl5i32jkvd1w39y4h2vn90
		foreign key (role_id) references user_roles (role_id)
)
;

create index FK2o0jvgh89lemvvo17cbqvdxaa
	on users_roles (user_id)
;

create index FKfivrl5i32jkvd1w39y4h2vn90
	on users_roles (role_id)
;

