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