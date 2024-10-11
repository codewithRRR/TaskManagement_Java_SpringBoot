package com.example.demo.dto;

import com.example.demo.entity.NoteEntity;

public class createNoteResponsedto {

	private Integer taskId;
	private NoteEntity note;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public NoteEntity getNote() {
		return note;
	}

	public void setNote(NoteEntity note) {
		this.note = note;
	}

	public createNoteResponsedto() {

	}

	public createNoteResponsedto(Integer taskId, NoteEntity note) {
		super();
		this.taskId = taskId;
		this.note = note;
	}

}
