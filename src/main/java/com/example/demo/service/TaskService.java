package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TaskEntity;

@Service
public class TaskService {

	private ArrayList<TaskEntity> tasks = new ArrayList<>();
	private int taskId = 1;

	public TaskEntity addTask(String title, String description, String deadline) {
		TaskEntity entity = new TaskEntity();
		entity.setId(taskId);
		entity.setTitle(title);
		entity.setDescription(description);
//		entity.setDeadline(new Date(deadline));
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

}
