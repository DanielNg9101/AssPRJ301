create database PlantShop
GO
use PlantShop
GO

ALTER TABLE Plants
ALTER COLUMN [imgPath] varchar(150)

create table Accounts(
 accID int identity(1,1)primary key,
 email varchar(30) unique,
 password varchar(30),
 fullname varchar(30),
 phone varchar(12),
 status int check(status =1 or status=0),-- 1:active; 0:inactive
 role int check(role=1 or role=0), --:admin, 0:user
 token varchar(50) default NULL
)

/*
DROP Table OrderDetails
DROP Table Orders
DROP Table Accounts
*/

go


GO
create table Categories(
 CateID int identity(1,1) primary key,
 CateName varchar(30)
)
GO



create table Plants (
 PID int identity(1,1) primary key,
 PName varchar(30),
 price int check(price>=0),
 imgPath varchar(150),
 description text,
 status int, --1:active, 0:inactive
 CateID int foreign key references Categories(CateID)
)
GO
create table Orders(
 OrderID int identity(1,1) primary key,
 OrdDate date,
 shipdate date,
 status int check(status =1 or status=2 or status=3),--1:processing, 2: completed, 3: cancel
 AccID int foreign key references [dbo].[Accounts]([accID])
)
GO
create table OrderDetails(
 DetailId int identity(1,1) primary key,
 OrderID int foreign key references Orders(OrderID),
 FID int foreign key references Plants(PID),
 quantity int check(quantity>=1)
 )

insert into [dbo].[Categories]
values ('orchild'),
	   ('roses'),
	   ('others')



insert into [dbo].[Plants]([PName], [price], [imgPath], [description], [status], [CateID])
values ('vanda', 100, 'images/img1.jpg', 'this is a venda flower', 1, 1),
       ('white rose', 90, 'images/img4.jpg', 'this is a rose', 1, 2),
       ('lan ho diep', 70, 'images/img2.jpg', 'hoa lan', 1, 1),
       ('lan hai', 90, 'images/img3.jpg', 'hoa lan', 1, 1),
       ('hoa hong cam', 90, 'images/img5.jpg', 'hoa hong', 1, 2),
       ('monstera', 90, 'images/img6.jpg', 'cay la kieng', 1, 3),
       ('var monstera', 90, 'images/img7.jpg', 'cay la kieng', 1, 3)
	   
insert into [dbo].[Accounts]([email], [password], [fullname], [phone], [status], [role])
values ('dac', '123', 'dac', '123456', 1, 0),
	   ('ha', '123', 'ha', '123456', 1, 0),
	   ('admin', '123', 'dac', '123456', 1, 1),
	   ('tam', '123', 'tam', '123456', 0, 0),
	   ('hao', '123', 'hao', '123456', 0, 1)

insert into dbo.[Orders]([OrdDate], [shipdate], [status], [AccID])
values ('2021-01-11', '2021-10-11', 2, 1),
	   ('2021-11-23', NULL, 1, 1),
	   ('2021-10-01', NULL, 3, 1)

insert into OrderDetails([OrderID], [FID], [quantity])
values (1, 1, 1),
	   (1, 2, 2),
	   (2, 5, 1),
	   (3, 7, 2),
	   (3, 6, 1)




Select [PID], [PName], [price], [imgPath], [description], [status], Plants.CateID as 'CateID', [CateName]
From [dbo].[Plants]	join [dbo].[Categories] on Plants.CateID = Categories.CateID
where Plants.PName like '%mon%'

Select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName
From Plants join Categories on Plants.CateID = Categories.CateID
Where Plants.PName Like '%mon%'

Select OrderID, OrdDate, shipdate, status, AccID
From Orders 
Where AccID = (Select [accID] 
			   From Accounts
			   Where email Like 'dac')
go

-- orders by category
Select OrderID, OrdDate, shipdate, status, AccID
From Orders 
Where AccID = (Select [accID] 
			   From Accounts
			   Where email Like 'dac')
and status = 2
go

--EXEC SP_RENAME '[OrderDetails].[PID]', 'FID', 'COLUMN' 
go

Select DetailId, OrderID, PID, PName, price, imgPath, quantity 
From OrderDetails, Plants
Where OrderID=1 and OrderDetails.FID = Plants.PID

select * 
from [dbo].[Orders]

Update [dbo].[Orders]
Set [status] = 2
Where [OrderID] = 1

Select top 1 OrderID
From Orders
Order By OrderID DESC

select * 
from [dbo].[Orders]
where [OrdDate] between '2021-01-11' and '2022-02-18' 
and [AccID] = 9

Select OrderID, OrdDate, shipdate, status, AccID
From Orders 
Where AccID = (Select [accID] 
			   From Accounts
			   Where email Like 'dac')
and [OrdDate] between '2021-01-11' and '2022-02-18'

insert into Plants([PName], [price], [imgPath], [description], [status], [CateID])
values ('Songoku', 2000, 'https://th.bing.com/th/id/R.9e2e832cf4bc2309470afab3f07c139c?rik=e4f2RNnU9fQEUw&pid=ImgRaw&r=0', 'songoku', 1, 2)



























insert into [Accounts] (email, password, fullname, phone, [status],  [role])
values ('dacng@gmail.com', '123', 'dac ng', '123456', 1, 0),
	   ('dani@gmail.com', '123', 'daniel ng', '123456', 1, 1)

--Select [accID], [email], [password], [fullname], [phone], [status], [role]
--From [dbo].[Accounts]
--Where email Like ? And password Like ? COLLATE SQL_Latin1_General_CP1_CS_AS

Select [accID], [email], [password], [fullname], [phone], [status], [role]
From [dbo].[Accounts]

Update [dbo].[Accounts]
Set [status] = 0 
Where [email] = 'dacng@gmail.com'

Update [dbo].[Accounts]
Set [password] = '123', [fullname] = '123', [phone] = '123'
Where [email] Like 'dacng@gmail.com' Delete [dbo].[Accounts] print''

print''

Delete [dbo].[Accounts]

Select accID
From Accounts
Where email Like 'dac'
