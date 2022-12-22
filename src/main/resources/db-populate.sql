INSERT INTO Users (uid, firstname, lastname, pricePlan) VALUES ('T-2', 'Thibo', 'Verbeerst', 'PREMIUM');
INSERT INTO Users (uid, firstname, lastname, pricePlan) VALUES ('T-1', 'Glenn', 'Callens', 'STANDARD');
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('B-1', 'RESIDENCE', -21.33977, 124.86297);
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('TT-1', 'Mining Colony 5', 100, 100, 100, 'B-1', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('TT-2', 'Mining Colony 201', 100, 100, 100, 'B-1', 'https://transporter1.thibo.cloud/');