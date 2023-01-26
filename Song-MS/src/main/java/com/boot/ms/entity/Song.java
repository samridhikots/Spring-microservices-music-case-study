package com.boot.ms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "song")
public class Song {
	@Id
	@GeneratedValue
	private int songId;
	private String songName;
	private String albumName;
	private int artistId;

	public Song(String songName, String albumName, int artistId) {
		super();
		this.songName = songName;
		this.albumName = albumName;
		this.artistId = artistId;
	}

}
