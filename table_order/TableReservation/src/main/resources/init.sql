

create database tableResrvation;

use tableResrvation;



create table resturant (
id int not null auto_increment,
name varchar(20) not null,
PRIMARY KEY (id)
);




create table tod (
id int not null auto_increment,
order_time int not null,
order_date date not null,
PRIMARY KEY (id)
);





create table table_in_resturant(
id int not null,
size int not null,
order_time int ,
resturant int not null,
PRIMARY KEY (id),
FOREIGN KEY (order_time) REFERENCES tod(id),
FOREIGN KEY (resturant) REFERENCES resturant(id)
);




create table orders(
id int not null auto_increment,
order_time int not null,
people_number int not null,
order_name varchar(20) not null,
telephone_number varchar(10) not null,
table_to_order int ,
PRIMARY KEY (id),
FOREIGN KEY (order_time) REFERENCES tod(id),
FOREIGN KEY (table_to_order) REFERENCES table_in_resturant(id)
);



insert into resturant (name) values ('mamaia');

insert into table_in_resturant(table_number,size,resturant) values(1,4,1);

insert into table_in_resturant(table_number,size,resturant) values(2,4,1);

insert into table_in_resturant(table_number,size,resturant) values(3,6,1);

insert into table_in_resturant(table_number,size,resturant) values(4,4,1);

insert into table_in_resturant(table_number,size,resturant) values(5,4,1);

insert into table_in_resturant(table_number,size,resturant) values(6,4,1);

insert into table_in_resturant(table_number,size,resturant) values(7,8,1);