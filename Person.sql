use master
go

	--int id;
	--String name;
	--boolean favorit;	
	--int hairColor;		
	--String address;
	--String phone;
	--String note;


create database PersonApiDB
go

use PersonApiDB

create table Person
(
	id int identity (1000,1) primary key,
	[name] nvarchar(25),
	favorit bit,
	hairColor int,
	[address] nvarchar(35),
	phone nvarchar(12),
	note nvarchar(50)
)
go


insert into Person values('Jan', 0, 3, 'vej 5A', '003232327', 'Noget om mig')
insert into Person values('Ronald', 1, 5, 'vej', '0032325678', 'Noget om mig')
insert into Person values('Linette', 1, 2, 'vej7', '0232555', 'Noget om mig')
insert into Person values('Jannie', 1, 5, 'vej', '003333333', 'Noget om mig')
insert into Person values('Martin', 0, 1, 'vej 34', '002344324327', 'Noget om mig')
insert into Person values('Asger', 1, 1, 'vej 32', '0032423627', 'Noget om mig')
insert into Person values('Marc', 0, 4, 'vej 35', '0045233627', 'Noget om mig')
insert into Person values('Patrick', 0, 3, 'vej 109', '004532627', 'Noget om mig')

select * from Person