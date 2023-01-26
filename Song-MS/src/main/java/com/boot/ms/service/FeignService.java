package com.boot.ms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.boot.ms.model.Artist;

@FeignClient(value = "ARTIST-MS")
public interface FeignService {

	@GetMapping("/artist/get-artist/{id}")
	public Artist getArtistById(@PathVariable int id);
}
