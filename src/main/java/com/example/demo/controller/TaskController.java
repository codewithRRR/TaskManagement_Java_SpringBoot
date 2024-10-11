package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.TaskDto;
import com.example.demo.dto.TaskResponseDto;
import com.example.demo.dto.UpdateTaskDto;
import com.example.demo.entity.TaskEntity;
import com.example.demo.service.NoteService;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	TaskService taskService;

	@Autowired
	NoteService noteService;

	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping("")
	public ResponseEntity<List<TaskEntity>> getTasks() {
		List<TaskEntity> tasks = taskService.getTasks();
		return ResponseEntity.ok(tasks);

	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Integer id) {
		TaskEntity task = taskService.getTaskById(id);
		var notes = noteService.getNotesForTask(id);
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		var taskResponse = modelMapper.map(task, TaskResponseDto.class);
		taskResponse.setNotes(notes);
		return ResponseEntity.ok(taskResponse);
	}

	@PostMapping("")
	public ResponseEntity<TaskEntity> addTask(@RequestBody TaskDto dto) throws ParseException {
		TaskEntity task = taskService.addTask(dto.getTitle(), dto.getDescription(), dto.getDeadline());

		return ResponseEntity.ok(task);

	}

	@PatchMapping("/{id}")
	public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDto dto)
			throws ParseException {
		TaskEntity task = taskService.updateTask(id, dto.getDescription(), dto.getDeadline(), dto.getCompleted());
		if (task == null) {
			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(task);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleErrors(Exception e) {
		if (e instanceof ParseException) {
			return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid Date format"));
		}
		e.printStackTrace();
		return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal server error"));

	}

}
