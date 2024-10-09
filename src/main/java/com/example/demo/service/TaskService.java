package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TaskEntity;

@Service
public class TaskService {

	private ArrayList<TaskEntity> tasks = new ArrayList<>();
	private int taskId = 1;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
		TaskEntity entity = new TaskEntity();
		entity.setId(taskId);
		entity.setTitle(title);
		entity.setDescription(description);
		entity.setDeadline(dateFormat.parse(deadline));
		entity.setCompleted(false);
		tasks.add(entity);
		taskId++;
		return entity;
	}

	public ArrayList<TaskEntity> getTasks() {
		return tasks;
	}

	public TaskEntity getTaskById(int id) {
		for (TaskEntity task : tasks) {
			if (task.getId() == id) {
				return task;
			}
		}
		return null;
	}

	public TaskEntity updateTask(int id, String description, String deadline, Boolean completed) throws ParseException {
		TaskEntity task = getTaskById(id);
		if (task == null) {
			return null;

		}
		if (description != null) {
			task.setDescription(description);
		}
		if (deadline != null) {
			task.setDeadline(dateFormat.parse(deadline));
		}
		if (completed != null) {
			task.setCompleted(completed);
		}
		return task;
	}

}
