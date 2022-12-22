INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('25b37fdf-7aec-4b90-bc55-009a53fec577', 'Malakai', 'Hart', 'FREE');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('0e0377a9-faac-4f88-9942-5811285cae94', 'Calum', 'Lynn', 'FREE');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('b3301e46-8ae3-45f5-afe3-a21abe17d651', 'Marwa', 'Little', 'FREE');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('e1005282-0895-4d47-82e2-e395177504e0', 'Lyla', 'Bauer', 'FREE');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('702a34f8-26cd-4619-a7d9-7f7d1687d248', 'Regan', 'Obrien', 'FREE');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('c1cde060-b836-4796-95b3-55b7f90665d0', 'Leonard', 'Melendez', 'FREE');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'Thibo', 'Verbeerst', 'PREMIUM');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('a146d991-ae9d-42ce-8be8-c87de2018a61', 'Glenn', 'Callens', 'PREMIUM');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('542dcc1b-212b-4b9d-a17a-7d33953f4761', 'Pieter', 'Verheyen', 'PREMIUM');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('21c9ad88-0948-4b4f-a614-3e755dbb25d2', 'Delia', 'Vervaeke', 'PREMIUM');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('e29cce0b-5ffd-4db5-8a40-dd930e767fcb', 'Wiebe', 'Desmadryl', 'PREMIUM');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('bdc3eb92-96ce-4522-b1b7-3c3c2cf4d97c', 'Seyfaa', 'INC.', 'BUSINESS');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('9dc5d03e-622c-45fb-8e3e-2a3be05ea1f9', 'MaraSutra', 'BVBA', 'BUSINESS');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('b2bd5851-b29e-40f9-9d53-31eca7211cca', 'LunaExel', 'NV', 'BUSINESS');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('eed9c42b-7b2e-4195-b5da-719e1fba5e2d', 'Zenni', 'BV', 'BUSINESS');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('9fa53a93-61fb-4bbc-923d-fe9c5858c56c', 'GIAA', 'INC', 'BUSINESS');
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('a194216d-9b76-4442-a3b9-413b02a883e0', 'PICKUP', 3.165367, 51.295283);
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('e0a90ef7-c481-4e15-9b61-a51fbddb07f1', 'RESIDENCE', 4.113754, 50.938392);
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('f3e4fc8b-5230-47b7-8f68-907826817d6e', 'RESIDENCE', 3.772950, 50.624066);
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('a7ac16dc-92f5-4754-957b-cbe73942f635', 'PICKUP', 4.366608, 50.848305);
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('8b2086f7-7cef-4533-a4e1-61e121b9e4fb', 'PICKUP', 3.717947, 51.047303);
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('40b247a1-5552-43d6-9301-6da8320dfc40', 'RESIDENCE', 3.168264, 50.953966);
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('45fdc53b-8121-4e2a-a693-4690b41847d7', 'RESIDENCE', 4.344415, 51.095626);
INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES ('1631ba92-3136-4d41-b786-1ef6461c1b33', 'RESIDENCE', 5.603190, 51.030032);
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('e4c3da8f-ce97-4ae9-9b44-609309ccfdd3', 'Train station Pickup', 100, 100, 100, 'a194216d-9b76-4442-a3b9-413b02a883e0', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('fe2ee86e-83bd-451d-b63e-c96d5b4c47fe', 'Mining Colony Pickup', 100, 100, 100, 'a7ac16dc-92f5-4754-957b-cbe73942f635', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('551d17ea-4cb3-4dd6-a55a-2794d7641af8', 'Mars Arrival Pickup', 100, 100, 100, '8b2086f7-7cef-4533-a4e1-61e121b9e4fb', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('eb2f8330-864f-40ab-bb1b-d3a9aeef4840', 'Delias Home Transporter', 50, 50, 50, 'e0a90ef7-c481-4e15-9b61-a51fbddb07f1', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('593ce2a1-00fe-4a13-997e-dea277790aff', 'Thibo s Home Transporter', 50, 50, 50, 'f3e4fc8b-5230-47b7-8f68-907826817d6e', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('8fb972bb-9279-44a2-9c3b-a567e039cba3', 'Glenns Home Transporter', 50, 50, 50, '40b247a1-5552-43d6-9301-6da8320dfc40', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('77108c5b-b4ea-46f9-8830-73055de598e4', 'Pieters Home Transporter', 50, 50, 50, '45fdc53b-8121-4e2a-a693-4690b41847d7', 'https://transporter1.thibo.cloud/');
INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES ('8ffd4794-18f8-4aa6-a9ec-3c831f452689', 'Wiebes Home Transporter', 50, 50, 50, '1631ba92-3136-4d41-b786-1ef6461c1b33', 'https://transporter1.thibo.cloud/');