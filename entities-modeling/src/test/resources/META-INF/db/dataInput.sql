INSERT INTO author (id,createddate,lastmodifieddate,username,userpassword) VALUES (1,TIMESTAMP '2014-10-19 13:38:35.900',TIMESTAMP '2014-10-19 13:38:35.900','testUser','password');
INSERT INTO tag (id,createddate,lastmodifieddate,name) VALUES (3,TIMESTAMP '2014-10-19 13:38:35.216',TIMESTAMP '2014-10-19 13:38:35.216','Test');
INSERT INTO post (id,body,createddate,lastmodifieddate,subject,author_id) VALUES (2,'This is a body of this blog post',TIMESTAMP '2014-10-19 13:38:35.188',TIMESTAMP '2014-10-19 13:38:35.188','subject',1);
INSERT INTO post_comment (id,content,createddate,lastmodifieddate,author_id,post_id) VALUES (4,'This is a post',TIMESTAMP '2014-10-19 13:38:35.222',TIMESTAMP '2014-10-19 13:38:35.222',1,2);
INSERT INTO post_tag (post_id,tags_id) VALUES (2,3);
INSERT INTO post_images (filename,title,sizex,sizey,post_id) VALUES ('illustration1.jpg','image title1',200,300,2);
INSERT INTO post_images (filename,title,sizex,sizey,post_id) VALUES ('illustration2.jpg','image title2',100,80,2);
INSERT INTO post_images (filename,title,sizex,sizey,post_id) VALUES ('illustration3.jpg','image title3',1,1,2);

