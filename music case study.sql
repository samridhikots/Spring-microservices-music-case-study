create database music_case_study;

use music_case_study;
create table song(song_id int PRIMARY KEY AUTO_INCREMENT, song_name varchar(25), album_name varchar(40), artist_id varchar(25));
insert into song(song_name, album_name, artist_id) values("Darasal", "Raabta", 1);
insert into song(song_name, album_name, artist_id) values("Aafreen aafreen", "Coke Studio season 9", 2);
insert into song(song_name, album_name, artist_id) values("Aadat", "Aadat", 1);
insert into song(song_name, album_name, artist_id) values("Shape of you", "Divide", 3);
insert into song(song_name, album_name, artist_id) values("Supermarket flowers", "Divide", 3);

create table artist(artist_id int PRIMARY KEY AUTO_INCREMENT, artist_name varchar(25));
insert into artist(artist_name) values("Atif aslam");
insert into artist(artist_name) values("Rahat Fatah Ali Khan");
insert into artist(artist_name) values("Ed Sheeran");
