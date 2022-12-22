CREATE TABLE MarsUsers(
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

CREATE TABLE Notifications(
                              type varchar(256) not null ,
                              title varchar(256) not null ,
                              expireTime datetime not null ,
                              message varchar(256) not null ,
                              createDate datetime not null ,
                              receiver varchar(256),
                              FOREIGN KEY (receiver) REFERENCES MarsUsers(uid)
);

CREATE TABLE Items(
                      uid varchar(256) primary key not null ,
                      name varchar(256) not null ,
                      mapSummary varchar(256) not null ,
                      molecules varchar(256) not null ,
                      height double not null ,
                      length double not null ,
                      width double not null ,
                      sendTime timestamp not null
);

CREATE TABLE Blacklist(
                          itemID varchar(256) not null ,
                          userID varchar(256) not null ,
                          FOREIGN KEY (itemID) REFERENCES Items(uid),
                          FOREIGN KEY (userID) REFERENCES MarsUsers(uid)
);


CREATE TABLE Links(
                      uid varchar(256) primary key not null ,
                      senderTransporter varchar(256) not null ,
                      receiverTransporter varchar(256) not null ,
                      senderUser varchar(256) not null ,
                      receiverUser varchar(256) not null ,
                      linkStatus varchar(256) not null,
                      item varchar(256) not null,
                      FOREIGN KEY (senderTransporter) REFERENCES Transporters(uid),
                      FOREIGN KEY (receiverTransporter) REFERENCES Transporters(uid),
                      FOREIGN KEY (senderUser) REFERENCES MarsUsers(uid),
                      FOREIGN KEY (receiverUser) REFERENCES MarsUsers(uid),
                      FOREIGN KEY (item) REFERENCES Items(uid)
);