package com.scrumandcoke.movietheaterclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrumandcoke.movietheaterclub.dto.MultiplexDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.service.MultiplexService;

@RestController
@RequestMapping("/multiplex")
public class MultiplexController {

	@Autowired
	MultiplexService multiplexService;
	
	@GetMapping("/all")
	public List<MultiplexDto> getAllMultiplex() throws GlobalException {
        return multiplexService.getAllMultiplex();
    }
	
	@PostMapping("/add")
	public void addMultiplex(@RequestBody MultiplexDto multiplexDto) throws GlobalException {
		multiplexService.addMultiplex(multiplexDto);
	}
	
	@PutMapping("/update") 
	public void updateMultiplex(@RequestBody MultiplexDto multiplexDto) throws GlobalException {
		multiplexService.updateMultiplex(multiplexDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteMultiplex(@PathVariable Integer id) throws GlobalException {
		multiplexService.deleteMultiplex(id);
	}
}
