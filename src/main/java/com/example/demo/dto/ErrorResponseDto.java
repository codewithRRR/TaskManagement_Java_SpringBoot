package com.example.demo.dto;

public class ErrorResponseDto {

	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ErrorResponseDto() {

	}

	public ErrorResponseDto(String error) {
		super();
		this.error = error;
	}

}
