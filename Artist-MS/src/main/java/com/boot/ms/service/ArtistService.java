package com.boot.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ms.entity.Artist;
import com.boot.ms.repository.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	ArtistRepository repository;

	public List<Artist> getAllArtists() {
		return repository.findAll();
	}

	public Artist getArtist(int id) {
		return repository.findById(id).orElse(null);
	}

	public Artist insertArtist(Artist artist) {
		return repository.save(artist);
	}

	public Artist updateArtist(Artist artist) {
		Artist artistData = repository.findById(artist.getArtistId()).get();
		artistData.setArtistName(artist.getArtistName());
		return repository.save(artistData);
	}

	public void deleteArtist(int id) {
		repository.deleteById(id);
	}
}
