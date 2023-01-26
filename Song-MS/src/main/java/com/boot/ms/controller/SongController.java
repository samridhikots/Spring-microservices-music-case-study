package com.boot.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
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

import com.boot.ms.SongMsApplication;
import com.boot.ms.entity.Song;
import com.boot.ms.model.Artist;
import com.boot.ms.model.FailureResponse;
import com.boot.ms.model.SongArtistResponse;
import com.boot.ms.service.FeignService;
import com.boot.ms.service.SongService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/song")
@RibbonClient(name = "ARTIST-MS", configuration = SongMsApplication.class)
public class SongController {

	@Autowired
	SongService service;

	@Autowired
	FeignService feignService;

	@GetMapping("/get-all-songs")
	public ResponseEntity<List<Song>> getAllSongs() {
		return new ResponseEntity<List<Song>>(service.getAllSongs(), HttpStatus.OK);
	}

	@GetMapping("/get-song/{id}")
	public ResponseEntity<?> getSong(@PathVariable int id) {
		Song song = service.getSong(id);
		ResponseEntity<?> responseEntity = null;

		if (song == null) {
			responseEntity = new ResponseEntity<String>("Song with the given id: " + id + " doesn't exist",
					HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<Song>(song, HttpStatus.OK);
		}
		return responseEntity;
	}

	@PostMapping("/insert-new-song")
	public ResponseEntity<Song> insertNewSong(@RequestBody Song song) {
		return new ResponseEntity<Song>(service.insertSong(song), HttpStatus.OK);
	}

	@PutMapping("/update-song")
	public ResponseEntity<?> updateArtist(@RequestBody Song song) {
		Song songData = service.getSong(song.getSongId());
		ResponseEntity<?> responseEntity = null;

		if (songData == null) {
			responseEntity = new ResponseEntity<String>(
					"Song with the given id: " + song.getSongId() + " doesn't exist", HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<Song>(service.updateSong(song), HttpStatus.OK);
		}
		return responseEntity;
	}

	@DeleteMapping("/delete-song/{id}")
	public ResponseEntity<String> deleteArtist(@PathVariable int id) {
		Song songData = service.getSong(id);
		ResponseEntity<String> responseEntity = null;

		if (songData == null) {
			responseEntity = new ResponseEntity<String>("Song with the given id: " + id + " doesn't exist",
					HttpStatus.NOT_FOUND);
		} else {
			service.deleteSong(id);
			responseEntity = new ResponseEntity<String>("Song deleted with the given id: " + id, HttpStatus.OK);
		}

		return responseEntity;
	}

	@GetMapping("/get-artist-and-songs/{artistId}")
	@HystrixCommand(fallbackMethod = "myFallback")
	public ResponseEntity<?> getArtistAndSongs(@PathVariable int artistId) {
		ResponseEntity<?> responseEntity = null;
		List<Song> song = service.getSongsByArtist(artistId);

//		String url = "http://localhost:5000/artist/get-artist/" + artistId;
//		Artist artist = template.getForObject(url, Artist.class);
		if (song.isEmpty()) {
			responseEntity = new ResponseEntity<String>("No songs found with the given artist id: " + artistId,
					HttpStatus.NOT_FOUND);
		} else {
			SongArtistResponse response = new SongArtistResponse();
			Artist artist = feignService.getArtistById(artistId);
			response.setSong(service.getSongsByArtist(artistId));
			response.setArtist(artist);
			responseEntity = new ResponseEntity<SongArtistResponse>(response, HttpStatus.OK);
		}
		return responseEntity;
	}

	public ResponseEntity<?> myFallback(@PathVariable int artistId) {
		ResponseEntity<?> responseEntity = null;
		List<Song> song = service.getSongsByArtist(artistId);
		if (song.isEmpty()) {
			responseEntity = new ResponseEntity<String>("No songs found with the given artist id: " + artistId,
					HttpStatus.NOT_FOUND);
		} else {
			FailureResponse response = new FailureResponse();
			response.setSongs(service.getSongsByArtist(artistId));
			response.setMessage("Coudln't get the Artists. Artist service is currently down.");
			responseEntity = new ResponseEntity<FailureResponse>(response, HttpStatus.OK);
		}
		return responseEntity;
	}

}