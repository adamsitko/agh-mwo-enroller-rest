package com.company.enroller.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import com.company.enroller.persistence.MeetingService;
import com.company.enroller.persistence.ParticipantService;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

	@Autowired
	MeetingService meetingService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getMeetings() {
		Collection<Meeting> meetings = meetingService.getAll();
		return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting(@PathVariable("id") String id) {
	     Meeting meeting = meetingService.findById(id);
	     if (meeting == null) {
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	     }
	     return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	 }
	
	/*@RequestMapping(value = "", method = RequestMethod.POST)
	 public ResponseEntity<?> registerParticipant(@RequestBody Participant participant){
		 if (participantService.findByLogin(participant.getLogin()) != null) {
			 return new ResponseEntity("Unable to create. A participant with login " + participant.getLogin() + " already exist.", HttpStatus.CONFLICT);
		 }
		 participantService.create(participant);
		 return new ResponseEntity<Participant>(participant, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 public ResponseEntity<?> deleteParticipant(@PathVariable("id") String login){
		 if (participantService.findByLogin(login) == null) {
			 return new ResponseEntity("Unable to delete. A participant with login given as " + login + " do not exist.", HttpStatus.CONFLICT);
		 }
		 Participant participant = participantService.findByLogin(login);
		 participantService.delete(participant);
		 return new ResponseEntity("A participant with login " + login + " has been deleted.", HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	 public ResponseEntity<?> updateParticipant(@PathVariable("id") String login, @RequestBody Participant newParticipant){
		 Participant participant = participantService.findByLogin(login);
		 if (participant == null) {
			 return new ResponseEntity("Unable to modify. A participant with login " + participant.getLogin() + " do not exist.", HttpStatus.CONFLICT);
		 }
		 participant.setPassword(newParticipant.getPassword());
		 participantService.update(participant);
		 return new ResponseEntity("A participant with login " + participant.getLogin() + " has been update.", HttpStatus.OK);
	 }*/

}
