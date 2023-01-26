package com.boot.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.ms.entity.Artist;
import com.boot.ms.service.ArtistService;

@RestController
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	ArtistService service;

	@GetMapping("/get-all-artists")
	public ResponseEntity<List<Artist>> getAllArtists() {
		return new ResponseEntity<List<Artist>>(service.getAllArtists(), HttpStatus.OK);
	}

	@GetMapping("/get-artist/{id}")
	public ResponseEntity<?> getArtist(@PathVariable int id) {
		Artist artist = service.getArtist(id);
		ResponseEntity<?> responseEntity = null;

		if (artist == null) {
			responseEntity = new ResponseEntity<String>("Artist with the given id: " + id + " doesn't exist",
					HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<Artist>(artist, HttpStatus.OK);
		}
		return responseEntity;
	}

	@PostMapping("/insert-new-artist")
	public ResponseEntity<Artist> insertNewArtist(@RequestBody Artist artist) {
		return new ResponseEntity<Artist>(service.insertArtist(artist), HttpStatus.OK);
	}

	@PutMapping("/update-artist")
	public ResponseEntity<?> updateArtist(@RequestBody Artist artist) {
		Artist artistData = service.getArtist(artist.getArtistId());
		ResponseEntity<?> responseEntity = null;

		if (artistData == null) {
			responseEntity = new ResponseEntity<String>(
					"Artist with the given id: " + artist.getArtistId() + " doesn't exist", HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<Artist>(service.updateArtist(artist), HttpStatus.OK);
		}
		return responseEntity;
	}

	@DeleteMapping("/delete-artist/{id}")
	public ResponseEntity<String> deleteArtist(@PathVariable int id) {
		Artist artist = service.getArtist(id);
		ResponseEntity<String> responseEntity = null;

		if (artist == null) {
			responseEntity = new ResponseEntity<String>("Artist with the given id: " + id + " doesn't exist",
					HttpStatus.NOT_FOUND);
		} else {
			service.deleteArtist(id);
			responseEntity = new ResponseEntity<String>("Artist deleted with the given id: " + id, HttpStatus.OK);
		}

		return responseEntity;
	}
}
