use dmab0913_3

IF OBJECT_ID('db_owner.PartChart', 'U') IS NOT NULL
	DROP TABLE db_owner.PartChart;
IF OBJECT_ID('db_owner.Chart', 'U') IS NOT NULL
	DROP TABLE db_owner.Chart;
IF OBJECT_ID('db_owner.Compendium', 'U') IS NOT NULL
	DROP TABLE db_owner.Compendium;
IF OBJECT_ID('db_owner.Queen', 'U') IS NOT NULL
	DROP TABLE db_owner.Queen;
IF OBJECT_ID('db_owner.Breeder', 'U') IS NOT NULL
	DROP TABLE db_owner.Breeder;
IF OBJECT_ID('db_owner.City', 'U') IS NOT NULL
	DROP TABLE db_owner.City;

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
	mother int,
	fathersMother int, 
	breederID int,
    	primary key (queenID),
	foreign key(mother) references Queen(queenID),
	foreign key(fathersMother) references Queen(queenID),
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
	pedigree varchar(255) not null,
	type varchar(50) not null,
	compendiumID int not null,
    	primary key (chartID),
	foreign key(breederID) references Breeder(breederID),
	foreign key(compendiumID) references Compendium(compendiumID),
);

create table PartChart (
	partChartID  int  IDENTITY(1,1) not null, 
	honeyYield int not null,
	honeyYieldYear int not null,
	swarmTendency int not null,
	nosema int not null,
	temper int not null,
	honeycombfirmness int not null,
	cleansingAbility int not null,
	chartID int not null,
	queenID int not null
    	primary key (partChartID),
	foreign key(chartID) references Chart(chartID),
	foreign key(queenID) references Queen(queenID),
);





