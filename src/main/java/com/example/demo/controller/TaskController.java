package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.TaskEntity;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("")
	public ResponseEntity<List<TaskEntity>> getTasks() {
		List<TaskEntity> tasks = taskService.getTasks();
		return ResponseEntity.ok(tasks);

	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id) {
		TaskEntity task = taskService.getTaskById(id);
		if (task == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task);
	}

	@PostMapping("")
	public ResponseEntity<TaskEntity> addTask(@RequestBody TaskDto dto) {
		TaskEntity task = taskService.addTask(dto.getTitle(), dto.getDescription(), dto.getDeadline());

		return ResponseEntity.ok(task);
	}
}
