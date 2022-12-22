INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('T-2', 'Thibo', 'Verbeerst', 'PREMIUM');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('T-1', 'Glenn', 'Callens', 'STANDARD');
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('B-1', 'RESIDENCE', -21.33977, 124.86297);
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('TT-1', 'Mining Colony 5', 100, 100, 100, 'B-1', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('TT-2', 'Mining Colony 201', 100, 100, 100, 'B-1', 'https://transporter1.thibo.cloud/');
INSERT INTO Notifications (type, title, expireTime, message, createDate, receiver) VALUES ('SystemNotification', 'Maintenance', '2050-12-22 01:46:56', 'Our services will be down between 2PM and 3PM for maintenance. Were apologize for the inconvenience.', '2022-12-22 01:47:49', null);
INSERT INTO Notifications (type, title, expireTime, message, createDate, receiver) VALUES ('ShipNotification', 'aa', '2023-12-22 01:48:10', 'dfsd', '2022-12-22 01:48:15', 'T-2');