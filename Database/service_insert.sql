SELECT * FROM FIT5042.SERVICE;
--- INSERT SERVICE
INSERT INTO FIT5042.Service (service_no, "NAME", "TYPE","THUMBNAIL","DESCRIPTION")
	VALUES (1, 'ASK A LIBRARIAN', 'Education','thumbnail','Not sure where to start your research? Stuck somewhere along the way? Ask a Librarian.
Our librarians provide research tips and strategies to point you in the right direction.');
INSERT INTO FIT5042.Service (service_no, "NAME", "TYPE","THUMBNAIL","DESCRIPTION")
	VALUES (2, 'Home and Contents Insurance', 'Insurance','thumbnail','Protect your most valuable assets with quality cover from a name you can trust. Our home and contents insurance offers peace of mind at an affordable price.');
INSERT INTO FIT5042.Service (service_no, "NAME", "TYPE","THUMBNAIL","DESCRIPTION")
	VALUES (3, 'Australian Police Child ID', 'Child care','thumbnail','Helps parents and guardians easily collect and send important information about their child/children to police in an event of a disappearance or abduction. No data from this app is collected or stored by the police unless it is sent to them.');
INSERT INTO FIT5042.Service (service_no, "NAME", "TYPE","THUMBNAIL","DESCRIPTION")
	VALUES (4, 'Find a child care service', 'Child care','thumbnail','Search by type of care (long, occasional, after school etc), location (town, postcode etc) or name of centre to find approved child care services in your area.');
INSERT INTO FIT5042.Service (service_no, "NAME", "TYPE","THUMBNAIL","DESCRIPTION")
	VALUES (5, 'Citizenship Wizard', 'Citizenship','thumbnail','The Citizenship Wizard will give you information about what to do and how to apply for Australian citizenship.');


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