package com.boot.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.ms.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}