INSERT INTO City VALUES(800, 'Høje Taastrup');
INSERT INTO City VALUES(877, 'Valby');
INSERT INTO City VALUES(900, 'København C');
INSERT INTO City VALUES(1000, 'København K');
INSERT INTO City VALUES(1505, 'København V');
INSERT INTO City VALUES(1800, 'Frederiksberg C');
INSERT INTO City VALUES(2000, 'Frederiksberg');
INSERT INTO City VALUES(2100, 'København ');
INSERT INTO City VALUES(2200, 'København N');
INSERT INTO City VALUES(2300, 'København S');
INSERT INTO City VALUES(2400, 'København NV');
INSERT INTO City VALUES(2450, 'København SV');
INSERT INTO City VALUES(2500, 'Valby');
INSERT INTO City VALUES(2600, 'Glostrup');
INSERT INTO City VALUES(2605, 'Br?ndby');
INSERT INTO City VALUES(2610, 'R?dovre');
INSERT INTO City VALUES(2620, 'Albertslund');
INSERT INTO City VALUES(2625, 'Vallensb');
INSERT INTO City VALUES(2630, 'Taastrup');
INSERT INTO City VALUES(2633, 'Taastrup');
INSERT INTO City VALUES(2635, 'Ish?j');
INSERT INTO City VALUES(2640, 'Hedehusene');
INSERT INTO City VALUES(2650, 'Hvidovre');
INSERT INTO City VALUES(2660, 'Br?ndby Strand');
INSERT INTO City VALUES(2665, 'Vallensb?k Strand');
INSERT INTO City VALUES(2670, 'Greve');
INSERT INTO City VALUES(2680, 'Solr?d Strand');
INSERT INTO City VALUES(2690, 'Karlslunde');
INSERT INTO City VALUES(2700, 'Br?nsh?j');
INSERT INTO City VALUES(2720, 'Vanl?se');
INSERT INTO City VALUES(2730, 'Herlev');
INSERT INTO City VALUES(2740, 'Skovlunde');
INSERT INTO City VALUES(2750, 'Ballerup');
INSERT INTO City VALUES(2760, 'M?l?v');
INSERT INTO City VALUES(2765, 'Sm?rum');
INSERT INTO City VALUES(2770, 'Kastrup');
INSERT INTO City VALUES(2791, 'Drag?r');
INSERT INTO City VALUES(2800, 'Kongens Lyngby');
INSERT INTO City VALUES(2820, 'Gentofte');
INSERT INTO City VALUES(2830, 'Virum');
INSERT INTO City VALUES(2840, 'Holte');
INSERT INTO City VALUES(2850, 'N?rum');
INSERT INTO City VALUES(2860, 'S?borg');
INSERT INTO City VALUES(2880, 'Bagsv?rd');
INSERT INTO City VALUES(2900, 'Hellerup');
INSERT INTO City VALUES(2920, 'Charlottenlund');
INSERT INTO City VALUES(2930, 'Klampenborg');
INSERT INTO City VALUES(2942, 'Skodsborg');
INSERT INTO City VALUES(2950, 'Vedb');
INSERT INTO City VALUES(2960, 'Rungsted Kyst');
INSERT INTO City VALUES(2970, 'H?rsholm');
INSERT INTO City VALUES(2980, 'Kokkedal');
INSERT INTO City VALUES(2990, 'Niv');
INSERT INTO City VALUES(3000, 'Helsing?r');
INSERT INTO City VALUES(3050, 'Humleb');
INSERT INTO City VALUES(3060, 'Esperg?rde');
INSERT INTO City VALUES(3070, 'Snekkersten');
INSERT INTO City VALUES(3080, 'Tik?b');
INSERT INTO City VALUES(3100, 'Hornb');
INSERT INTO City VALUES(3120, 'Dronningm?lle');
INSERT INTO City VALUES(3140, '?lsg?rde');
INSERT INTO City VALUES(3150, 'Helleb');
INSERT INTO City VALUES(3200, 'Helsinge');
INSERT INTO City VALUES(3210, 'Vejby');
INSERT INTO City VALUES(3220, 'Tisvildeleje');
INSERT INTO City VALUES(3230, 'Gr?sted');
INSERT INTO City VALUES(3250, 'Gilleleje');
INSERT INTO City VALUES(3300, 'Frederiksv?rk');
INSERT INTO City VALUES(3310, '?lsted');
INSERT INTO City VALUES(3320, 'Sk?vinge');
INSERT INTO City VALUES(3330, 'G?rl?se');
INSERT INTO City VALUES(3360, 'Liseleje');
INSERT INTO City VALUES(3370, 'Melby');
INSERT INTO City VALUES(3390, 'Hundested');
INSERT INTO City VALUES(3400, 'Hiller?d');
INSERT INTO City VALUES(3450, 'Aller?d');
INSERT INTO City VALUES(3460, 'Birker?d');
INSERT INTO City VALUES(3480, 'Fredensborg');
INSERT INTO City VALUES(3490, 'Kvistg?rd');
INSERT INTO City VALUES(3500, 'V?rl?se');
INSERT INTO City VALUES(3520, 'Farum');
INSERT INTO City VALUES(3540, 'Lynge');
INSERT INTO City VALUES(3550, 'Slangerup');
INSERT INTO City VALUES(3600, 'Frederikssund');
INSERT INTO City VALUES(3630, 'J?gerspris');
INSERT INTO City VALUES(3650, '?lstykke');
INSERT INTO City VALUES(3660, 'Stenl?se');
INSERT INTO City VALUES(3670, 'Veks? Sj?lland');
INSERT INTO City VALUES(3700, 'R?nne');
INSERT INTO City VALUES(3720, 'Aakirkeby');
INSERT INTO City VALUES(3730, 'Nex?');
INSERT INTO City VALUES(3740, 'Svaneke');
INSERT INTO City VALUES(3751, '?stermarie');
INSERT INTO City VALUES(3760, 'Gudhjem');
INSERT INTO City VALUES(3770, 'Allinge');
INSERT INTO City VALUES(3782, 'Klemensker');
INSERT INTO City VALUES(3790, 'Hasle');
INSERT INTO City VALUES(4000, 'Roskilde');
INSERT INTO City VALUES(4040, 'Jyllinge');
INSERT INTO City VALUES(4050, 'Skibby');
INSERT INTO City VALUES(4060, 'Kirke S?by');
INSERT INTO City VALUES(4070, 'Kirke Hyllinge');
INSERT INTO City VALUES(4100, 'Ringsted');
INSERT INTO City VALUES(4105, 'Ringsted');
INSERT INTO City VALUES(4130, 'Viby Sj?lland');
INSERT INTO City VALUES(4140, 'Borup');
INSERT INTO City VALUES(4160, 'Herlufmagle');
INSERT INTO City VALUES(4171, 'Glums?');
INSERT INTO City VALUES(4173, 'Fjenneslev');
INSERT INTO City VALUES(4174, 'Jystrup Midtsj');
INSERT INTO City VALUES(4180, 'Sor?');
INSERT INTO City VALUES(4190, 'Munke Bjergby');
INSERT INTO City VALUES(4200, 'Slagelse');
INSERT INTO City VALUES(4220, 'Kors?r');
INSERT INTO City VALUES(4230, 'Sk?lsk?r');
INSERT INTO City VALUES(4241, 'Vemmelev');
INSERT INTO City VALUES(4242, 'Boeslunde');
INSERT INTO City VALUES(4243, 'Rude');
INSERT INTO City VALUES(4250, 'Fuglebjerg');
INSERT INTO City VALUES(4261, 'Dalmose');
INSERT INTO City VALUES(4262, 'Sandved');
INSERT INTO City VALUES(4270, 'H?ng');
INSERT INTO City VALUES(4281, 'G?rlev');
INSERT INTO City VALUES(4291, 'Ruds Vedby');
INSERT INTO City VALUES(4293, 'Dianalund');
INSERT INTO City VALUES(4295, 'Stenlille');
INSERT INTO City VALUES(4296, 'Nyrup');
INSERT INTO City VALUES(4300, 'Holb');
INSERT INTO City VALUES(4320, 'Lejre');
INSERT INTO City VALUES(4330, 'Hvals?');
INSERT INTO City VALUES(4340, 'T?ll?se');
INSERT INTO City VALUES(4350, 'Ugerl?se');
INSERT INTO City VALUES(4360, 'Kirke Eskilstrup');
INSERT INTO City VALUES(4370, 'Store Merl?se');
INSERT INTO City VALUES(4390, 'Vipper?d');
INSERT INTO City VALUES(4400, 'Kalundborg');
INSERT INTO City VALUES(4420, 'Regstrup');
INSERT INTO City VALUES(4440, 'M?rk?v');
INSERT INTO City VALUES(4450, 'Jyderup');
INSERT INTO City VALUES(4460, 'Snertinge');
INSERT INTO City VALUES(4470, 'Sveb?lle');
INSERT INTO City VALUES(4480, 'Store Fuglede');
INSERT INTO City VALUES(4490, 'Jerslev Sj?lland');
INSERT INTO City VALUES(4500, 'Nyk?bing Sj');
INSERT INTO City VALUES(4520, 'Svinninge');
INSERT INTO City VALUES(4532, 'Gislinge');
INSERT INTO City VALUES(4534, 'H?rve');
INSERT INTO City VALUES(4540, 'F?revejle');
INSERT INTO City VALUES(4550, 'Asn');
INSERT INTO City VALUES(4560, 'Vig');
INSERT INTO City VALUES(4571, 'Grevinge');
INSERT INTO City VALUES(9600, 'Aars');
INSERT INTO City VALUES(9610, 'N?rager');
INSERT INTO City VALUES(9620, 'Aalestrup');
INSERT INTO City VALUES(9631, 'Gedsted');
INSERT INTO City VALUES(9632, 'M?ldrup');
INSERT INTO City VALUES(9640, 'Fars?');
INSERT INTO City VALUES(9670, 'L?gst?r');
INSERT INTO City VALUES(9681, 'Ranum');
INSERT INTO City VALUES(9690, 'Fjerritslev');
INSERT INTO City VALUES(9700, 'Br?nderslev');
INSERT INTO City VALUES(9740, 'Jerslev J');
INSERT INTO City VALUES(9750, '?ster Vr');
INSERT INTO City VALUES(9760, 'Vr');
INSERT INTO City VALUES(9800, 'Hj?rring');
INSERT INTO City VALUES(9830, 'T?rs');
INSERT INTO City VALUES(9850, 'Hirtshals');
INSERT INTO City VALUES(9870, 'Sindal');
INSERT INTO City VALUES(9881, 'Bindslev');
INSERT INTO City VALUES(9900, 'Frederikshavn');
INSERT INTO City VALUES(9940, 'L?s?');
INSERT INTO City VALUES(9970, 'Strandby');
INSERT INTO City VALUES(9981, 'Jerup');
INSERT INTO City VALUES(9982, '?lb');
INSERT INTO City VALUES(9990, 'Skagen');

INSERT INTO Breeder VALUES('Karsten', 'IkkeAdmin', 'Sofiendalsvej 60', '2500', '12345678', 'ikke@admin.dk', 'test123', 'false');
INSERT INTO Breeder VALUES('Jesper', 'ErAdmin', 'Sofiendalsvej 60', '2500', '12345678', 'er@admin.dk', 'test123', 'true');
INSERT INTO Compendium VALUES('TEST', 'TEST');
INSERT INTO Chart VALUES(1, 1999, 'pedigree', 'true', 1);
INSERT INTO Queen VALUES(2012, 'KS023', 4, 4, 4, 5, 5, 3, 'true', NULL, NULL, 1);
INSERT INTO Queen VALUES(2012, 'KS024', 4, 4, 4, 5, 5, 3, 'true', NULL, NULL, 1);
INSERT INTO Queen VALUES(2012, 'KS025', 4, 4, 4, 5, 5, 3, 'true', NULL, NULL, 1);