package com.boot.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ms.entity.Song;
import com.boot.ms.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	SongRepository repository;

	public List<Song> getAllSongs() {
		return repository.findAll();
	}

	public Song getSong(int id) {
		return repository.findById(id).orElse(null);
	}

	public Song insertSong(Song song) {
		return repository.save(song);
	}

	public Song updateSong(Song song) {
		Song songData = repository.findById(song.getSongId()).get();
		songData.setSongName(song.getSongName());
		songData.setAlbumName(song.getAlbumName());
		return repository.save(songData);
	}

	public void deleteSong(int id) {
		repository.deleteById(id);
	}

	public List<Song> getSongsByArtist(int artistId) {
		return repository.findAllByArtistId(artistId);
	}
}
