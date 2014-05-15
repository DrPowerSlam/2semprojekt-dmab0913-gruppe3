use dmab0913_3

IF OBJECT_ID('db_owner.City', 'U') IS NOT NULL
	DROP TABLE db_owner.City;
IF OBJECT_ID('db_owner.Breeder', 'U') IS NOT NULL
	DROP TABLE db_owner.Breeder;
IF OBJECT_ID('db_owner.Queen', 'U') IS NOT NULL
	DROP TABLE db_owner.Queen;
IF OBJECT_ID('db_owner.Compendium', 'U') IS NOT NULL
	DROP TABLE db_owner.Compendium;
IF OBJECT_ID('db_owner.Chart', 'U') IS NOT NULL
	DROP TABLE db_owner.Chart;
IF OBJECT_ID('db_owner.ChartQueens', 'U') IS NOT NULL
	DROP TABLE db_owner.ChartQueens;

create table City (
    zipCode  int not null, 
    city     varchar(15) not null,
    primary key (zipCode)
);

create table Breeder (
	breederID int IDENTITY(1,1) not null,
	fname varchar(50) not null,
	lname varchar(50) not null,
	address varchar(50) not null,
	zipCode int not null,
	phone varchar(15) not null,
	email varchar(50) not null,
	password varchar(50) not null,
	isAdmin varchar(50) not null,
	primary key (breederID),
	foreign key(zipCode) references City(zipCode),
);

INSERT INTO Breeder VALUES('Karsten', 'Ikke admin', 'Sofiendalsvej 60', '2500', '12345678', 'ikke@admin.dk', 'test123', 'false');

create table Queen (
	queenID  int  IDENTITY(1,1) not null, 
	year int not null,
	name varchar(50) not null,
	honeyYield int,
	swarmTendency int,
	nosema int,
	temper int,
	honeycombfirmness int,
	cleansingAbility int,
	isAlive varchar(10) not null,
	lParent int,
	rParent int, 
	breederID int,
    primary key (queenID),
	foreign key(lParent) references Queen(queenID),
	foreign key(rParent) references Queen(queenID),
	foreign key(breederID) references Breeder(breederID),
);

create table Compendium (
	compendiumID int IDENTITY(1,1) not null,
	name varchar(50) not null,
	date varchar(30) not null,
	primary key (compendiumID),
);

create table Chart (
	chartID  int  IDENTITY(1,1) not null, 
	breederID int not null,
	year int not null,
	honeyYield int not null,
	swarmTendency int not null,
	nosema int not null,
	temper int not null,
	honeycombfirmness int not null,
	cleansingAbility int not null,
	compendiumID int not null,
    primary key (chartID),
	foreign key(breederID) references Breeder(breederID),
	foreign key(compendiumID) references Compendium(compendiumID),
);


create table ChartQueens (
	chartID int not null,
	queenID int not null,
	primary key(chartID,queenID),
	foreign key(chartID) references Chart(chartID),
	foreign key(queenID) references Queen(queenID),
);