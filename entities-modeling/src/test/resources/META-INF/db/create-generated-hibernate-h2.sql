create table Author (id int8 not null, createdDate timestamp, lastModifiedDate timestamp, userName varchar(20) not null, userPassword varchar(40) not null, primary key (id))
create table POST_COMMENT (id int8 not null, createdDate timestamp, lastModifiedDate timestamp, content varchar(2000) not null, author_id int8 not null, post_id int8 not null, primary key (id))
create table Post (id int8 not null, createdDate timestamp, lastModifiedDate timestamp, body varchar(20000) not null, subject varchar(30) not null, author_id int8 not null, primary key (id))
create table Post_Tag (Post_id int8 not null, tags_id int8 not null, primary key (Post_id, tags_id))
create table Post_images (Post_id int8 not null, sizeX int4 not null, sizeY int4 not null, fileName varchar(255) not null, title varchar(30) not null, primary key (Post_id, sizeX, sizeY, fileName, title))
create table Tag (id int8 not null, createdDate timestamp, lastModifiedDate timestamp, name varchar(20) not null, primary key (id))
alter table POST_COMMENT add constraint FK_iil6hwjuquelekw9nyydl900w foreign key (author_id) references Author
alter table POST_COMMENT add constraint FK_5a2wsgsdciw00c3r0ckkcy8bw foreign key (post_id) references Post
alter table Post add constraint FK_m7j5gwmpa7dklv5bnc41ertmi foreign key (author_id) references Author
alter table Post_Tag add constraint FK_t0ml4xx4nhnqjwrhxugwlvrpm foreign key (tags_id) references Tag
alter table Post_Tag add constraint FK_ex1oknil4le2tootduvjnllp6 foreign key (Post_id) references Post
alter table Post_images add constraint FK_e1cctrpwih4egtxo313673h9l foreign key (Post_id) references Post
create sequence hibernate_sequence start 1 increment 1
