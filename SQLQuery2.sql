select 
	a.[e_id],
	a.[e_first_name],
	a.[e_last_name],
	a.[e_gender],
	a.[e_dob],
	a.[e_email],
	a.[e_phone],
	a.[e_salary],
	a.[e_join_date],
	b.[department_id],
	b.[department_name],
	b.[manager_id],
	c.[job_id],
	c.[job_title],
	c.[min_salary],
	c.[max_salary],
	a.[location_id],
	a.[manager_id],
from Employees as a JOIN Departments as b ON a.[DepartmentID] = b.[DepartmentID]
					JOIN Jobs as c ON a.[JobID] = c.[JobID]
					JOIN Locations as d on a.[LocationId] = d.[LocationId]

select *from Employees


CREATE TABLE [Departments](
	[department_id] int identity primary key,
	[dapartment_name] [varchar](50) NULL,
	[manager_id] [int] NULL,
) 

/****** Object:  Table [dbo].[Employees]    Script Date: 22/10/2019 05:25:15 ******/
CREATE TABLE [Employees](
	[e_id] [int] IDENTITY PRIMARY KEY,
	[e_first_name] [varchar](30) NULL,
	[e_last_name] [varchar](30) NULL,
	[e_gender] [bit] DEFAULT 1,
	[e_dob] [date] not null,
	[e_email] [varchar](50) NULL,
	[e_phone] [varchar](20) NULL,
	[e_join_date] [date] NULL,
	[location_id] [int] NULL,
	[job_id] int NULL,
	[e_salary] [float] NULL,
	[manager_id] [int] NULL,
	[department_id] int NULL,
)

CREATE TABLE [dbo].[JobsHistory](
	[e_id] [int] NULL,
	[start_date] [date] NULL,
	[end_date] [date] NULL,
	[job_id] int NULL,
	[department_id] int NULL
) 

CREATE TABLE [Jobs](
	[job_id] int identity PRIMARY KEY,
	[job_title] [varchar](100) NULL,
	[min_salary] [int] NULL,
	[max_salary] [int] NULL,
) 
/****** Object:  Table [dbo].[Locations]    Script Date: 22/10/2019 05:25:16 ******/
CREATE TABLE [dbo].[Locations](
	[location_id] int identity primary key,
	[StreetAddress] [varchar](100) NULL,
	[Ward_id] [varchar](5) foreign key references Wards([Ward_id])
) 

ALTER TABLE [dbo].[Departments] ADD FOREIGN KEY([manager_id])
REFERENCES [dbo].[Employees] ([e_id])
GO
ALTER TABLE [dbo].[Employees] ADD FOREIGN KEY([department_id])
REFERENCES [dbo].[Departments] ([department_id])
GO
ALTER TABLE [dbo].[Employees] ADD FOREIGN KEY([job_id])
REFERENCES [dbo].[Jobs] ([job_id])
GO
ALTER TABLE [dbo].[Employees] ADD FOREIGN KEY([manager_id])
REFERENCES [dbo].[Employees] ([e_id])
GO
ALTER TABLE [dbo].[JobsHistory] ADD FOREIGN KEY([department_id])
REFERENCES [dbo].[Departments] ([department_id])
GO
ALTER TABLE [dbo].[JobsHistory] ADD FOREIGN KEY([e_id])
REFERENCES [dbo].[Employees] ([e_id])
GO
ALTER TABLE [dbo].[JobsHistory] ADD FOREIGN KEY([job_id])
REFERENCES [dbo].[Jobs] ([job_id])
GO
ALTER TABLE [dbo].[Employees] ADD FOREIGN KEY([location_id])
REFERENCES [dbo].[Locations] ([location_id])
GO