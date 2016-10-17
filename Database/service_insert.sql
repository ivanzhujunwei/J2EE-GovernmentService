SELECT * FROM FIT5042.SERVICE;
--- INSERT SERVICE
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (1, 'Not sure where to start your research? Stuck somewhere along the way? Ask a Librarian.
Our librarians provide research tips and strategies to point you in the right direction.', 'ASK A LIBRARIAN', '1.jpeg', 'Education', true);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (2, 'Protect your most valuable assets with quality cover from a name you can trust. Our home and contents insurance offers peace of mind at an affordable price.', 'Home and Contents Insurance', '2.jpeg', 'Insurance', true);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (3, 'Helps parents and guardians easily collect and send important information about their child/children to police in an event of a disappearance or abduction. No data from this app is collected or stored by the police unless it is sent to them.', 'Australian Police Child ID', '3.jpeg', 'Child care', true);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (4, 'Search by type of care (long, occasional, after school etc), location (town, postcode etc) or name of centre to find approved child care services in your area.', 'Find a child care service', '4.jpeg', 'Child care', true);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (5, 'The Citizenship Wizard will give you information about what to do and how to apply for Australian citizenship.', 'Citizenship Wizard', '5.jpeg', 'Citizenship', true);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (110309, 'test', 'test', 'e057f898-1258-4a29-9a79-cc57c937a32a.jpg', 'tes', true);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (153109, 'sdafasdfsdafasdfsdafasdfsdafasdfsdafasdfsdafasdfsdafasdfsdafasdf', '403', '1916c8e3-3833-4ebc-8cbf-5b37c1ab5898.jpg', '403', true);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (248509, ' Not sure where to start your research? Stuck somewhere along the way? Ask a Librarian. Our librarians provide research tips and strategies to point you in the right direction. ', 'sss', '88b5bba7-316f-4a7c-b23c-0ef0e44bed66.jpg', 'Insurance', true);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (112309, 'aassasd', 'qqqdddddssssaaa', 'a426d051-d8fc-4c09-9cc2-2aa463de3dea.jpg', 'asdfadsf', false);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (149109, 'w3rw', '12121212', 'efba5996-62a5-4ba8-a511-45efbc645980.jpg', '12121212', false);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (149309, 'ggg', 'ggg', 'df630d45-fa0a-437d-9793-238f6c9ac498.jpg', 'gggg', false);
INSERT INTO FIT5042.SERVICE (SERVICE_NO, DESCRIPTION, "NAME", THUMBNAIL, "TYPE", ISACTIVE) 
	VALUES (249609, 'serviceManagedBean.service.type', 'newservier', '4f9a203d-b8ec-4a85-bee2-7959dde48369.jpg', 'Child care', false);

-------



--- INSERT WORKER
INSERT INTO FIT5042.Worker (user_id, "LASTNAME", "FIRSTNAME","EMAIL","PASSWORD","USER_TYPE","ADDRESS",PHONE)
	VALUES (5, 'Ivan', 'Zhu','ivanzhujunwei@gmail.com','123','worker','East caulfield',426731819);

INSERT INTO FIT5042.Worker (user_id, "LASTNAME", "FIRSTNAME","EMAIL","PASSWORD","USER_TYPE","ADDRESS",PHONE)
	VALUES (1, 'Troy', 'Liu','zhiliu@gmail.com','123','worker','East caulfield',422731899);

INSERT INTO FIT5042.Worker (user_id, "LASTNAME", "FIRSTNAME","EMAIL","PASSWORD","USER_TYPE","ADDRESS",PHONE)
	VALUES (2, 'Roy', 'Liu','rli230@gmail.com','123','worker','East caulfield',426731519);

INSERT INTO FIT5042.Worker (user_id, "LASTNAME", "FIRSTNAME","EMAIL","PASSWORD","USER_TYPE","ADDRESS",PHONE)
	VALUES (2, 'Richard', 'Guo','rguo198@gmail.com','123','worker','East caulfield',416730819);

--- INSERT PUBLICUSER
INSERT INTO FIT5042.publicuser (user_id, "LASTNAME", "FIRSTNAME","EMAIL","PASSWORD","USER_TYPE","ADDRESS",PHONE)
	VALUES (1, 'Peter', 'K','sed@gmail.com','000','public','Clayton',426731819);
INSERT INTO FIT5042.publicuser (user_id, "LASTNAME", "FIRSTNAME","EMAIL","PASSWORD","USER_TYPE","ADDRESS",PHONE)
	VALUES (2, 'Andy', 'J','andyJd@163.com','000','public','Clayton',426745819);
INSERT INTO FIT5042.publicuser (user_id, "LASTNAME", "FIRSTNAME","EMAIL","PASSWORD","USER_TYPE","ADDRESS",PHONE)
	VALUES (3, 'Kevin', 'Pl','sed@gmail.com','000','public','Clayton',457783299);

---- Get assigned worker
SELECT COUNT(wo.USER_ID),wo.USER_ID,wo.LASTNAME from worker wo
left join ServiceUse w   on wo.USER_ID = w.MANAGED_BY 
GROUP BY wo.USER_ID,wo.LASTNAME ORDER BY COUNT(wo.USER_ID) asc;