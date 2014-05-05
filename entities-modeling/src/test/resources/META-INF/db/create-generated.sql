create table Author (id int8 not null, createdDate timestamp, lastModifiedDate timestamp, userName varchar(255), userPassword varchar(255), primary key (id))
create table Post (id int8 not null, body varchar(255), createdDate timestamp, lastModifiedDate timestamp, postDate timestamp, subject varchar(255), author_id int8, primary key (id))
create table PostComment (id int8 not null, commentDate timestamp, content varchar(255), post_id int8, primary key (id))
create table Post_Tag (Post_id int8 not null, tags_id int8 not null, primary key (Post_id, tags_id))
create table Post_images (Post_id int8 not null, filename varchar(255) not null, sizeX int4 not null, sizeY int4 not null, title varchar(255) not null, primary key (Post_id, filename, sizeX, sizeY, title))
create table Tag (id int8 not null, name varchar(255), primary key (id))
alter table Post add constraint FK_m7j5gwmpa7dklv5bnc41ertmi foreign key (author_id) references Author
alter table PostComment add constraint FK_8r9k1y7s1kkj4jc5aoyrli3ic foreign key (post_id) references Post
alter table Post_Tag add constraint FK_t0ml4xx4nhnqjwrhxugwlvrpm foreign key (tags_id) references Tag
alter table Post_Tag add constraint FK_ex1oknil4le2tootduvjnllp6 foreign key (Post_id) references Post
alter table Post_images add constraint FK_e1cctrpwih4egtxo313673h9l foreign key (Post_id) references Post
create sequence hibernate_sequence start 1 increment 1
