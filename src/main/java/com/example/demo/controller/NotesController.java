package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.createNoteDto;
import com.example.demo.dto.createNoteResponsedto;
import com.example.demo.entity.NoteEntity;
import com.example.demo.service.NoteService;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

	@Autowired
	NoteService noteService;

	@GetMapping("")
	public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
		var notes = noteService.getNotesForTask(taskId);
		return ResponseEntity.ok(notes);
	}

	@PostMapping("")
	public ResponseEntity<createNoteResponsedto> addNote(@PathVariable("taskId") Integer taskId,
			@RequestBody createNoteDto body) {
		var note = noteService.addNoteForTask(taskId, body.getTitle(), body.getBody());
		return ResponseEntity.ok(new createNoteResponsedto(taskId, note));

	}
}
