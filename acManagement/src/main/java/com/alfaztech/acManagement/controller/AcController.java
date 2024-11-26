package com.alfaztech.acManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alfaztech.acManagement.model.AC;
import com.alfaztech.acManagement.service.AcService;

@RestController
@RequestMapping("/api/ac")
@CrossOrigin(origins = "http://localhost:5173")
public class AcController {
	
	@Autowired
	private AcService acService;
	
	
	@GetMapping("/all")
	public List<AC> getAll()
	{
		return acService.getAllAc();
	}
	
	@PostMapping()
	public AC saveAc(@RequestBody AC ac) {
		return acService.saveAC(ac);
	}
	
	// Adding comment for checking build

}
