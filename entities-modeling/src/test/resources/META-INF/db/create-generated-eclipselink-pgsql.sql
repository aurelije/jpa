CREATE TABLE POST_COMMENT (ID BIGINT NOT NULL, CONTENT VARCHAR(2000) NOT NULL, CREATEDDATE TIMESTAMP, LASTMODIFIEDDATE TIMESTAMP, AUTHOR_ID BIGINT NOT NULL, POST_ID BIGINT NOT NULL, PRIMARY KEY (ID))
CREATE TABLE POST (ID BIGINT NOT NULL, BODY VARCHAR(20000) NOT NULL, CREATEDDATE TIMESTAMP, LASTMODIFIEDDATE TIMESTAMP, SUBJECT VARCHAR(30) NOT NULL, AUTHOR_ID BIGINT NOT NULL, PRIMARY KEY (ID))
CREATE TABLE TAG (ID BIGINT NOT NULL, CREATEDDATE TIMESTAMP, LASTMODIFIEDDATE TIMESTAMP, NAME VARCHAR(20) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE AUTHOR (ID BIGINT NOT NULL, CREATEDDATE TIMESTAMP, LASTMODIFIEDDATE TIMESTAMP, USERNAME VARCHAR(20) NOT NULL, USERPASSWORD VARCHAR(40) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE Post_IMAGES (FILENAME VARCHAR(255) NOT NULL, SIZEX INTEGER, SIZEY INTEGER, TITLE VARCHAR(30) NOT NULL, Post_ID BIGINT)
CREATE TABLE POST_TAG (Post_ID BIGINT NOT NULL, tags_ID BIGINT NOT NULL, PRIMARY KEY (Post_ID, tags_ID))
ALTER TABLE POST_COMMENT ADD CONSTRAINT FK_POST_COMMENT_AUTHOR_ID FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR (ID)
ALTER TABLE POST_COMMENT ADD CONSTRAINT FK_POST_COMMENT_POST_ID FOREIGN KEY (POST_ID) REFERENCES POST (ID)
ALTER TABLE POST ADD CONSTRAINT FK_POST_AUTHOR_ID FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR (ID)
ALTER TABLE Post_IMAGES ADD CONSTRAINT FK_Post_IMAGES_Post_ID FOREIGN KEY (Post_ID) REFERENCES POST (ID)
ALTER TABLE POST_TAG ADD CONSTRAINT FK_POST_TAG_Post_ID FOREIGN KEY (Post_ID) REFERENCES POST (ID)
ALTER TABLE POST_TAG ADD CONSTRAINT FK_POST_TAG_tags_ID FOREIGN KEY (tags_ID) REFERENCES TAG (ID)
CREATE SEQUENCE SEQ_GEN_SEQUENCE INCREMENT BY 50 START WITH 50
