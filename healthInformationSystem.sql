#Project 2 CS 174A
#James Thompson
#Heneli Kailahi 

DROP DATABASE health_information_system;

CREATE DATABASE health_information_system;
USE health_information_system;

CREATE TABLE Patient(
	patientid VARCHAR(100),
	providerid VARCHAR(100) NOT NULL,
	patientrole VARCHAR(100) NOT NULL,
	givenname VARCHAR(100)  NOT NULL,
	familyname VARCHAR(100),
	suffix VARCHAR(100),
	gender VARCHAR(100) NOT NULL,
# 	birthtime VARCHAR(100) NOT NULL,
# 	xmlhealthcreation VARCHAR(100)TIME NOT NULL,
  birthtime VARCHAR(100),
	xmlhealthcreation VARCHAR(100),
	PRIMARY KEY(patientid)
#	FOREIGN KEY (patientrole) REFERENCES Guardians (guardianno)
);

CREATE TABLE Guardians(
	guardianno VARCHAR(100),
    patientid VARCHAR(100),
	givenname VARCHAR(100),
	familyname VARCHAR(100),
	phone VARCHAR(100) NOT NULL,
	address VARCHAR(100),
	city VARCHAR(100),
	state VARCHAR(100),
	zip VARCHAR(100),
	PRIMARY KEY(guardianno, patientid),
  FOREIGN KEY (patientid) REFERENCES Patient (patientid) 
    ON DELETE CASCADE
);

CREATE TABLE Author(
	authorid VARCHAR(100),
	authortitle VARCHAR(100),
	authorfirstname VARCHAR(100) NOT NULL,
	authorlastname VARCHAR(100),
	PRIMARY KEY(authorid)
);


CREATE TABLE Insurance_Company(
	payerid VARCHAR(100),
	name VARCHAR(100) NOT NULL,
	policytype VARCHAR(100) NOT NULL,
	PRIMARY KEY(payerid)
);

CREATE TABLE Family_History(
	relativeid VARCHAR(100),
    patientid VARCHAR(100),
	patientrelation VARCHAR(100) NOT NULL,
	age VARCHAR(100),
	diagnosis VARCHAR(100) NOT NULL,
	PRIMARY KEY(relativeid, patientid),
    FOREIGN KEY (patientid) REFERENCES Patient (patientid)
      ON DELETE CASCADE
);

CREATE TABLE Allergies(
	substance VARCHAR(100),
    patientid VARCHAR(100),
	reaction VARCHAR(100) NOT NULL,
	status VARCHAR(100) NOT NULL,
	PRIMARY KEY(substance, patientid),
  FOREIGN KEY (patientid) REFERENCES Patient (patientid)
    ON DELETE CASCADE
);

CREATE TABLE Lab_Test_Reports(
	labtestresultid VARCHAR(100),
	patientvisitid VARCHAR(100),
	labtestperformeddate VARCHAR(100) NOT NULL,
	labtesttype VARCHAR(100) NOT NULL,
	testresultvalue VARCHAR(100) NOT NULL,
	referencerangehigh VARCHAR(100),
	referencerangelow VARCHAR(100),
	PRIMARY KEY(labtestresultid, patientvisitid)
);

CREATE TABLE Plan(
	planid VARCHAR(100),
	planpatientid VARCHAR(100) NOT NULL,
	activity VARCHAR(100) NOT NULL,
	PRIMARY KEY(planid)
#	FOREIGN KEY (planpatientid) REFERENCES Patient (patientid)
);

# ALTER TABLE Patient
# ADD FOREIGN KEY (patientrole) REFERENCES Guardians (guardianno);

ALTER TABLE Plan
ADD FOREIGN KEY (planpatientid) REFERENCES Patient (patientid);

#Relations
CREATE TABLE has_Insurance_Company(
	patientid VARCHAR(100),
  payerid VARCHAR(100),
	purpose VARCHAR(100),
  PRIMARY KEY (patientid, payerid),
  FOREIGN KEY (patientid) REFERENCES Patient (patientid),
  FOREIGN KEY (payerid) REFERENCES Insurance_Company (payerid)
);

CREATE TABLE visits_Lab(
	patientid VARCHAR(100),
  labtestresultid VARCHAR(100),
  PRIMARY KEY (patientid, labtestresultid),
  FOREIGN KEY (patientid) REFERENCES Patient (patientid),
  FOREIGN KEY (labtestresultid) REFERENCES  Lab_Test_Reports (labtestresultid)
);

CREATE TABLE has_Author(
  patientid VARCHAR(100),
  authorid VARCHAR(100),
  participatingrole VARCHAR(100) NOT NULL,
	PRIMARY KEY (patientid, authorid),
  FOREIGN KEY (patientid) REFERENCES Patient (patientid),
  FOREIGN KEY (authorid) REFERENCES Author (authorid)
);

CREATE TABLE has_Plan(
	patientid VARCHAR(100),
  planid VARCHAR(100),
  plandate VARCHAR(100) NOT NULL,
	PRIMARY KEY (patientid, planid),
	FOREIGN KEY (patientid) REFERENCES Patient (patientid),
  FOREIGN KEY (planid) REFERENCES Plan (planid)
);
