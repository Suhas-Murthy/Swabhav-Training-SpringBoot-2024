package com.aurionpro.jpacrud.dto;

import java.util.List;

import com.aurionpro.jpacrud.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageResponseDto<T> {
	private long totalElements;
	private int totalPages;
	private int size;
	private List<T> content;
	private boolean isLastPage;

}
