package com.boot.ms.model;

import java.util.List;

import com.boot.ms.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongArtistResponse {
	private List<Song> song;
	private Artist artist;

}
