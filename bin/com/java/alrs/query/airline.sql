CREATE TABLE `flight` (
  `flightId` int(10) NOT NULL AUTO_INCREMENT,
  `flightName` varchar(50) NOT NULL,
  `date` varchar(20) DEFAULT NULL,
  `source` varchar(30) NOT NULL,
  `destination` varchar(30) NOT NULL,
`departureTime` varchar(30) NOT NULL,
  `arrivalTime` varchar(30) NOT NULL,
      `prise` varchar(30) NOT NULL,
    `isActive` bit(1) DEFAULT 1,
   PRIMARY KEY (`flightId`)
);

insert into flight set airlineId=?, flightName=?, date=?, source=?,
 destination=?, classType=?, departureTime=?, arrivalTime=?,availableSet=?, prise=?;

insert into flight set  flightName="indiGo" ,date="13/02/2111", source="jaipur",
  destination="delhi" , departureTime="10:11", arrivalTime="10:21",  prise="5000";


CREATE TABLE `customer` (
`Id` int(10) NOT NULL AUTO_INCREMENT,
`customerName` varchar(300) DEFAULT NULL,
`mobile` varchar(300) DEFAULT NULL,
`email` varchar(300) DEFAULT NULL,
`pnrNo` int(250) DEFAULT NULL,
`flightId` varchar(20) NOT NULL,
`isActive` bit(1) DEFAULT 1,
PRIMARY KEY (`Id`),
KEY `flightId` (`flightId`),
  CONSTRAINT `source_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`flightId`)
);

insert into customer set  customerName="renu", mobile="90034535533" , email="renu@gmail.com", flighId=1;


 insert into flight set airlineId=?, airlineName=? , fromLoc=?, toLoc=? ,fclass=?, departureTime=?, arrivalTime=?,prise=?, availableSet=?

  CREATE TABLE Orders (
      OrderID int NOT NULL,
      OrderNumber int NOT NULL,
      PersonID int,
      PRIMARY KEY (OrderID),
      FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
  );

create TABLE `customer` (
    `Id` int(10) NOT NULL AUTO_INCREMENT,
     `customerName` varchar(300) DEFAULT NULL,
    `mobile` varchar(300) DEFAULT NULL,
 `email` varchar(300) DEFAULT NULL,
 `pnrNo` varchar(255) DEFAULT NULL,
    `flightId` int(10) DEFAULT NULL,
 `isActive` bit(1) DEFAULT 1,
  `isCancel` bit(1) DEFAULT 1,
 PRIMARY KEY (`Id`),
 KEY `flightId` (`flightId`),
  CONSTRAINT `source_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`flightId`)
 );
"select c.*, f.prise from customer c left join flight f on c.flightId = f.flightId where pnrNo=-477753222";
 "select c.*, f.prise,f.source, f.destination from customer c left join flight f on c.flightId = f.flightId where pnrNo=-477753222";


ALTER TABLE customer
ADD  `isTcancel` bit(1) DEFAULT 1 ;

ALTER TABLE customer
DROP COLUMN date;

select c.*, f.flightName, f.date, f.classType, f.source, f.destination, f.departureTime, f.arrivalTime, f.prise from customer c left join flight f on c.flightId = f.flightId where pnrNo=-477753222;

int pnrNo = ThreadLocalRandom.current().nextInt();  
      customerDTO.setPnrNo(pnrNo);



      update flight set  flightName="indiGo" ,date="13/02/2111", source="jaipur",
  destination="delhi" , departureTime="10:11", arrivalTime="10:21",  prise="5000" where flighId=1; 

  
  insert into customer set customerName="renu",mobile="3211212212", email="renu@g.com",pnrNo="212123alr", flightId=1;

update customer set isActive=1, isCancel=1, isSchedule=1;

CREATE TABLE `payment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `payment` varchar(30) NOT NULL,
  `pnrNo` varchar(30) DEFAULT NULL,
 `isActive` bit(1) DEFAULT 1,
  PRIMARY KEY (`id`)
  );