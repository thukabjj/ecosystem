package br.com.ecosystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@GetMapping
	@ResponseBody
	public ResponseEntity<String> getClients(){
		return ResponseEntity.ok("Arthur");
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<String> registerClient(){
		return ResponseEntity.ok("Arthur");
	}
	@PutMapping
	@ResponseBody
	public ResponseEntity<String> updateClient(){
		return ResponseEntity.ok("Arthur");
	}
	
	@DeleteMapping
	public void deleteClient(){
	}
}
