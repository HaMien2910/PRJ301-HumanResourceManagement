select 
	a.[EmployeeID], 
	a.[FirstName], 
	a.[LastName], 
	a.[Gender], 
	a.[DOB] ,
	a.[Phone], 
	a.[Email], 
	a.[HireDate], 
	a.salary, 
	b.[DepartmentID], 
	b.[DepartmentName],
	b.[ManagerID], 
	c.[JobID], 
	c.[JobTitle], 
	c.min_salary, 
	c.max_salary, 
	d.LocationID, 
	d.Province_id, 
	d.District_id, 
	d.ward_id, 
	d.streetAddress,
	a.[ManagerID]
from Employees as a JOIN Departments as b ON a.[DepartmentID] = b.[DepartmentID]
					JOIN Jobs as c ON a.[JobID] = c.[JobID]
					JOIN Locations as d on a.[LocationId] = d.[LocationId]



CREATE TABLE [dbo].[Departments](
	[DepartmentID] [varchar](20) primary key,
	[DepartmentName] [varchar](50) NULL,
	[ManagerID] [int] NULL,
) 

drop table [Departments]
/****** Object:  Table [dbo].[Employees]    Script Date: 22/10/2019 05:25:15 ******/
CREATE TABLE [dbo].[Employees](
	[EmployeeID] [int] IDENTITY PRIMARY KEY,
	[FirstName] [varchar](30) NULL,
	[LastName] [varchar](30) NULL,
	[Gender] [bit] DEFAULT 1,
	[DOB] [date] not null,
	[Email] [varchar](50) NULL,
	[Phone] [varchar](20) NULL,
	[HireDate] [date] NULL,
	[LocationId] [int] NULL,
	[JobID] [varchar](20) NULL,
	[Salary] [float] NULL,
	[ManagerID] [int] NULL,
	[DepartmentID] [varchar](20) NULL,
)

CREATE TABLE [dbo].[JobHistory](
	[EmployeeID] [int] NULL,
	[Start_date] [date] NULL,
	[End_date] [date] NULL,
	[JobID] [varchar](20) NULL,
	[DepartmentID] [varchar](20) NULL
) 

CREATE TABLE [dbo].[Jobs](
	[JobID] [varchar](20) PRIMARY KEY,
	[JobTitle] [varchar](100) NULL,
	[min_salary] [int] NULL,
	[max_salary] [int] NULL,
) 
/****** Object:  Table [dbo].[Locations]    Script Date: 22/10/2019 05:25:16 ******/
CREATE TABLE [dbo].[Locations](
	[LocationID] int identity primary key,
	[Province_id] [varchar](5) foreign key references tinhthanhpho(matp),
	[District_id] [varchar](5) foreign key references quanhuyen(maqh),
	[Ward_id] [varchar](5) foreign key references xaphuongthitran(xaid),
	[StreetAddress] [varchar](100) NULL,
) 

ALTER TABLE [dbo].[Departments] ADD FOREIGN KEY([ManagerID])
REFERENCES [dbo].[Employees] ([EmployeeID])
GO
ALTER TABLE [dbo].[Employees] ADD FOREIGN KEY([DepartmentID])
REFERENCES [dbo].[Departments] ([DepartmentID])
GO
ALTER TABLE [dbo].[Employees] ADD FOREIGN KEY([JobID])
REFERENCES [dbo].[Jobs] ([JobID])
GO
ALTER TABLE [dbo].[Employees] ADD FOREIGN KEY([ManagerID])
REFERENCES [dbo].[Employees] ([EmployeeID])
GO
ALTER TABLE [dbo].[JobHistory] ADD FOREIGN KEY([DepartmentID])
REFERENCES [dbo].[Departments] ([DepartmentID])
GO
ALTER TABLE [dbo].[JobHistory] ADD FOREIGN KEY([EmployeeID])
REFERENCES [dbo].[Employees] ([EmployeeID])
GO
ALTER TABLE [dbo].[JobHistory] ADD FOREIGN KEY([JobID])
REFERENCES [dbo].[Jobs] ([JobID])
GO
ALTER TABLE [dbo].[Employees] ADD FOREIGN KEY([LocationID])
REFERENCES [dbo].[Locations] ([LocationID])
GO