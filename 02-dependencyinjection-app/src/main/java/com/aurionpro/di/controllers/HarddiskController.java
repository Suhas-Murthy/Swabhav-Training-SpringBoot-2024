package com.aurionpro.di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.di.entity.Harddisk;

@RestController
public class HarddiskController {

	@Autowired
	private Harddisk hardisk;
	
	@GetMapping("/harddisks")
	public Harddisk getHarddisk() {
		return hardisk;
	}
}
