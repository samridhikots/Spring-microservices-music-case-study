package com.boot.ms.model;

import java.util.List;

import com.boot.ms.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailureResponse {
	private List<Song> songs;
	private String message;
}
