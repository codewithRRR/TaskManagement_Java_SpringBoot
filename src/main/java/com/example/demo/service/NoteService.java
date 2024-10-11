package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.NoteEntity;
import com.example.demo.entity.TaskEntity;

@Service
public class NoteService {

	@Autowired
	TaskService service;
	private HashMap<Integer, TaskNotesHolder> tasknotesholders = new HashMap<>();

	class TaskNotesHolder {
		protected int noteId = 1;
		protected ArrayList<NoteEntity> notes = new ArrayList<>();
	}

	public List<NoteEntity> getNotesForTask(int taskId) {
		TaskEntity task = service.getTaskById(taskId);
		if (task == null) {
			return null;
		}
		if (tasknotesholders.get(taskId) == null) {
			tasknotesholders.put(taskId, new TaskNotesHolder());
		}
		return tasknotesholders.get(taskId).notes;
	}

	public NoteEntity addNoteForTask(int taskId, String title, String body) {
		TaskEntity task = service.getTaskById(taskId);
		if (task == null) {
			return null;
		}
		if (tasknotesholders.get(taskId) == null) {
			tasknotesholders.put(taskId, new TaskNotesHolder());
		}
		TaskNotesHolder taskNotesHolder = tasknotesholders.get(taskId);
		NoteEntity entity = new NoteEntity();
		entity.setId(taskNotesHolder.noteId);
		entity.setTitle(title);
		entity.setBody(body);
		taskNotesHolder.notes.add(entity);
		taskNotesHolder.noteId++;
		return entity;

	}

}
