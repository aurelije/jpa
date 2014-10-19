alter table POST_COMMENT drop constraint if exists FK_iil6hwjuquelekw9nyydl900w
alter table POST_COMMENT drop constraint if exists FK_5a2wsgsdciw00c3r0ckkcy8bw
alter table Post drop constraint if exists FK_m7j5gwmpa7dklv5bnc41ertmi
alter table Post_Tag drop constraint if exists FK_t0ml4xx4nhnqjwrhxugwlvrpm
alter table Post_Tag drop constraint if exists FK_ex1oknil4le2tootduvjnllp6
alter table Post_images drop constraint if exists FK_e1cctrpwih4egtxo313673h9l
drop table Author cascade
drop table POST_COMMENT cascade
drop table Post cascade
drop table Post_Tag cascade
drop table Post_images cascade
drop table Tag cascade
drop sequence entity_sequence
