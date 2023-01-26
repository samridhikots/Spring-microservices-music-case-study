package com.boot.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.ms.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

	public List<Song> findAllByArtistId(int artistId);

}
