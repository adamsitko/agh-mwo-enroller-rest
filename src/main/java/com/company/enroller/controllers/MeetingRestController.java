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
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	 public ResponseEntity<?> createMeeting(@RequestBody Meeting meeting){
		 if (meetingService.findById(Long.toString(meeting.getId())) != null) {
			 return new ResponseEntity("Unable to create. A meeting with id " + meeting.getId() + " already exist.", HttpStatus.CONFLICT);
		 }
		 meetingService.create(meeting);
		 return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 public ResponseEntity<?> deleteMeeting(@PathVariable("id") String id){
		 if (meetingService.findById(id) == null) {
			 return new ResponseEntity("Unable to delete. A meeting with id given as " + id + " do not exist.", HttpStatus.CONFLICT);
		 }
		 Meeting meeting = meetingService.findById(id);
		 meetingService.delete(meeting);
		 return new ResponseEntity("A meeting with id " + id + " has been deleted.", HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	 public ResponseEntity<?> updatemeeting(@PathVariable("id") String id, @RequestBody Meeting newMeeting){
		 Meeting meeting = meetingService.findById(id);
		 if (meeting == null) {
			 return new ResponseEntity("Unable to modify. A meeting with id " + id + " do not exist.", HttpStatus.CONFLICT);
		 }
		 meeting.setDescription(newMeeting.getDescription());
		 meeting.setTitle(newMeeting.getTitle());
		 meeting.setDate(newMeeting.getDate());
		 meetingService.update(meeting);
		 return new ResponseEntity("A meeting with id " + meeting.getId() + " has been update.", HttpStatus.OK);
	 }

}
