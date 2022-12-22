INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('25b37fdf-7aec-4b90-bc55-009a53fec577', 'Malakai', 'Hart', 'STANDARD');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('0e0377a9-faac-4f88-9942-5811285cae94', 'Calum', 'Lynn', 'STANDARD');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('b3301e46-8ae3-45f5-afe3-a21abe17d651', 'Marwa', 'Little', 'STANDARD');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('e1005282-0895-4d47-82e2-e395177504e0', 'Lyla', 'Bauer', 'STANDARD');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('702a34f8-26cd-4619-a7d9-7f7d1687d248', 'Regan', 'Obrien', 'STANDARD');
INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES ('c1cde060-b836-4796-95b3-55b7f90665d0', 'Leonard', 'Melendez', 'STANDARD');
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
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f08', 'Plant', '{"summary":{"O2":1000,"H20":5000},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":1.0,"length":1.0,"width":1.0},"cost":6.16940088214996E-5}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}]', 30, 20, 25, '2022-12-22 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('c91bc67d-fa6d-442e-acb4-566e8b493f08', 'Pen', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-22 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('b81bc67d-fa6d-442e-acb4-566e8b493f08', 'Pencil', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-21 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('a81bc67d-fa6d-442e-acb4-566e8b493f08', 'Notebook', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-20 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f07', 'Desk', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-19 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f06', 'Computer', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-18 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f05', 'Phone', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-17 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f04', 'Tablet', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-16 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f03', 'Printer', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-16 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f02', 'Monitor', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-15 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f01', 'Television', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-15 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('c91bc67d-fa6d-442e-acb4-566e8b493f01', 'Sofa', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-14 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('b81bc67d-fa6d-442e-acb4-566e8b493f01', 'Coffee table', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-14 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('a81bc67d-fa6d-442e-acb4-566e8b493f01', 'Lamp', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-13 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493f00', 'Rug', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-13 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493eff', 'Painting', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-12 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493efe', 'Gun', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-12 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493efd', 'Coke', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-12 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493efc', 'Cow', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-11 22:03:19.125919');
INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493efb', 'Salt', '{"summary":{"O2":1500,"H20":7500},"molecules":[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11}],"size":{"height":2.0,"length":2.0,"width":2.0},"cost":1.5898532143437499E-4}', '[{"id":1,"type":"H20","xPosition":118,"yPosition":119},{"id":2,"type":"O2","xPosition":18,"yPosition":11},{"id":3,"type":"H20","xPosition":100,"yPosition":100},{"id":4,"type":"O2","xPosition":50,"yPosition":50}]', 60, 40, 50, '2022-12-11 22:03:19.125919');
INSERT INTO BlackList (itemID, userID) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493efc', '7fbd8cf3-488a-49b4-a746-de7e92bc876d');
INSERT INTO BlackList (itemID, userID) VALUES ('d81bc67d-fa6d-442e-acb4-566e8b493efb', 'a146d991-ae9d-42ce-8be8-c87de2018a61');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('ccb930c8-3940-48e6-ae73-2631eacbcb20', '8fb972bb-9279-44a2-9c3b-a567e039cba3', '593ce2a1-00fe-4a13-997e-dea277790aff', 'a146d991-ae9d-42ce-8be8-c87de2018a61', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f08');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('f55c82b6-0bd4-4c16-a48f-165e4f2d13ec', '8fb972bb-9279-44a2-9c3b-a567e039cba3', '593ce2a1-00fe-4a13-997e-dea277790aff', 'a146d991-ae9d-42ce-8be8-c87de2018a61', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'SENT', 'c91bc67d-fa6d-442e-acb4-566e8b493f08');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('d0d272e7-2483-44da-9b6c-b903728402ed', '8fb972bb-9279-44a2-9c3b-a567e039cba3', '593ce2a1-00fe-4a13-997e-dea277790aff', 'a146d991-ae9d-42ce-8be8-c87de2018a61', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'SENT', 'b81bc67d-fa6d-442e-acb4-566e8b493f08');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('81d44024-72b9-4fa7-a338-5bfaa48e132f', '8fb972bb-9279-44a2-9c3b-a567e039cba3', '593ce2a1-00fe-4a13-997e-dea277790aff', 'a146d991-ae9d-42ce-8be8-c87de2018a61', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'SENT', 'a81bc67d-fa6d-442e-acb4-566e8b493f08');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('014198ed-02c3-4e9d-81ee-74b9fc534cd3', '8fb972bb-9279-44a2-9c3b-a567e039cba3', '593ce2a1-00fe-4a13-997e-dea277790aff', 'a146d991-ae9d-42ce-8be8-c87de2018a61', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f07');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('00eb0403-77c3-4712-9b20-52342e1a7829', '8fb972bb-9279-44a2-9c3b-a567e039cba3', '593ce2a1-00fe-4a13-997e-dea277790aff', 'a146d991-ae9d-42ce-8be8-c87de2018a61', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f06');

INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('04bf38ca-6684-4259-94c1-843381613823', '593ce2a1-00fe-4a13-997e-dea277790aff', 'e4c3da8f-ce97-4ae9-9b44-609309ccfdd3', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'eed9c42b-7b2e-4195-b5da-719e1fba5e2d', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f05');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('7fbd83a2-96e3-47cf-8be4-b3469c17e60a', '593ce2a1-00fe-4a13-997e-dea277790aff', 'fe2ee86e-83bd-451d-b63e-c96d5b4c47fe', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'b2bd5851-b29e-40f9-9d53-31eca7211cca', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f04');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('0ac7efbf-7cc5-4c09-b46e-2c5eac1a1b03', '593ce2a1-00fe-4a13-997e-dea277790aff', '551d17ea-4cb3-4dd6-a55a-2794d7641af8', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', '9dc5d03e-622c-45fb-8e3e-2a3be05ea1f9', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f03');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('09719de4-efbd-4d12-bdca-afb0f9bb5bd7', '593ce2a1-00fe-4a13-997e-dea277790aff', 'eb2f8330-864f-40ab-bb1b-d3a9aeef4840', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', '21c9ad88-0948-4b4f-a614-3e755dbb25d2', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f02');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('cf3187de-06c4-497c-9849-e604ef8ed3ad', '593ce2a1-00fe-4a13-997e-dea277790aff', '8fb972bb-9279-44a2-9c3b-a567e039cba3', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'a146d991-ae9d-42ce-8be8-c87de2018a61', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f01');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('7ad4fcb0-af9b-4de5-be87-1dcaa2c4ba60', '593ce2a1-00fe-4a13-997e-dea277790aff', '77108c5b-b4ea-46f9-8830-73055de598e4', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', '542dcc1b-212b-4b9d-a17a-7d33953f4761', 'SENT', 'c91bc67d-fa6d-442e-acb4-566e8b493f01');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('4c90450e-2dcd-4b45-8e1a-f9e20562e6c1', '593ce2a1-00fe-4a13-997e-dea277790aff', '8ffd4794-18f8-4aa6-a9ec-3c831f452689', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'e29cce0b-5ffd-4db5-8a40-dd930e767fcb', 'SENT', 'b81bc67d-fa6d-442e-acb4-566e8b493f01');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('7aa33f88-18f9-452d-94d3-c787c6b25a93', '593ce2a1-00fe-4a13-997e-dea277790aff', 'e4c3da8f-ce97-4ae9-9b44-609309ccfdd3', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', '9fa53a93-61fb-4bbc-923d-fe9c5858c56c', 'SENT', 'a81bc67d-fa6d-442e-acb4-566e8b493f01');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('398ffb35-3dbd-476b-92dd-43206cbd8aba', '593ce2a1-00fe-4a13-997e-dea277790aff', 'fe2ee86e-83bd-451d-b63e-c96d5b4c47fe', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', '0e0377a9-faac-4f88-9942-5811285cae94', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493f00');
INSERT INTO Links(uid, senderTransporter, receiverTransporter, senderUser, receiverUser, linkStatus, item) VALUES ('83783f29-5f23-4577-b320-f69c2617ac31', '593ce2a1-00fe-4a13-997e-dea277790aff', 'fe2ee86e-83bd-451d-b63e-c96d5b4c47fe', '7fbd8cf3-488a-49b4-a746-de7e92bc876d', 'b3301e46-8ae3-45f5-afe3-a21abe17d651', 'SENT', 'd81bc67d-fa6d-442e-acb4-566e8b493eff');
