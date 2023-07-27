drop database if exists prj321x_asm01;

create database prj321x_asm01;

use prj321x_asm01;

create table roles (
	id int not null auto_increment primary key,
    role_name varchar(255)
);

create table users (
	id int not null auto_increment primary key,
    address varchar(255) not null,
    email varchar(255) not null,
    full_name varchar(255) not null,
    note varchar(255),
    `password` varchar(255) not null,
    phone_number varchar(255) not null,
    `status` int not null,
    username varchar(255) not null,
    created datetime not null default now(),
    role_id int not null,
    foreign key(role_id) references roles (id)
);

create table donations (
	id int not null auto_increment primary key,
    `code` varchar(255) not null,
    created datetime not null default now(),
    `description` varchar(255) not null,
    start_date date not null,
    end_date date not null,
    `name` varchar(255) not null,
    money int not null default 0,
    organization_name varchar(255) not null,
    phone_number varchar(255) not null,
    `status` int not null
);

create table user_donations (
	id int not null auto_increment primary key,
    created date not null default (curdate()),
    money int not null,
    `name` varchar(255) not null,
    `status` int not null,
    `text` varchar(255),
    donation_id int,
    user_id int,
    foreign key(donation_id) references donations (id) on delete cascade,
    foreign key(user_id) references users (id) on delete cascade
);

insert into roles values(1, "ADMIN"), (2, "USER");

insert into users (address, email, full_name, `password`, phone_number,
				`status`, username, role_id)
			values ("12 Quang Trung", "admin@gmail.com", "Nguyễn Huy", "113", "0325164899",
					1, "huynguyen", 1),
                  ("21 Nguyễn Huệ", "ngoc@gmail.com", "Minh Ngọc", "115", "0328464852",
					0, "minhngoc", 2);
                    
insert into donations (`code`, `description`, start_date, end_date,
					`name`, organization_name, phone_number, `status`)
			values("IDE113", "Ủng hộ trẻ em miền núi", "2023-06-06",
					"2023-06-09", "Hỗ trợ trẻ em miền núi", "Hội từ thiện", "0345671148", 1);
                    
insert into user_donations (money, `name`, `status`, `text`, donation_id, user_id)
			values(1000000, "ngoc", 1, "Goodluck", 1, 2);
