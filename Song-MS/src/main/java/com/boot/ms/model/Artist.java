package com.boot.ms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Artist {
	@Id
	@GeneratedValue
	private int artistId;
	private String artistName;
}
