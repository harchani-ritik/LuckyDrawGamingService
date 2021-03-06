package com.luckydraw.luckydraw.controller;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckydraw.luckydraw.entities.RaffleTicket;
import com.luckydraw.luckydraw.entities.User;
import com.luckydraw.luckydraw.services.LuckyDrawService;

@RestController
public class LuckyDrawController {
	
	@Autowired 
	LuckyDrawService luckyDrawService;
	
	@GetMapping("/")
	public String connectionCheck(){
		/*The following method is used to populate events table admin with event timing and prizes*/
//		return luckyDrawService.populateEventsTable();
		return "Welcome to Lucky Draw Gaming Service";
	}
	
	@PostMapping("/register")
	public ResponseEntity<HashMap<String,String>> registerUser(@RequestBody User user) {
		return luckyDrawService.registerNewUser(user);
	}
	
	@GetMapping("/raffle-ticket/{userId}")
	public ResponseEntity<HashMap<String,String>> generateRaffleTicket(@PathVariable String userId) {
		return luckyDrawService.generateRaffleTicket(userId);
	}
	
	@PostMapping("/participate")
	public ResponseEntity<HashMap<String,String>> participateInEvent(@RequestBody RaffleTicket raffleTicket) throws InterruptedException, ExecutionException {
		return luckyDrawService.participateInEvent(raffleTicket);
	}
	
	@GetMapping("/winners")
	public ResponseEntity<HashMap<String,String>> getWinnersList() throws InterruptedException, ExecutionException{
		return luckyDrawService.fetchWinners();
	}
	
	@GetMapping("/events")
	public ResponseEntity<HashMap<String,Object>> getEvents() throws InterruptedException, ExecutionException{
		return luckyDrawService.fetchEvents();
	}
	
	@PostMapping("/winner")
	public ResponseEntity<HashMap<String,String>> computeWinner(@RequestBody HashMap<String,String> map) throws InterruptedException, ExecutionException {
		return luckyDrawService.computeWinner(map);
	}
	
}
