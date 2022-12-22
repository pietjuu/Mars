CREATE TABLE Users(
                      uid varchar(256) primary key not null,
                      firstname varchar(256) not null ,
                      lastname varchar(256) not null ,
                      pricePlan varchar(256) not null
);

CREATE TABLE Buildings(
                          uid varchar(256) primary key not null,
                          typeOfLocation varchar(256) not null ,
                          longitude float not null ,
                          latitude float not null
);

CREATE TABLE Transporters(
                             uid varchar(256) primary key not null ,
                             name varchar(256) not null ,
                             height double not null ,
                             length double not null ,
                             width double not null ,
                             buildingID varchar(256) not null ,
                             ip varchar(256) not null ,
                             FOREIGN KEY (buildingID) REFERENCES Buildings(uid)
);