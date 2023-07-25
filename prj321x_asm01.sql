drop database if exists prj321x_asm01;

create database prj321x_asm01;

use prj321x_asm01;

create table `role`(
	id int(11) not null auto_increment primary key,
    role_name varchar(255)
);

create table `user`(
	id int(11) not null auto_increment primary key,
    address varchar(255) not null,
    email varchar(255) not null,
    full_name varchar(255) not null,
    note varchar(255),
    `password` varchar(255) not null,
    phone_number varchar(255) not null,
    `status` int not null,
    username varchar(255) not null,
    created datetime not null default now(),
    role_id int(11) not null,
    foreign key(role_id) references `role`(id)
);

create table donation(
	id int(11) not null auto_increment primary key,
    `code` varchar(255) not null,
    created datetime not null default now(),
    `description` varchar(255) not null,
    start_date date not null,
    end_date date not null,
    `name` varchar(255) not null,
    money int(11) not null default 0,
    organization_name varchar(255) not null,
    phone_number varchar(255) not null,
    `status` int(11) not null
);

create table user_donation(
	id int(11) not null auto_increment primary key,
    created date not null default (curdate()),
    money int(11) not null,
    `name` varchar(255) not null,
    `status` int(11) not null,
    `text` varchar(255),
    donation_id int(11),
    user_id int(11),
    foreign key(donation_id) references donation(id) on delete cascade,
    foreign key(user_id) references user(id) on delete cascade
);

insert into role values(1, "ADMIN"), (2, "USER");

insert into `user`(address, email, full_name, `password`, phone_number,
				`status`, username, role_id)
			values("12 Quang Trung", "luv2code@gmail.com", "Nguyễn Huy", "113", "0325164899",
					1, "huynguyen", 1),
                  ("21 Nguyễn Huệ", "ngoc@gmail.com", "Minh Ngọc", "115", "0328464852",
					0, "minhngoc", 2);
                    
insert into donation(`code`, `description`, start_date, end_date,
					`name`, organization_name, phone_number, `status`)
			values("IDE113", "Ủng hộ trẻ em miền núi", "2023-06-06",
					"2023-06-09", "Hỗ trợ trẻ em miền núi", "Hội từ thiện", "0345671148", 1);
                    
insert into user_donation(money, `name`, `status`, `text`, donation_id, user_id)
			values(1000000, "ngoc", 1, "Goodluck", 1, 2);
