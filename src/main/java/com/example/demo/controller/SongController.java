package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.entites.Song;
import com.example.demo.services.SongService;


@Controller
public class SongController
{
	@Autowired
	SongService service; 
	
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song)
	{
		
		boolean songStatus=service.songExists(song.getName());
		if(songStatus == false)
		{
		service.addSong(song);
		System.out.println("Song added successfully");
		
	}
		else
		{
			System.out.println("Song already exists");
		}
		return "adminHome";
	}
	@GetMapping("/viewSong")
	public String viewSong(Model model) {
		
		List<Song> songsList=service.fetchAllSongs();
		
		model.addAttribute("songs", songsList);
		return "displaySongs";
	}
	@GetMapping("/playSong")
	public String playSong(Model model)
	{
		boolean premiumUser =true;
		if(premiumUser == true)
		{
		
		List<Song> songsList=service.fetchAllSongs();
		
		model.addAttribute("songs", songsList);
		return "displaySongs";
		}
		else
		{
			return "makePayment";
		}
	}
	
	
	
	

}
